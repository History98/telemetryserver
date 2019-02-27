/*
    Author: KMN
    Descr: Main Entry Point For Telemetry Agent.
    Core Components/Frameworks Used: postgreSQL Database, Prometheus, RESTful JAX-RS.
    Institution: LJMU
 */



package com.telemetryserver.client;


import com.telemetryserver.Instrumentation.*;

import com.telemetryserver.model.*;
import com.telemetryserver.dao.*;
import io.prometheus.client.*;
import io.prometheus.client.exporter.MetricsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TelemetryApp
{

    public static void main(String[] args)
    {
        //Will not work if no database is running
        //Prints Nodes in Database
        //printAllNodes();

        //NodeMonitoring.startPrometheusServer(CollectorRegistry.defaultRegistry, 2018);
        //NodeMonitoring.startPrometheusServer(collectorRegistry, 2018);

        ODLNodeInstrumetation.get_instance();
        instrumentODLNodes(1000);
        startTestServer(2018);
    }


    private static void printAllNodes()
    {
        System.out.println("List Of All Nodes is : \n");
        List<Node> nodes = new NodeDAOImpl().getAllNodes();

        for(Node node : nodes)
        {
            System.out.println(node);
            System.out.println("\t" + node.get_nodeMetrics());
            List<LinkMetrics> lm = node.get_linkMetrics();

            for(LinkMetrics l : lm)
                System.out.println("\t" + "\t" + l);

            System.out.println("");
        }
    }


    private static void instrumentNode_1(long period)
    {
        Timer pollTimer = new Timer();
        pollTimer.scheduleAtFixedRate(new DatabasePollScheduler(), 0, period);


        System.out.print("Started instrumentation");
    }


    private static void instrumentODLNodes(long period)
    {
        Timer pollTimer = new Timer();
        pollTimer.scheduleAtFixedRate(new ODLParamPollScheduler(), 0, period);


        System.out.print("Started ODL instrumentation");
    }

    public static void startTestServer(int port)
    {
        try
        {

            Server server = new Server(port);
            ServletContextHandler context = new ServletContextHandler();
            context.setContextPath("/");
            server.setHandler(context);

            //Expose our Instrumented servlet.
            //context.addServlet(new ServletHolder(Node_1_Instrumented_Average_Power.getInstance()), "/");

            context.addServlet(new ServletHolder(ODLNodeInstrumetation.get_instance()), "/");

            //Prometheus Metrics Servlet
            context.addServlet(new ServletHolder(new MetricsServlet()), "/metrics");

            // Add metrics about CPU, JVM memory etc.
            //DefaultExports.initialize();

            // Start the webserver.
            server.start();
            server.join();


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
 }

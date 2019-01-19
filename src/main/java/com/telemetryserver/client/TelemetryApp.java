/*
    Author: KMN
    Descr: Main Entry Point For Telemetry Agent.
    Core Components/Frameworks Used: postgreSQL Database, Prometheus, RESTful JAX-RS.
    Institution: LJMU
 */



package com.telemetryserver.client;


import com.telemetryserver.Instrumentation.*;

import com.codahale.metrics.MetricRegistry;
import com.telemetryserver.model.*;
import com.telemetryserver.dao.*;
import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
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
        printAllNodes();


        //CollectorRegistry collectorRegistry = new CollectorRegistry();

        //Collector foo1_collector = Gauge.build().name("foo1").help("help foo1").create();
        //Collector foo2_collector = Counter.build().name("foo2").help("help foo2").create();

        //((Gauge) foo1_collector).inc();
        //((Counter) foo2_collector).inc();

        //collectorRegistry.register(foo1_collector);
        //collectorRegistry.register(foo2_collector);


        //CollectorRegistry.defaultRegistry.register(foo1_collector);
        //CollectorRegistry.defaultRegistry.register(foo2_collector);

        //foo1_collector.register(CollectorRegistry.defaultRegistry);
        //foo2_collector.register(CollectorRegistry.defaultRegistry);

        //Node_1_Instrumented_Utilisation.getInstance();
        //Node_1_Instrumented_Average_Power.getInstance();

        //new Node_1_dummyvariable2();
        //new Node_1_dummyvariable();

        //Node_1_dummyvariable2.setProgress2(5);
        //Node_1_dummyvariable.setProgress1(3);

        //System.out.print(collectorRegistry.metricFamilySamples().);

        //instrumentNode_1(1000);

        //NodeMonitoring.startPrometheusServer(CollectorRegistry.defaultRegistry, 2018);
        //NodeMonitoring.startPrometheusServer(collectorRegistry, 2018);


        //startTestServer(2018);

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


    public static void startTestServer(int port)
    {
        try
        {
            Server server = new Server(port);
            ServletContextHandler context = new ServletContextHandler();
            context.setContextPath("/");
            server.setHandler(context);

            //Expose our Instrumented servlet.
            context.addServlet(new ServletHolder(Instrumented_Class.getInstance()), "/");

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

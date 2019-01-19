package com.telemetryserver.Instrumentation;

import java.io.IOException;
import java.util.*;
import com.telemetryserver.model.*;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NodeMonitoring
{
    static class ExampleServlet extends HttpServlet
    {
        static final Counter requests = Counter.build()
                .name("hello_worlds_total_example")
                .help("Number of hello worlds served.").register();

        @Override
        protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
                throws ServletException, IOException {
            resp.getWriter().println("Hello World! example node monitor");
            // Increment the number of requests.
            requests.inc();
        }
    }


    public static void updateNodeInstrumentation(List<Node> currentNodes)
     {
         for(Node node : currentNodes)
         {
             //We are only monitoring Node_1 for now
             if(node.get_name().equals("Node 1"))
             {
                 //Get Current Metrics
                 Node_1_Instrumented_Utilisation node_1_utilisation = Node_1_Instrumented_Utilisation.getInstance();
                 //Node_1_Instrumented_Average_Power node_1_average_power = Node_1_Instrumented_Average_Power.getInstance();

                 float current_utilisation = node.get_nodeMetrics().get_utilisation();
                 //float current_average_power = node.get_nodeMetrics().get_average_power();

                 System.out.println(current_utilisation);
                 //System.out.println(current_average_power);

                 //Update Prometheus metrics
                 //node_1_average_power.setNode_1_average_power(current_average_power);
                 node_1_utilisation.setNode_1_utilisation(current_utilisation);
             }
         }
     }


     public static void startPrometheusServer(CollectorRegistry collectorRegistry, int port)
     {

         Timer myPeriodicTimer = new Timer();
         TimerTask myTimerHandler = new TimerTask()
         {
             @Override
             public void run()
             {
                 System.out.print(1);
             }
         };
         myPeriodicTimer.scheduleAtFixedRate(myTimerHandler, 0, 1000);

         try
         {
             Server server = new Server(port);
             ServletContextHandler context = new ServletContextHandler();
             context.setContextPath("/");
             server.setHandler(context);

             // Expose our example servlet.
             context.addServlet(new ServletHolder(Node_1_Instrumented_Utilisation.getInstance()), "/u");
             //context.addServlet(new ServletHolder(Node_1_Instrumented_Average_Power.getInstance()), "/p");

             //context.addServlet(new ServletHolder(new ExampleServlet()), "/");

             // Expose Promtheus metrics.
             //context.addServlet(new ServletHolder(new CustomMetricsServlet(collectorRegistry)), "/metrics");

             context.addServlet(new ServletHolder(new MetricsServlet(collectorRegistry)), "/metrics");
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

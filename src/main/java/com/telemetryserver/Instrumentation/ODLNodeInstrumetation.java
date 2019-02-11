package com.telemetryserver.Instrumentation;

import com.telemetryserver.dao.ODLRESTHelper;
import io.prometheus.client.Gauge;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ODLNodeInstrumetation extends HttpServlet
{
    private static Map<Integer, String> metric_list_packets_received = new HashMap<Integer, String>();
    private static Map<Integer, String> metric_list_packets_sent = new HashMap<Integer, String>();;

    private static ODLNodeInstrumetation _instance = new ODLNodeInstrumetation();

    public static ODLNodeInstrumetation get_instance()
    {
        if (_instance == null)
            _instance = new ODLNodeInstrumetation();

        return _instance;
    }




    //ODL Node Metrics
    public static final Gauge Node_1_Link_1_packets_received_total = Gauge.build().name("Node_1_Link_1_packets_received_total")
            .help("Node_1_Link_1_packets_received_total").register();

    public static final Gauge Node_1_Link_1_packets_sent_total = Gauge.build().name("Node_1_Link_1_packets_sent_total")
            .help("Node_1_Link_1_packets_sent_total").register();


    public static final Gauge Node_2_Link_1_packets_received_total = Gauge.build().name("Node_2_Link_1_packets_received_total")
            .help("Node_2_Link_1_packets_received_total").register();

    public static final Gauge Node_2_Link_1_packets_sent_total = Gauge.build().name("Node_2_Link_1_packets_sent_total")
            .help("Node_2_Link_1_packets_sent_total").register();


    public static final Gauge Node_3_Link_1_packets_received_total = Gauge.build().name("Node_3_Link_1_packets_received_total")
            .help("Node_3_Link_1_packets_received_total").register();

    public static final Gauge Node_3_Link_1_packets_sent_total = Gauge.build().name("Node_3_Link_1_packets_sent_total")
            .help("Node_3_Link_1_packets_sent_total").register();


    public static final Gauge Node_4_Link_1_packets_received_total = Gauge.build().name("Node_4_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_4_Link_1_packets_received_total").register();

    public static final Gauge Node_4_Link_1_packets_sent_total = Gauge.build().name("Node_4_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_4_Link_1_packets_sent_total").register();


    public static final Gauge Node_5_Link_1_packets_received_total = Gauge.build().name("Node_5_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_5_Link_1_packets_received_total").register();

    public static final Gauge Node_5_Link_1_packets_sent_total = Gauge.build().name("Node_5_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_5_Link_1_packets_sent_total").register();


    public static final Gauge Node_6_Link_1_packets_received_total = Gauge.build().name("Node_6_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_6_Link_1_packets_received_total").register();

    public static final Gauge Node_6_Link_1_packets_sent_total = Gauge.build().name("Node_6_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_6_Link_1_packets_sent_total").register();


    public static final Gauge Node_7_Link_1_packets_received_total = Gauge.build().name("Node_7_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_7_Link_1_packets_received_total").register();

    public static final Gauge Node_7_Link_1_packets_sent_total = Gauge.build().name("Node_7_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_7_Link_1_packets_sent_total").register();


    public static final Gauge Node_8_Link_1_packets_received_total = Gauge.build().name("Node_8_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_8_Link_1_packets_received_total").register();

    public static final Gauge Node_8_Link_1_packets_sent_total = Gauge.build().name("Node_8_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_8_Link_1_packets_sent_total").register();


    public static final Gauge Node_9_Link_1_packets_received_total = Gauge.build().name("Node_9_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_9_Link_1_packets_received_total").register();

    public static final Gauge Node_9_Link_1_packets_sent_total = Gauge.build().name("Node_9_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_9_Link_1_packets_sent_total").register();


    public static final Gauge Node_10_Link_1_packets_received_total = Gauge.build().name("Node_10_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_10_Link_1_packets_received_total").register();

    public static final Gauge Node_10_Link_1_packets_sent_total = Gauge.build().name("Node_10_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_10_Link_1_packets_sent_total").register();


    public static final Gauge Node_11_Link_1_packets_received_total = Gauge.build().name("Node_11_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_11_Link_1_packets_received_total").register();

    public static final Gauge Node_11_Link_1_packets_sent_total = Gauge.build().name("Node_11_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_11_Link_1_packets_sent_total").register();


    public static final Gauge Node_12_Link_1_packets_received_total = Gauge.build().name("Node_12_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_12_Link_1_packets_received_total").register();

    public static final Gauge Node_12_Link_1_packets_sent_total = Gauge.build().name("Node_12_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_12_Link_1_packets_sent_total").register();


    public static final Gauge Node_13_Link_1_packets_received_total = Gauge.build().name("Node_13_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_13_Link_1_packets_received_total").register();

    public static final Gauge Node_13_Link_1_packets_sent_total = Gauge.build().name("Node_13_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_13_Link_1_packets_sent_total").register();


    public static final Gauge Node_14_Link_1_packets_received_total = Gauge.build().name("Node_14_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_14_Link_1_packets_received_total").register();

    public static final Gauge Node_14_Link_1_packets_sent_total = Gauge.build().name("Node_14_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_14_Link_1_packets_sent_total").register();


    public static final Gauge Node_15_Link_1_packets_received_total = Gauge.build().name("Node_15_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_15_Link_1_packets_received_total").register();

    public static final Gauge Node_15_Link_1_packets_sent_total = Gauge.build().name("Node_15_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_15_Link_1_packets_sent_total").register();


    public static final Gauge Node_16_Link_1_packets_received_total = Gauge.build().name("Node_16_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_16_Link_1_packets_received_total").register();

    public static final Gauge Node_16_Link_1_packets_sent_total = Gauge.build().name("Node_16_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_16_Link_1_packets_sent_total").register();


    public static final Gauge Node_17_Link_1_packets_received_total = Gauge.build().name("Node_17_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_17_Link_1_packets_received_total").register();

    public static final Gauge Node_17_Link_1_packets_sent_total = Gauge.build().name("Node_17_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_17_Link_1_packets_sent_total").register();


    public static final Gauge Node_18_Link_1_packets_received_total = Gauge.build().name("Node_18_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_18_Link_1_packets_received_total").register();

    public static final Gauge Node_18_Link_1_packets_sent_total = Gauge.build().name("Node_18_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_18_Link_1_packets_sent_total").register();


    public static final Gauge Node_19_Link_1_packets_received_total = Gauge.build().name("Node_19_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_19_Link_1_packets_received_total").register();

    public static final Gauge Node_19_Link_1_packets_sent_total = Gauge.build().name("Node_19_Link_1_packets_sent_total")
            .labelNames("Packet").help("Node_19_Link_1_packets_sent_total").register();

    public static final Gauge Node_20_Link_1_packets_received_total = Gauge.build().name("Node_20_Link_1_packets_received_total")
            .labelNames("Packet").help("Node_20_Link_1_packets_received_total").register();

    public static final Gauge Node_20_Link_1_packets_sent_total = Gauge.build().name("Node_20_Link_1_packets_sent_totall")
            .labelNames("Packet").help("Node_20_Link_1_packets_sent_total").register();


    public static void updateODLPacketMetrics()
    {
        System.out.println("Updating ODL Packet Metrics");

        //Used for 19 switches
        int N = 3; //get number of nodes
        int linkNumber = 1;

        int received = ODLRESTHelper.ODLRESTNodeLinkPacketParamExtraction(1, linkNumber, "received");
        int sent = ODLRESTHelper.ODLRESTNodeLinkPacketParamExtraction(1, linkNumber, "transmitted");

        Node_1_Link_1_packets_received_total.set(received);
        Node_1_Link_1_packets_sent_total.set(sent);

        received = ODLRESTHelper.ODLRESTNodeLinkPacketParamExtraction(2, linkNumber, "received");
        sent = ODLRESTHelper.ODLRESTNodeLinkPacketParamExtraction(2, linkNumber, "transmitted");

        Node_2_Link_1_packets_received_total.set(received);
        Node_2_Link_1_packets_sent_total.set(sent);

        received = ODLRESTHelper.ODLRESTNodeLinkPacketParamExtraction(3, linkNumber, "received");
        sent = ODLRESTHelper.ODLRESTNodeLinkPacketParamExtraction(3, linkNumber, "transmitted");

        Node_3_Link_1_packets_received_total.set(received);
        Node_3_Link_1_packets_sent_total.set(sent);
    }


    public ODLNodeInstrumetation()
    {
        metric_list_packets_received.put(1, "Node_1_Link_1_packets_received_total");
        metric_list_packets_received.put(2, "Node_2_Link_1_packets_received_total");
        metric_list_packets_received.put(3, "Node_3_Link_1_packets_received_total");

        metric_list_packets_sent.put(1, "Node_1_Link_1_packets_sent_total");
        metric_list_packets_sent.put(2, "Node_2_Link_1_packets_sent_total");
        metric_list_packets_sent.put(3, "Node_3_Link_1_packets_sent_total");

    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException
    {
        resp.getWriter().println("Hello from ODL Instrumetation!!!" + Node_1_Link_1_packets_sent_total.get());
        resp.getWriter().println("Hello from ODL Instrumetation!!!" + Node_2_Link_1_packets_sent_total.get());
        resp.getWriter().println("Hello from ODL Instrumetation!!!" + Node_3_Link_1_packets_sent_total.get());

        resp.getWriter().println("Hello from ODL Instrumetation!!!" + Node_1_Link_1_packets_sent_total.get());
        resp.getWriter().println("Hello from ODL Instrumetation!!!" + Node_2_Link_1_packets_sent_total.get());
        resp.getWriter().println("Hello from ODL Instrumetation!!!" + Node_3_Link_1_packets_sent_total.get());

    }
}

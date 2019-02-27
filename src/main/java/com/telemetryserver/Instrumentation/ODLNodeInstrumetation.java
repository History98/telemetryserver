/*
 Author: KMN
 Descr: Node Instrumentation
 Institution: LJMU
 */


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
    public static int[][] prevTXBytes = new int[32][5]; //Updated every second!!!!
    public static int linkBW = (int) 8e7; //This is 10MB;

    private static ODLNodeInstrumetation _instance = new ODLNodeInstrumetation();

    public static ODLNodeInstrumetation get_instance()
    {
        if (_instance == null)
            _instance = new ODLNodeInstrumetation();

        return _instance;
    }


    //ODL Node Metrics
    //region packets_received_total

    public static final Gauge Node_1_Link_1_packets_received_total = Gauge.build().name("Node_1_Link_1_packets_received_total").help("Node_1_Link_1_packets_received_total").register();

    public static final Gauge Node_2_Link_1_packets_received_total = Gauge.build().name("Node_2_Link_1_packets_received_total").help("Node_2_Link_1_packets_received_total").register();

    public static final Gauge Node_3_Link_1_packets_received_total = Gauge.build().name("Node_3_Link_1_packets_received_total").help("Node_3_Link_1_packets_received_total").register();

    public static final Gauge Node_4_Link_1_packets_received_total = Gauge.build().name("Node_4_Link_1_packets_received_total").help("Node_4_Link_1_packets_received_total").register();

    public static final Gauge Node_5_Link_1_packets_received_total = Gauge.build().name("Node_5_Link_1_packets_received_total").help("Node_5_Link_1_packets_received_total").register();

    public static final Gauge Node_6_Link_1_packets_received_total = Gauge.build().name("Node_6_Link_1_packets_received_total").help("Node_6_Link_1_packets_received_total").register();

    public static final Gauge Node_7_Link_1_packets_received_total = Gauge.build().name("Node_7_Link_1_packets_received_total").help("Node_7_Link_1_packets_received_total").register();

    public static final Gauge Node_8_Link_1_packets_received_total = Gauge.build().name("Node_8_Link_1_packets_received_total").help("Node_8_Link_1_packets_received_total").register();

    public static final Gauge Node_9_Link_1_packets_received_total = Gauge.build().name("Node_9_Link_1_packets_received_total").help("Node_9_Link_1_packets_received_total").register();

    public static final Gauge Node_10_Link_1_packets_received_total = Gauge.build().name("Node_10_Link_1_packets_received_total").help("Node_10_Link_1_packets_received_total").register();

    public static final Gauge Node_11_Link_1_packets_received_total = Gauge.build().name("Node_11_Link_1_packets_received_total").help("Node_11_Link_1_packets_received_total").register();

    public static final Gauge Node_12_Link_1_packets_received_total = Gauge.build().name("Node_12_Link_1_packets_received_total").help("Node_12_Link_1_packets_received_total").register();

    public static final Gauge Node_13_Link_1_packets_received_total = Gauge.build().name("Node_13_Link_1_packets_received_total").help("Node_13_Link_1_packets_received_total").register();

    public static final Gauge Node_14_Link_1_packets_received_total = Gauge.build().name("Node_14_Link_1_packets_received_total").help("Node_14_Link_1_packets_received_total").register();

    public static final Gauge Node_15_Link_1_packets_received_total = Gauge.build().name("Node_15_Link_1_packets_received_total").help("Node_15_Link_1_packets_received_total").register();

    public static final Gauge Node_16_Link_1_packets_received_total = Gauge.build().name("Node_16_Link_1_packets_received_total").help("Node_16_Link_1_packets_received_total").register();

    public static final Gauge Node_17_Link_1_packets_received_total = Gauge.build().name("Node_17_Link_1_packets_received_total").help("Node_17_Link_1_packets_received_total").register();

    public static final Gauge Node_18_Link_1_packets_received_total = Gauge.build().name("Node_18_Link_1_packets_received_total").help("Node_18_Link_1_packets_received_total").register();

    public static final Gauge Node_19_Link_1_packets_received_total = Gauge.build().name("Node_19_Link_1_packets_received_total").help("Node_19_Link_1_packets_received_total").register();

    public static final Gauge Node_20_Link_1_packets_received_total = Gauge.build().name("Node_20_Link_1_packets_received_total").help("Node_20_Link_1_packets_received_total").register();

    public static final Gauge Node_1_Link_2_packets_received_total = Gauge.build().name("Node_1_Link_2_packets_received_total").help("Node_1_Link_2_packets_received_total").register();

    public static final Gauge Node_2_Link_2_packets_received_total = Gauge.build().name("Node_2_Link_2_packets_received_total").help("Node_2_Link_2_packets_received_total").register();

    public static final Gauge Node_3_Link_2_packets_received_total = Gauge.build().name("Node_3_Link_2_packets_received_total").help("Node_3_Link_2_packets_received_total").register();

    public static final Gauge Node_4_Link_2_packets_received_total = Gauge.build().name("Node_4_Link_2_packets_received_total").help("Node_4_Link_2_packets_received_total").register();

    public static final Gauge Node_5_Link_2_packets_received_total = Gauge.build().name("Node_5_Link_2_packets_received_total").help("Node_5_Link_2_packets_received_total").register();

    public static final Gauge Node_6_Link_2_packets_received_total = Gauge.build().name("Node_6_Link_2_packets_received_total").help("Node_6_Link_2_packets_received_total").register();

    public static final Gauge Node_7_Link_2_packets_received_total = Gauge.build().name("Node_7_Link_2_packets_received_total").help("Node_7_Link_2_packets_received_total").register();

    public static final Gauge Node_8_Link_2_packets_received_total = Gauge.build().name("Node_8_Link_2_packets_received_total").help("Node_8_Link_2_packets_received_total").register();

    public static final Gauge Node_9_Link_2_packets_received_total = Gauge.build().name("Node_9_Link_2_packets_received_total").help("Node_9_Link_2_packets_received_total").register();

    public static final Gauge Node_10_Link_2_packets_received_total = Gauge.build().name("Node_10_Link_2_packets_received_total").help("Node_10_Link_2_packets_received_total").register();

    public static final Gauge Node_11_Link_2_packets_received_total = Gauge.build().name("Node_11_Link_2_packets_received_total").help("Node_11_Link_2_packets_received_total").register();

    public static final Gauge Node_12_Link_2_packets_received_total = Gauge.build().name("Node_12_Link_2_packets_received_total").help("Node_12_Link_2_packets_received_total").register();

    public static final Gauge Node_13_Link_2_packets_received_total = Gauge.build().name("Node_13_Link_2_packets_received_total").help("Node_13_Link_2_packets_received_total").register();

    public static final Gauge Node_14_Link_2_packets_received_total = Gauge.build().name("Node_14_Link_2_packets_received_total").help("Node_14_Link_2_packets_received_total").register();

    public static final Gauge Node_15_Link_2_packets_received_total = Gauge.build().name("Node_15_Link_2_packets_received_total").help("Node_15_Link_2_packets_received_total").register();

    public static final Gauge Node_16_Link_2_packets_received_total = Gauge.build().name("Node_16_Link_2_packets_received_total").help("Node_16_Link_2_packets_received_total").register();

    public static final Gauge Node_17_Link_2_packets_received_total = Gauge.build().name("Node_17_Link_2_packets_received_total").help("Node_17_Link_2_packets_received_total").register();

    public static final Gauge Node_18_Link_2_packets_received_total = Gauge.build().name("Node_18_Link_2_packets_received_total").help("Node_18_Link_2_packets_received_total").register();

    public static final Gauge Node_19_Link_2_packets_received_total = Gauge.build().name("Node_19_Link_2_packets_received_total").help("Node_19_Link_2_packets_received_total").register();

    public static final Gauge Node_20_Link_2_packets_received_total = Gauge.build().name("Node_20_Link_2_packets_received_total").help("Node_20_Link_2_packets_received_total").register();

    public static final Gauge Node_1_Link_3_packets_received_total = Gauge.build().name("Node_1_Link_3_packets_received_total").help("Node_1_Link_3_packets_received_total").register();

    public static final Gauge Node_2_Link_3_packets_received_total = Gauge.build().name("Node_2_Link_3_packets_received_total").help("Node_2_Link_3_packets_received_total").register();

    public static final Gauge Node_3_Link_3_packets_received_total = Gauge.build().name("Node_3_Link_3_packets_received_total").help("Node_3_Link_3_packets_received_total").register();

    public static final Gauge Node_4_Link_3_packets_received_total = Gauge.build().name("Node_4_Link_3_packets_received_total").help("Node_4_Link_3_packets_received_total").register();

    public static final Gauge Node_5_Link_3_packets_received_total = Gauge.build().name("Node_5_Link_3_packets_received_total").help("Node_5_Link_3_packets_received_total").register();

    public static final Gauge Node_6_Link_3_packets_received_total = Gauge.build().name("Node_6_Link_3_packets_received_total").help("Node_6_Link_3_packets_received_total").register();

    public static final Gauge Node_7_Link_3_packets_received_total = Gauge.build().name("Node_7_Link_3_packets_received_total").help("Node_7_Link_3_packets_received_total").register();

    public static final Gauge Node_8_Link_3_packets_received_total = Gauge.build().name("Node_8_Link_3_packets_received_total").help("Node_8_Link_3_packets_received_total").register();

    public static final Gauge Node_9_Link_3_packets_received_total = Gauge.build().name("Node_9_Link_3_packets_received_total").help("Node_9_Link_3_packets_received_total").register();

    public static final Gauge Node_10_Link_3_packets_received_total = Gauge.build().name("Node_10_Link_3_packets_received_total").help("Node_10_Link_3_packets_received_total").register();

    public static final Gauge Node_11_Link_3_packets_received_total = Gauge.build().name("Node_11_Link_3_packets_received_total").help("Node_11_Link_3_packets_received_total").register();

    public static final Gauge Node_12_Link_3_packets_received_total = Gauge.build().name("Node_12_Link_3_packets_received_total").help("Node_12_Link_3_packets_received_total").register();

    public static final Gauge Node_13_Link_3_packets_received_total = Gauge.build().name("Node_13_Link_3_packets_received_total").help("Node_13_Link_3_packets_received_total").register();

    public static final Gauge Node_14_Link_3_packets_received_total = Gauge.build().name("Node_14_Link_3_packets_received_total").help("Node_14_Link_3_packets_received_total").register();

    public static final Gauge Node_15_Link_3_packets_received_total = Gauge.build().name("Node_15_Link_3_packets_received_total").help("Node_15_Link_3_packets_received_total").register();

    public static final Gauge Node_16_Link_3_packets_received_total = Gauge.build().name("Node_16_Link_3_packets_received_total").help("Node_16_Link_3_packets_received_total").register();

    public static final Gauge Node_17_Link_3_packets_received_total = Gauge.build().name("Node_17_Link_3_packets_received_total").help("Node_17_Link_3_packets_received_total").register();

    public static final Gauge Node_18_Link_3_packets_received_total = Gauge.build().name("Node_18_Link_3_packets_received_total").help("Node_18_Link_3_packets_received_total").register();

    public static final Gauge Node_19_Link_3_packets_received_total = Gauge.build().name("Node_19_Link_3_packets_received_total").help("Node_19_Link_3_packets_received_total").register();

    public static final Gauge Node_20_Link_3_packets_received_total = Gauge.build().name("Node_20_Link_3_packets_received_total").help("Node_20_Link_3_packets_received_total").register();

    public static final Gauge Node_1_Link_4_packets_received_total = Gauge.build().name("Node_1_Link_4_packets_received_total").help("Node_1_Link_4_packets_received_total").register();

    public static final Gauge Node_2_Link_4_packets_received_total = Gauge.build().name("Node_2_Link_4_packets_received_total").help("Node_2_Link_4_packets_received_total").register();

    public static final Gauge Node_3_Link_4_packets_received_total = Gauge.build().name("Node_3_Link_4_packets_received_total").help("Node_3_Link_4_packets_received_total").register();

    public static final Gauge Node_4_Link_4_packets_received_total = Gauge.build().name("Node_4_Link_4_packets_received_total").help("Node_4_Link_4_packets_received_total").register();

    public static final Gauge Node_5_Link_4_packets_received_total = Gauge.build().name("Node_5_Link_4_packets_received_total").help("Node_5_Link_4_packets_received_total").register();

    public static final Gauge Node_6_Link_4_packets_received_total = Gauge.build().name("Node_6_Link_4_packets_received_total").help("Node_6_Link_4_packets_received_total").register();

    public static final Gauge Node_7_Link_4_packets_received_total = Gauge.build().name("Node_7_Link_4_packets_received_total").help("Node_7_Link_4_packets_received_total").register();

    public static final Gauge Node_8_Link_4_packets_received_total = Gauge.build().name("Node_8_Link_4_packets_received_total").help("Node_8_Link_4_packets_received_total").register();

    public static final Gauge Node_9_Link_4_packets_received_total = Gauge.build().name("Node_9_Link_4_packets_received_total").help("Node_9_Link_4_packets_received_total").register();

    public static final Gauge Node_10_Link_4_packets_received_total = Gauge.build().name("Node_10_Link_4_packets_received_total").help("Node_10_Link_4_packets_received_total").register();

    public static final Gauge Node_11_Link_4_packets_received_total = Gauge.build().name("Node_11_Link_4_packets_received_total").help("Node_11_Link_4_packets_received_total").register();

    public static final Gauge Node_12_Link_4_packets_received_total = Gauge.build().name("Node_12_Link_4_packets_received_total").help("Node_12_Link_4_packets_received_total").register();

    public static final Gauge Node_13_Link_4_packets_received_total = Gauge.build().name("Node_13_Link_4_packets_received_total").help("Node_13_Link_4_packets_received_total").register();

    public static final Gauge Node_14_Link_4_packets_received_total = Gauge.build().name("Node_14_Link_4_packets_received_total").help("Node_14_Link_4_packets_received_total").register();

    public static final Gauge Node_15_Link_4_packets_received_total = Gauge.build().name("Node_15_Link_4_packets_received_total").help("Node_15_Link_4_packets_received_total").register();

    public static final Gauge Node_16_Link_4_packets_received_total = Gauge.build().name("Node_16_Link_4_packets_received_total").help("Node_16_Link_4_packets_received_total").register();

    public static final Gauge Node_17_Link_4_packets_received_total = Gauge.build().name("Node_17_Link_4_packets_received_total").help("Node_17_Link_4_packets_received_total").register();

    public static final Gauge Node_18_Link_4_packets_received_total = Gauge.build().name("Node_18_Link_4_packets_received_total").help("Node_18_Link_4_packets_received_total").register();

    public static final Gauge Node_19_Link_4_packets_received_total = Gauge.build().name("Node_19_Link_4_packets_received_total").help("Node_19_Link_4_packets_received_total").register();

    public static final Gauge Node_20_Link_4_packets_received_total = Gauge.build().name("Node_20_Link_4_packets_received_total").help("Node_20_Link_4_packets_received_total").register();

//endregion

    //region packets_sent_total

    public static final Gauge Node_1_Link_1_packets_sent_total = Gauge.build().name("Node_1_Link_1_packets_sent_total").help("Node_1_Link_1_packets_sent_total").register();

    public static final Gauge Node_2_Link_1_packets_sent_total = Gauge.build().name("Node_2_Link_1_packets_sent_total").help("Node_2_Link_1_packets_sent_total").register();

    public static final Gauge Node_3_Link_1_packets_sent_total = Gauge.build().name("Node_3_Link_1_packets_sent_total").help("Node_3_Link_1_packets_sent_total").register();

    public static final Gauge Node_4_Link_1_packets_sent_total = Gauge.build().name("Node_4_Link_1_packets_sent_total").help("Node_4_Link_1_packets_sent_total").register();

    public static final Gauge Node_5_Link_1_packets_sent_total = Gauge.build().name("Node_5_Link_1_packets_sent_total").help("Node_5_Link_1_packets_sent_total").register();

    public static final Gauge Node_6_Link_1_packets_sent_total = Gauge.build().name("Node_6_Link_1_packets_sent_total").help("Node_6_Link_1_packets_sent_total").register();

    public static final Gauge Node_7_Link_1_packets_sent_total = Gauge.build().name("Node_7_Link_1_packets_sent_total").help("Node_7_Link_1_packets_sent_total").register();

    public static final Gauge Node_8_Link_1_packets_sent_total = Gauge.build().name("Node_8_Link_1_packets_sent_total").help("Node_8_Link_1_packets_sent_total").register();

    public static final Gauge Node_9_Link_1_packets_sent_total = Gauge.build().name("Node_9_Link_1_packets_sent_total").help("Node_9_Link_1_packets_sent_total").register();

    public static final Gauge Node_10_Link_1_packets_sent_total = Gauge.build().name("Node_10_Link_1_packets_sent_total").help("Node_10_Link_1_packets_sent_total").register();

    public static final Gauge Node_11_Link_1_packets_sent_total = Gauge.build().name("Node_11_Link_1_packets_sent_total").help("Node_11_Link_1_packets_sent_total").register();

    public static final Gauge Node_12_Link_1_packets_sent_total = Gauge.build().name("Node_12_Link_1_packets_sent_total").help("Node_12_Link_1_packets_sent_total").register();

    public static final Gauge Node_13_Link_1_packets_sent_total = Gauge.build().name("Node_13_Link_1_packets_sent_total").help("Node_13_Link_1_packets_sent_total").register();

    public static final Gauge Node_14_Link_1_packets_sent_total = Gauge.build().name("Node_14_Link_1_packets_sent_total").help("Node_14_Link_1_packets_sent_total").register();

    public static final Gauge Node_15_Link_1_packets_sent_total = Gauge.build().name("Node_15_Link_1_packets_sent_total").help("Node_15_Link_1_packets_sent_total").register();

    public static final Gauge Node_16_Link_1_packets_sent_total = Gauge.build().name("Node_16_Link_1_packets_sent_total").help("Node_16_Link_1_packets_sent_total").register();

    public static final Gauge Node_17_Link_1_packets_sent_total = Gauge.build().name("Node_17_Link_1_packets_sent_total").help("Node_17_Link_1_packets_sent_total").register();

    public static final Gauge Node_18_Link_1_packets_sent_total = Gauge.build().name("Node_18_Link_1_packets_sent_total").help("Node_18_Link_1_packets_sent_total").register();

    public static final Gauge Node_19_Link_1_packets_sent_total = Gauge.build().name("Node_19_Link_1_packets_sent_total").help("Node_19_Link_1_packets_sent_total").register();

    public static final Gauge Node_20_Link_1_packets_sent_total = Gauge.build().name("Node_20_Link_1_packets_sent_total").help("Node_20_Link_1_packets_sent_total").register();

    public static final Gauge Node_1_Link_2_packets_sent_total = Gauge.build().name("Node_1_Link_2_packets_sent_total").help("Node_1_Link_2_packets_sent_total").register();

    public static final Gauge Node_2_Link_2_packets_sent_total = Gauge.build().name("Node_2_Link_2_packets_sent_total").help("Node_2_Link_2_packets_sent_total").register();

    public static final Gauge Node_3_Link_2_packets_sent_total = Gauge.build().name("Node_3_Link_2_packets_sent_total").help("Node_3_Link_2_packets_sent_total").register();

    public static final Gauge Node_4_Link_2_packets_sent_total = Gauge.build().name("Node_4_Link_2_packets_sent_total").help("Node_4_Link_2_packets_sent_total").register();

    public static final Gauge Node_5_Link_2_packets_sent_total = Gauge.build().name("Node_5_Link_2_packets_sent_total").help("Node_5_Link_2_packets_sent_total").register();

    public static final Gauge Node_6_Link_2_packets_sent_total = Gauge.build().name("Node_6_Link_2_packets_sent_total").help("Node_6_Link_2_packets_sent_total").register();

    public static final Gauge Node_7_Link_2_packets_sent_total = Gauge.build().name("Node_7_Link_2_packets_sent_total").help("Node_7_Link_2_packets_sent_total").register();

    public static final Gauge Node_8_Link_2_packets_sent_total = Gauge.build().name("Node_8_Link_2_packets_sent_total").help("Node_8_Link_2_packets_sent_total").register();

    public static final Gauge Node_9_Link_2_packets_sent_total = Gauge.build().name("Node_9_Link_2_packets_sent_total").help("Node_9_Link_2_packets_sent_total").register();

    public static final Gauge Node_10_Link_2_packets_sent_total = Gauge.build().name("Node_10_Link_2_packets_sent_total").help("Node_10_Link_2_packets_sent_total").register();

    public static final Gauge Node_11_Link_2_packets_sent_total = Gauge.build().name("Node_11_Link_2_packets_sent_total").help("Node_11_Link_2_packets_sent_total").register();

    public static final Gauge Node_12_Link_2_packets_sent_total = Gauge.build().name("Node_12_Link_2_packets_sent_total").help("Node_12_Link_2_packets_sent_total").register();

    public static final Gauge Node_13_Link_2_packets_sent_total = Gauge.build().name("Node_13_Link_2_packets_sent_total").help("Node_13_Link_2_packets_sent_total").register();

    public static final Gauge Node_14_Link_2_packets_sent_total = Gauge.build().name("Node_14_Link_2_packets_sent_total").help("Node_14_Link_2_packets_sent_total").register();

    public static final Gauge Node_15_Link_2_packets_sent_total = Gauge.build().name("Node_15_Link_2_packets_sent_total").help("Node_15_Link_2_packets_sent_total").register();

    public static final Gauge Node_16_Link_2_packets_sent_total = Gauge.build().name("Node_16_Link_2_packets_sent_total").help("Node_16_Link_2_packets_sent_total").register();

    public static final Gauge Node_17_Link_2_packets_sent_total = Gauge.build().name("Node_17_Link_2_packets_sent_total").help("Node_17_Link_2_packets_sent_total").register();

    public static final Gauge Node_18_Link_2_packets_sent_total = Gauge.build().name("Node_18_Link_2_packets_sent_total").help("Node_18_Link_2_packets_sent_total").register();

    public static final Gauge Node_19_Link_2_packets_sent_total = Gauge.build().name("Node_19_Link_2_packets_sent_total").help("Node_19_Link_2_packets_sent_total").register();

    public static final Gauge Node_20_Link_2_packets_sent_total = Gauge.build().name("Node_20_Link_2_packets_sent_total").help("Node_20_Link_2_packets_sent_total").register();

    public static final Gauge Node_1_Link_3_packets_sent_total = Gauge.build().name("Node_1_Link_3_packets_sent_total").help("Node_1_Link_3_packets_sent_total").register();

    public static final Gauge Node_2_Link_3_packets_sent_total = Gauge.build().name("Node_2_Link_3_packets_sent_total").help("Node_2_Link_3_packets_sent_total").register();

    public static final Gauge Node_3_Link_3_packets_sent_total = Gauge.build().name("Node_3_Link_3_packets_sent_total").help("Node_3_Link_3_packets_sent_total").register();

    public static final Gauge Node_4_Link_3_packets_sent_total = Gauge.build().name("Node_4_Link_3_packets_sent_total").help("Node_4_Link_3_packets_sent_total").register();

    public static final Gauge Node_5_Link_3_packets_sent_total = Gauge.build().name("Node_5_Link_3_packets_sent_total").help("Node_5_Link_3_packets_sent_total").register();

    public static final Gauge Node_6_Link_3_packets_sent_total = Gauge.build().name("Node_6_Link_3_packets_sent_total").help("Node_6_Link_3_packets_sent_total").register();

    public static final Gauge Node_7_Link_3_packets_sent_total = Gauge.build().name("Node_7_Link_3_packets_sent_total").help("Node_7_Link_3_packets_sent_total").register();

    public static final Gauge Node_8_Link_3_packets_sent_total = Gauge.build().name("Node_8_Link_3_packets_sent_total").help("Node_8_Link_3_packets_sent_total").register();

    public static final Gauge Node_9_Link_3_packets_sent_total = Gauge.build().name("Node_9_Link_3_packets_sent_total").help("Node_9_Link_3_packets_sent_total").register();

    public static final Gauge Node_10_Link_3_packets_sent_total = Gauge.build().name("Node_10_Link_3_packets_sent_total").help("Node_10_Link_3_packets_sent_total").register();

    public static final Gauge Node_11_Link_3_packets_sent_total = Gauge.build().name("Node_11_Link_3_packets_sent_total").help("Node_11_Link_3_packets_sent_total").register();

    public static final Gauge Node_12_Link_3_packets_sent_total = Gauge.build().name("Node_12_Link_3_packets_sent_total").help("Node_12_Link_3_packets_sent_total").register();

    public static final Gauge Node_13_Link_3_packets_sent_total = Gauge.build().name("Node_13_Link_3_packets_sent_total").help("Node_13_Link_3_packets_sent_total").register();

    public static final Gauge Node_14_Link_3_packets_sent_total = Gauge.build().name("Node_14_Link_3_packets_sent_total").help("Node_14_Link_3_packets_sent_total").register();

    public static final Gauge Node_15_Link_3_packets_sent_total = Gauge.build().name("Node_15_Link_3_packets_sent_total").help("Node_15_Link_3_packets_sent_total").register();

    public static final Gauge Node_16_Link_3_packets_sent_total = Gauge.build().name("Node_16_Link_3_packets_sent_total").help("Node_16_Link_3_packets_sent_total").register();

    public static final Gauge Node_17_Link_3_packets_sent_total = Gauge.build().name("Node_17_Link_3_packets_sent_total").help("Node_17_Link_3_packets_sent_total").register();

    public static final Gauge Node_18_Link_3_packets_sent_total = Gauge.build().name("Node_18_Link_3_packets_sent_total").help("Node_18_Link_3_packets_sent_total").register();

    public static final Gauge Node_19_Link_3_packets_sent_total = Gauge.build().name("Node_19_Link_3_packets_sent_total").help("Node_19_Link_3_packets_sent_total").register();

    public static final Gauge Node_20_Link_3_packets_sent_total = Gauge.build().name("Node_20_Link_3_packets_sent_total").help("Node_20_Link_3_packets_sent_total").register();

    public static final Gauge Node_1_Link_4_packets_sent_total = Gauge.build().name("Node_1_Link_4_packets_sent_total").help("Node_1_Link_4_packets_sent_total").register();

    public static final Gauge Node_2_Link_4_packets_sent_total = Gauge.build().name("Node_2_Link_4_packets_sent_total").help("Node_2_Link_4_packets_sent_total").register();

    public static final Gauge Node_3_Link_4_packets_sent_total = Gauge.build().name("Node_3_Link_4_packets_sent_total").help("Node_3_Link_4_packets_sent_total").register();

    public static final Gauge Node_4_Link_4_packets_sent_total = Gauge.build().name("Node_4_Link_4_packets_sent_total").help("Node_4_Link_4_packets_sent_total").register();

    public static final Gauge Node_5_Link_4_packets_sent_total = Gauge.build().name("Node_5_Link_4_packets_sent_total").help("Node_5_Link_4_packets_sent_total").register();

    public static final Gauge Node_6_Link_4_packets_sent_total = Gauge.build().name("Node_6_Link_4_packets_sent_total").help("Node_6_Link_4_packets_sent_total").register();

    public static final Gauge Node_7_Link_4_packets_sent_total = Gauge.build().name("Node_7_Link_4_packets_sent_total").help("Node_7_Link_4_packets_sent_total").register();

    public static final Gauge Node_8_Link_4_packets_sent_total = Gauge.build().name("Node_8_Link_4_packets_sent_total").help("Node_8_Link_4_packets_sent_total").register();

    public static final Gauge Node_9_Link_4_packets_sent_total = Gauge.build().name("Node_9_Link_4_packets_sent_total").help("Node_9_Link_4_packets_sent_total").register();

    public static final Gauge Node_10_Link_4_packets_sent_total = Gauge.build().name("Node_10_Link_4_packets_sent_total").help("Node_10_Link_4_packets_sent_total").register();

    public static final Gauge Node_11_Link_4_packets_sent_total = Gauge.build().name("Node_11_Link_4_packets_sent_total").help("Node_11_Link_4_packets_sent_total").register();

    public static final Gauge Node_12_Link_4_packets_sent_total = Gauge.build().name("Node_12_Link_4_packets_sent_total").help("Node_12_Link_4_packets_sent_total").register();

    public static final Gauge Node_13_Link_4_packets_sent_total = Gauge.build().name("Node_13_Link_4_packets_sent_total").help("Node_13_Link_4_packets_sent_total").register();

    public static final Gauge Node_14_Link_4_packets_sent_total = Gauge.build().name("Node_14_Link_4_packets_sent_total").help("Node_14_Link_4_packets_sent_total").register();

    public static final Gauge Node_15_Link_4_packets_sent_total = Gauge.build().name("Node_15_Link_4_packets_sent_total").help("Node_15_Link_4_packets_sent_total").register();

    public static final Gauge Node_16_Link_4_packets_sent_total = Gauge.build().name("Node_16_Link_4_packets_sent_total").help("Node_16_Link_4_packets_sent_total").register();

    public static final Gauge Node_17_Link_4_packets_sent_total = Gauge.build().name("Node_17_Link_4_packets_sent_total").help("Node_17_Link_4_packets_sent_total").register();

    public static final Gauge Node_18_Link_4_packets_sent_total = Gauge.build().name("Node_18_Link_4_packets_sent_total").help("Node_18_Link_4_packets_sent_total").register();

    public static final Gauge Node_19_Link_4_packets_sent_total = Gauge.build().name("Node_19_Link_4_packets_sent_total").help("Node_19_Link_4_packets_sent_total").register();

    public static final Gauge Node_20_Link_4_packets_sent_total = Gauge.build().name("Node_20_Link_4_packets_sent_total").help("Node_20_Link_4_packets_sent_total").register();

//endregion

    //region collision_count

    public static final Gauge Node_1_Link_1_collision_count = Gauge.build().name("Node_1_Link_1_collision_count").help("Node_1_Link_1_collision_count").register();

    public static final Gauge Node_2_Link_1_collision_count = Gauge.build().name("Node_2_Link_1_collision_count").help("Node_2_Link_1_collision_count").register();

    public static final Gauge Node_3_Link_1_collision_count = Gauge.build().name("Node_3_Link_1_collision_count").help("Node_3_Link_1_collision_count").register();

    public static final Gauge Node_4_Link_1_collision_count = Gauge.build().name("Node_4_Link_1_collision_count").help("Node_4_Link_1_collision_count").register();

    public static final Gauge Node_5_Link_1_collision_count = Gauge.build().name("Node_5_Link_1_collision_count").help("Node_5_Link_1_collision_count").register();

    public static final Gauge Node_6_Link_1_collision_count = Gauge.build().name("Node_6_Link_1_collision_count").help("Node_6_Link_1_collision_count").register();

    public static final Gauge Node_7_Link_1_collision_count = Gauge.build().name("Node_7_Link_1_collision_count").help("Node_7_Link_1_collision_count").register();

    public static final Gauge Node_8_Link_1_collision_count = Gauge.build().name("Node_8_Link_1_collision_count").help("Node_8_Link_1_collision_count").register();

    public static final Gauge Node_9_Link_1_collision_count = Gauge.build().name("Node_9_Link_1_collision_count").help("Node_9_Link_1_collision_count").register();

    public static final Gauge Node_10_Link_1_collision_count = Gauge.build().name("Node_10_Link_1_collision_count").help("Node_10_Link_1_collision_count").register();

    public static final Gauge Node_11_Link_1_collision_count = Gauge.build().name("Node_11_Link_1_collision_count").help("Node_11_Link_1_collision_count").register();

    public static final Gauge Node_12_Link_1_collision_count = Gauge.build().name("Node_12_Link_1_collision_count").help("Node_12_Link_1_collision_count").register();

    public static final Gauge Node_13_Link_1_collision_count = Gauge.build().name("Node_13_Link_1_collision_count").help("Node_13_Link_1_collision_count").register();

    public static final Gauge Node_14_Link_1_collision_count = Gauge.build().name("Node_14_Link_1_collision_count").help("Node_14_Link_1_collision_count").register();

    public static final Gauge Node_15_Link_1_collision_count = Gauge.build().name("Node_15_Link_1_collision_count").help("Node_15_Link_1_collision_count").register();

    public static final Gauge Node_16_Link_1_collision_count = Gauge.build().name("Node_16_Link_1_collision_count").help("Node_16_Link_1_collision_count").register();

    public static final Gauge Node_17_Link_1_collision_count = Gauge.build().name("Node_17_Link_1_collision_count").help("Node_17_Link_1_collision_count").register();

    public static final Gauge Node_18_Link_1_collision_count = Gauge.build().name("Node_18_Link_1_collision_count").help("Node_18_Link_1_collision_count").register();

    public static final Gauge Node_19_Link_1_collision_count = Gauge.build().name("Node_19_Link_1_collision_count").help("Node_19_Link_1_collision_count").register();

    public static final Gauge Node_20_Link_1_collision_count = Gauge.build().name("Node_20_Link_1_collision_count").help("Node_20_Link_1_collision_count").register();

    public static final Gauge Node_1_Link_2_collision_count = Gauge.build().name("Node_1_Link_2_collision_count").help("Node_1_Link_2_collision_count").register();

    public static final Gauge Node_2_Link_2_collision_count = Gauge.build().name("Node_2_Link_2_collision_count").help("Node_2_Link_2_collision_count").register();

    public static final Gauge Node_3_Link_2_collision_count = Gauge.build().name("Node_3_Link_2_collision_count").help("Node_3_Link_2_collision_count").register();

    public static final Gauge Node_4_Link_2_collision_count = Gauge.build().name("Node_4_Link_2_collision_count").help("Node_4_Link_2_collision_count").register();

    public static final Gauge Node_5_Link_2_collision_count = Gauge.build().name("Node_5_Link_2_collision_count").help("Node_5_Link_2_collision_count").register();

    public static final Gauge Node_6_Link_2_collision_count = Gauge.build().name("Node_6_Link_2_collision_count").help("Node_6_Link_2_collision_count").register();

    public static final Gauge Node_7_Link_2_collision_count = Gauge.build().name("Node_7_Link_2_collision_count").help("Node_7_Link_2_collision_count").register();

    public static final Gauge Node_8_Link_2_collision_count = Gauge.build().name("Node_8_Link_2_collision_count").help("Node_8_Link_2_collision_count").register();

    public static final Gauge Node_9_Link_2_collision_count = Gauge.build().name("Node_9_Link_2_collision_count").help("Node_9_Link_2_collision_count").register();

    public static final Gauge Node_10_Link_2_collision_count = Gauge.build().name("Node_10_Link_2_collision_count").help("Node_10_Link_2_collision_count").register();

    public static final Gauge Node_11_Link_2_collision_count = Gauge.build().name("Node_11_Link_2_collision_count").help("Node_11_Link_2_collision_count").register();

    public static final Gauge Node_12_Link_2_collision_count = Gauge.build().name("Node_12_Link_2_collision_count").help("Node_12_Link_2_collision_count").register();

    public static final Gauge Node_13_Link_2_collision_count = Gauge.build().name("Node_13_Link_2_collision_count").help("Node_13_Link_2_collision_count").register();

    public static final Gauge Node_14_Link_2_collision_count = Gauge.build().name("Node_14_Link_2_collision_count").help("Node_14_Link_2_collision_count").register();

    public static final Gauge Node_15_Link_2_collision_count = Gauge.build().name("Node_15_Link_2_collision_count").help("Node_15_Link_2_collision_count").register();

    public static final Gauge Node_16_Link_2_collision_count = Gauge.build().name("Node_16_Link_2_collision_count").help("Node_16_Link_2_collision_count").register();

    public static final Gauge Node_17_Link_2_collision_count = Gauge.build().name("Node_17_Link_2_collision_count").help("Node_17_Link_2_collision_count").register();

    public static final Gauge Node_18_Link_2_collision_count = Gauge.build().name("Node_18_Link_2_collision_count").help("Node_18_Link_2_collision_count").register();

    public static final Gauge Node_19_Link_2_collision_count = Gauge.build().name("Node_19_Link_2_collision_count").help("Node_19_Link_2_collision_count").register();

    public static final Gauge Node_20_Link_2_collision_count = Gauge.build().name("Node_20_Link_2_collision_count").help("Node_20_Link_2_collision_count").register();

    public static final Gauge Node_1_Link_3_collision_count = Gauge.build().name("Node_1_Link_3_collision_count").help("Node_1_Link_3_collision_count").register();

    public static final Gauge Node_2_Link_3_collision_count = Gauge.build().name("Node_2_Link_3_collision_count").help("Node_2_Link_3_collision_count").register();

    public static final Gauge Node_3_Link_3_collision_count = Gauge.build().name("Node_3_Link_3_collision_count").help("Node_3_Link_3_collision_count").register();

    public static final Gauge Node_4_Link_3_collision_count = Gauge.build().name("Node_4_Link_3_collision_count").help("Node_4_Link_3_collision_count").register();

    public static final Gauge Node_5_Link_3_collision_count = Gauge.build().name("Node_5_Link_3_collision_count").help("Node_5_Link_3_collision_count").register();

    public static final Gauge Node_6_Link_3_collision_count = Gauge.build().name("Node_6_Link_3_collision_count").help("Node_6_Link_3_collision_count").register();

    public static final Gauge Node_7_Link_3_collision_count = Gauge.build().name("Node_7_Link_3_collision_count").help("Node_7_Link_3_collision_count").register();

    public static final Gauge Node_8_Link_3_collision_count = Gauge.build().name("Node_8_Link_3_collision_count").help("Node_8_Link_3_collision_count").register();

    public static final Gauge Node_9_Link_3_collision_count = Gauge.build().name("Node_9_Link_3_collision_count").help("Node_9_Link_3_collision_count").register();

    public static final Gauge Node_10_Link_3_collision_count = Gauge.build().name("Node_10_Link_3_collision_count").help("Node_10_Link_3_collision_count").register();

    public static final Gauge Node_11_Link_3_collision_count = Gauge.build().name("Node_11_Link_3_collision_count").help("Node_11_Link_3_collision_count").register();

    public static final Gauge Node_12_Link_3_collision_count = Gauge.build().name("Node_12_Link_3_collision_count").help("Node_12_Link_3_collision_count").register();

    public static final Gauge Node_13_Link_3_collision_count = Gauge.build().name("Node_13_Link_3_collision_count").help("Node_13_Link_3_collision_count").register();

    public static final Gauge Node_14_Link_3_collision_count = Gauge.build().name("Node_14_Link_3_collision_count").help("Node_14_Link_3_collision_count").register();

    public static final Gauge Node_15_Link_3_collision_count = Gauge.build().name("Node_15_Link_3_collision_count").help("Node_15_Link_3_collision_count").register();

    public static final Gauge Node_16_Link_3_collision_count = Gauge.build().name("Node_16_Link_3_collision_count").help("Node_16_Link_3_collision_count").register();

    public static final Gauge Node_17_Link_3_collision_count = Gauge.build().name("Node_17_Link_3_collision_count").help("Node_17_Link_3_collision_count").register();

    public static final Gauge Node_18_Link_3_collision_count = Gauge.build().name("Node_18_Link_3_collision_count").help("Node_18_Link_3_collision_count").register();

    public static final Gauge Node_19_Link_3_collision_count = Gauge.build().name("Node_19_Link_3_collision_count").help("Node_19_Link_3_collision_count").register();

    public static final Gauge Node_20_Link_3_collision_count = Gauge.build().name("Node_20_Link_3_collision_count").help("Node_20_Link_3_collision_count").register();

    public static final Gauge Node_1_Link_4_collision_count = Gauge.build().name("Node_1_Link_4_collision_count").help("Node_1_Link_4_collision_count").register();

    public static final Gauge Node_2_Link_4_collision_count = Gauge.build().name("Node_2_Link_4_collision_count").help("Node_2_Link_4_collision_count").register();

    public static final Gauge Node_3_Link_4_collision_count = Gauge.build().name("Node_3_Link_4_collision_count").help("Node_3_Link_4_collision_count").register();

    public static final Gauge Node_4_Link_4_collision_count = Gauge.build().name("Node_4_Link_4_collision_count").help("Node_4_Link_4_collision_count").register();

    public static final Gauge Node_5_Link_4_collision_count = Gauge.build().name("Node_5_Link_4_collision_count").help("Node_5_Link_4_collision_count").register();

    public static final Gauge Node_6_Link_4_collision_count = Gauge.build().name("Node_6_Link_4_collision_count").help("Node_6_Link_4_collision_count").register();

    public static final Gauge Node_7_Link_4_collision_count = Gauge.build().name("Node_7_Link_4_collision_count").help("Node_7_Link_4_collision_count").register();

    public static final Gauge Node_8_Link_4_collision_count = Gauge.build().name("Node_8_Link_4_collision_count").help("Node_8_Link_4_collision_count").register();

    public static final Gauge Node_9_Link_4_collision_count = Gauge.build().name("Node_9_Link_4_collision_count").help("Node_9_Link_4_collision_count").register();

    public static final Gauge Node_10_Link_4_collision_count = Gauge.build().name("Node_10_Link_4_collision_count").help("Node_10_Link_4_collision_count").register();

    public static final Gauge Node_11_Link_4_collision_count = Gauge.build().name("Node_11_Link_4_collision_count").help("Node_11_Link_4_collision_count").register();

    public static final Gauge Node_12_Link_4_collision_count = Gauge.build().name("Node_12_Link_4_collision_count").help("Node_12_Link_4_collision_count").register();

    public static final Gauge Node_13_Link_4_collision_count = Gauge.build().name("Node_13_Link_4_collision_count").help("Node_13_Link_4_collision_count").register();

    public static final Gauge Node_14_Link_4_collision_count = Gauge.build().name("Node_14_Link_4_collision_count").help("Node_14_Link_4_collision_count").register();

    public static final Gauge Node_15_Link_4_collision_count = Gauge.build().name("Node_15_Link_4_collision_count").help("Node_15_Link_4_collision_count").register();

    public static final Gauge Node_16_Link_4_collision_count = Gauge.build().name("Node_16_Link_4_collision_count").help("Node_16_Link_4_collision_count").register();

    public static final Gauge Node_17_Link_4_collision_count = Gauge.build().name("Node_17_Link_4_collision_count").help("Node_17_Link_4_collision_count").register();

    public static final Gauge Node_18_Link_4_collision_count = Gauge.build().name("Node_18_Link_4_collision_count").help("Node_18_Link_4_collision_count").register();

    public static final Gauge Node_19_Link_4_collision_count = Gauge.build().name("Node_19_Link_4_collision_count").help("Node_19_Link_4_collision_count").register();

    public static final Gauge Node_20_Link_4_collision_count = Gauge.build().name("Node_20_Link_4_collision_count").help("Node_20_Link_4_collision_count").register();

//endregion

    //region bytes_received_total

    public static final Gauge Node_1_Link_1_bytes_received_total = Gauge.build().name("Node_1_Link_1_bytes_received_total").help("Node_1_Link_1_bytes_received_total").register();

    public static final Gauge Node_2_Link_1_bytes_received_total = Gauge.build().name("Node_2_Link_1_bytes_received_total").help("Node_2_Link_1_bytes_received_total").register();

    public static final Gauge Node_3_Link_1_bytes_received_total = Gauge.build().name("Node_3_Link_1_bytes_received_total").help("Node_3_Link_1_bytes_received_total").register();

    public static final Gauge Node_4_Link_1_bytes_received_total = Gauge.build().name("Node_4_Link_1_bytes_received_total").help("Node_4_Link_1_bytes_received_total").register();

    public static final Gauge Node_5_Link_1_bytes_received_total = Gauge.build().name("Node_5_Link_1_bytes_received_total").help("Node_5_Link_1_bytes_received_total").register();

    public static final Gauge Node_6_Link_1_bytes_received_total = Gauge.build().name("Node_6_Link_1_bytes_received_total").help("Node_6_Link_1_bytes_received_total").register();

    public static final Gauge Node_7_Link_1_bytes_received_total = Gauge.build().name("Node_7_Link_1_bytes_received_total").help("Node_7_Link_1_bytes_received_total").register();

    public static final Gauge Node_8_Link_1_bytes_received_total = Gauge.build().name("Node_8_Link_1_bytes_received_total").help("Node_8_Link_1_bytes_received_total").register();

    public static final Gauge Node_9_Link_1_bytes_received_total = Gauge.build().name("Node_9_Link_1_bytes_received_total").help("Node_9_Link_1_bytes_received_total").register();

    public static final Gauge Node_10_Link_1_bytes_received_total = Gauge.build().name("Node_10_Link_1_bytes_received_total").help("Node_10_Link_1_bytes_received_total").register();

    public static final Gauge Node_11_Link_1_bytes_received_total = Gauge.build().name("Node_11_Link_1_bytes_received_total").help("Node_11_Link_1_bytes_received_total").register();

    public static final Gauge Node_12_Link_1_bytes_received_total = Gauge.build().name("Node_12_Link_1_bytes_received_total").help("Node_12_Link_1_bytes_received_total").register();

    public static final Gauge Node_13_Link_1_bytes_received_total = Gauge.build().name("Node_13_Link_1_bytes_received_total").help("Node_13_Link_1_bytes_received_total").register();

    public static final Gauge Node_14_Link_1_bytes_received_total = Gauge.build().name("Node_14_Link_1_bytes_received_total").help("Node_14_Link_1_bytes_received_total").register();

    public static final Gauge Node_15_Link_1_bytes_received_total = Gauge.build().name("Node_15_Link_1_bytes_received_total").help("Node_15_Link_1_bytes_received_total").register();

    public static final Gauge Node_16_Link_1_bytes_received_total = Gauge.build().name("Node_16_Link_1_bytes_received_total").help("Node_16_Link_1_bytes_received_total").register();

    public static final Gauge Node_17_Link_1_bytes_received_total = Gauge.build().name("Node_17_Link_1_bytes_received_total").help("Node_17_Link_1_bytes_received_total").register();

    public static final Gauge Node_18_Link_1_bytes_received_total = Gauge.build().name("Node_18_Link_1_bytes_received_total").help("Node_18_Link_1_bytes_received_total").register();

    public static final Gauge Node_19_Link_1_bytes_received_total = Gauge.build().name("Node_19_Link_1_bytes_received_total").help("Node_19_Link_1_bytes_received_total").register();

    public static final Gauge Node_20_Link_1_bytes_received_total = Gauge.build().name("Node_20_Link_1_bytes_received_total").help("Node_20_Link_1_bytes_received_total").register();

    public static final Gauge Node_1_Link_2_bytes_received_total = Gauge.build().name("Node_1_Link_2_bytes_received_total").help("Node_1_Link_2_bytes_received_total").register();

    public static final Gauge Node_2_Link_2_bytes_received_total = Gauge.build().name("Node_2_Link_2_bytes_received_total").help("Node_2_Link_2_bytes_received_total").register();

    public static final Gauge Node_3_Link_2_bytes_received_total = Gauge.build().name("Node_3_Link_2_bytes_received_total").help("Node_3_Link_2_bytes_received_total").register();

    public static final Gauge Node_4_Link_2_bytes_received_total = Gauge.build().name("Node_4_Link_2_bytes_received_total").help("Node_4_Link_2_bytes_received_total").register();

    public static final Gauge Node_5_Link_2_bytes_received_total = Gauge.build().name("Node_5_Link_2_bytes_received_total").help("Node_5_Link_2_bytes_received_total").register();

    public static final Gauge Node_6_Link_2_bytes_received_total = Gauge.build().name("Node_6_Link_2_bytes_received_total").help("Node_6_Link_2_bytes_received_total").register();

    public static final Gauge Node_7_Link_2_bytes_received_total = Gauge.build().name("Node_7_Link_2_bytes_received_total").help("Node_7_Link_2_bytes_received_total").register();

    public static final Gauge Node_8_Link_2_bytes_received_total = Gauge.build().name("Node_8_Link_2_bytes_received_total").help("Node_8_Link_2_bytes_received_total").register();

    public static final Gauge Node_9_Link_2_bytes_received_total = Gauge.build().name("Node_9_Link_2_bytes_received_total").help("Node_9_Link_2_bytes_received_total").register();

    public static final Gauge Node_10_Link_2_bytes_received_total = Gauge.build().name("Node_10_Link_2_bytes_received_total").help("Node_10_Link_2_bytes_received_total").register();

    public static final Gauge Node_11_Link_2_bytes_received_total = Gauge.build().name("Node_11_Link_2_bytes_received_total").help("Node_11_Link_2_bytes_received_total").register();

    public static final Gauge Node_12_Link_2_bytes_received_total = Gauge.build().name("Node_12_Link_2_bytes_received_total").help("Node_12_Link_2_bytes_received_total").register();

    public static final Gauge Node_13_Link_2_bytes_received_total = Gauge.build().name("Node_13_Link_2_bytes_received_total").help("Node_13_Link_2_bytes_received_total").register();

    public static final Gauge Node_14_Link_2_bytes_received_total = Gauge.build().name("Node_14_Link_2_bytes_received_total").help("Node_14_Link_2_bytes_received_total").register();

    public static final Gauge Node_15_Link_2_bytes_received_total = Gauge.build().name("Node_15_Link_2_bytes_received_total").help("Node_15_Link_2_bytes_received_total").register();

    public static final Gauge Node_16_Link_2_bytes_received_total = Gauge.build().name("Node_16_Link_2_bytes_received_total").help("Node_16_Link_2_bytes_received_total").register();

    public static final Gauge Node_17_Link_2_bytes_received_total = Gauge.build().name("Node_17_Link_2_bytes_received_total").help("Node_17_Link_2_bytes_received_total").register();

    public static final Gauge Node_18_Link_2_bytes_received_total = Gauge.build().name("Node_18_Link_2_bytes_received_total").help("Node_18_Link_2_bytes_received_total").register();

    public static final Gauge Node_19_Link_2_bytes_received_total = Gauge.build().name("Node_19_Link_2_bytes_received_total").help("Node_19_Link_2_bytes_received_total").register();

    public static final Gauge Node_20_Link_2_bytes_received_total = Gauge.build().name("Node_20_Link_2_bytes_received_total").help("Node_20_Link_2_bytes_received_total").register();

    public static final Gauge Node_1_Link_3_bytes_received_total = Gauge.build().name("Node_1_Link_3_bytes_received_total").help("Node_1_Link_3_bytes_received_total").register();

    public static final Gauge Node_2_Link_3_bytes_received_total = Gauge.build().name("Node_2_Link_3_bytes_received_total").help("Node_2_Link_3_bytes_received_total").register();

    public static final Gauge Node_3_Link_3_bytes_received_total = Gauge.build().name("Node_3_Link_3_bytes_received_total").help("Node_3_Link_3_bytes_received_total").register();

    public static final Gauge Node_4_Link_3_bytes_received_total = Gauge.build().name("Node_4_Link_3_bytes_received_total").help("Node_4_Link_3_bytes_received_total").register();

    public static final Gauge Node_5_Link_3_bytes_received_total = Gauge.build().name("Node_5_Link_3_bytes_received_total").help("Node_5_Link_3_bytes_received_total").register();

    public static final Gauge Node_6_Link_3_bytes_received_total = Gauge.build().name("Node_6_Link_3_bytes_received_total").help("Node_6_Link_3_bytes_received_total").register();

    public static final Gauge Node_7_Link_3_bytes_received_total = Gauge.build().name("Node_7_Link_3_bytes_received_total").help("Node_7_Link_3_bytes_received_total").register();

    public static final Gauge Node_8_Link_3_bytes_received_total = Gauge.build().name("Node_8_Link_3_bytes_received_total").help("Node_8_Link_3_bytes_received_total").register();

    public static final Gauge Node_9_Link_3_bytes_received_total = Gauge.build().name("Node_9_Link_3_bytes_received_total").help("Node_9_Link_3_bytes_received_total").register();

    public static final Gauge Node_10_Link_3_bytes_received_total = Gauge.build().name("Node_10_Link_3_bytes_received_total").help("Node_10_Link_3_bytes_received_total").register();

    public static final Gauge Node_11_Link_3_bytes_received_total = Gauge.build().name("Node_11_Link_3_bytes_received_total").help("Node_11_Link_3_bytes_received_total").register();

    public static final Gauge Node_12_Link_3_bytes_received_total = Gauge.build().name("Node_12_Link_3_bytes_received_total").help("Node_12_Link_3_bytes_received_total").register();

    public static final Gauge Node_13_Link_3_bytes_received_total = Gauge.build().name("Node_13_Link_3_bytes_received_total").help("Node_13_Link_3_bytes_received_total").register();

    public static final Gauge Node_14_Link_3_bytes_received_total = Gauge.build().name("Node_14_Link_3_bytes_received_total").help("Node_14_Link_3_bytes_received_total").register();

    public static final Gauge Node_15_Link_3_bytes_received_total = Gauge.build().name("Node_15_Link_3_bytes_received_total").help("Node_15_Link_3_bytes_received_total").register();

    public static final Gauge Node_16_Link_3_bytes_received_total = Gauge.build().name("Node_16_Link_3_bytes_received_total").help("Node_16_Link_3_bytes_received_total").register();

    public static final Gauge Node_17_Link_3_bytes_received_total = Gauge.build().name("Node_17_Link_3_bytes_received_total").help("Node_17_Link_3_bytes_received_total").register();

    public static final Gauge Node_18_Link_3_bytes_received_total = Gauge.build().name("Node_18_Link_3_bytes_received_total").help("Node_18_Link_3_bytes_received_total").register();

    public static final Gauge Node_19_Link_3_bytes_received_total = Gauge.build().name("Node_19_Link_3_bytes_received_total").help("Node_19_Link_3_bytes_received_total").register();

    public static final Gauge Node_20_Link_3_bytes_received_total = Gauge.build().name("Node_20_Link_3_bytes_received_total").help("Node_20_Link_3_bytes_received_total").register();

    public static final Gauge Node_1_Link_4_bytes_received_total = Gauge.build().name("Node_1_Link_4_bytes_received_total").help("Node_1_Link_4_bytes_received_total").register();

    public static final Gauge Node_2_Link_4_bytes_received_total = Gauge.build().name("Node_2_Link_4_bytes_received_total").help("Node_2_Link_4_bytes_received_total").register();

    public static final Gauge Node_3_Link_4_bytes_received_total = Gauge.build().name("Node_3_Link_4_bytes_received_total").help("Node_3_Link_4_bytes_received_total").register();

    public static final Gauge Node_4_Link_4_bytes_received_total = Gauge.build().name("Node_4_Link_4_bytes_received_total").help("Node_4_Link_4_bytes_received_total").register();

    public static final Gauge Node_5_Link_4_bytes_received_total = Gauge.build().name("Node_5_Link_4_bytes_received_total").help("Node_5_Link_4_bytes_received_total").register();

    public static final Gauge Node_6_Link_4_bytes_received_total = Gauge.build().name("Node_6_Link_4_bytes_received_total").help("Node_6_Link_4_bytes_received_total").register();

    public static final Gauge Node_7_Link_4_bytes_received_total = Gauge.build().name("Node_7_Link_4_bytes_received_total").help("Node_7_Link_4_bytes_received_total").register();

    public static final Gauge Node_8_Link_4_bytes_received_total = Gauge.build().name("Node_8_Link_4_bytes_received_total").help("Node_8_Link_4_bytes_received_total").register();

    public static final Gauge Node_9_Link_4_bytes_received_total = Gauge.build().name("Node_9_Link_4_bytes_received_total").help("Node_9_Link_4_bytes_received_total").register();

    public static final Gauge Node_10_Link_4_bytes_received_total = Gauge.build().name("Node_10_Link_4_bytes_received_total").help("Node_10_Link_4_bytes_received_total").register();

    public static final Gauge Node_11_Link_4_bytes_received_total = Gauge.build().name("Node_11_Link_4_bytes_received_total").help("Node_11_Link_4_bytes_received_total").register();

    public static final Gauge Node_12_Link_4_bytes_received_total = Gauge.build().name("Node_12_Link_4_bytes_received_total").help("Node_12_Link_4_bytes_received_total").register();

    public static final Gauge Node_13_Link_4_bytes_received_total = Gauge.build().name("Node_13_Link_4_bytes_received_total").help("Node_13_Link_4_bytes_received_total").register();

    public static final Gauge Node_14_Link_4_bytes_received_total = Gauge.build().name("Node_14_Link_4_bytes_received_total").help("Node_14_Link_4_bytes_received_total").register();

    public static final Gauge Node_15_Link_4_bytes_received_total = Gauge.build().name("Node_15_Link_4_bytes_received_total").help("Node_15_Link_4_bytes_received_total").register();

    public static final Gauge Node_16_Link_4_bytes_received_total = Gauge.build().name("Node_16_Link_4_bytes_received_total").help("Node_16_Link_4_bytes_received_total").register();

    public static final Gauge Node_17_Link_4_bytes_received_total = Gauge.build().name("Node_17_Link_4_bytes_received_total").help("Node_17_Link_4_bytes_received_total").register();

    public static final Gauge Node_18_Link_4_bytes_received_total = Gauge.build().name("Node_18_Link_4_bytes_received_total").help("Node_18_Link_4_bytes_received_total").register();

    public static final Gauge Node_19_Link_4_bytes_received_total = Gauge.build().name("Node_19_Link_4_bytes_received_total").help("Node_19_Link_4_bytes_received_total").register();

    public static final Gauge Node_20_Link_4_bytes_received_total = Gauge.build().name("Node_20_Link_4_bytes_received_total").help("Node_20_Link_4_bytes_received_total").register();

//endregion

    //region bytes_sent_total

    public static final Gauge Node_1_Link_1_bytes_sent_total = Gauge.build().name("Node_1_Link_1_bytes_sent_total").help("Node_1_Link_1_bytes_sent_total").register();

    public static final Gauge Node_2_Link_1_bytes_sent_total = Gauge.build().name("Node_2_Link_1_bytes_sent_total").help("Node_2_Link_1_bytes_sent_total").register();

    public static final Gauge Node_3_Link_1_bytes_sent_total = Gauge.build().name("Node_3_Link_1_bytes_sent_total").help("Node_3_Link_1_bytes_sent_total").register();

    public static final Gauge Node_4_Link_1_bytes_sent_total = Gauge.build().name("Node_4_Link_1_bytes_sent_total").help("Node_4_Link_1_bytes_sent_total").register();

    public static final Gauge Node_5_Link_1_bytes_sent_total = Gauge.build().name("Node_5_Link_1_bytes_sent_total").help("Node_5_Link_1_bytes_sent_total").register();

    public static final Gauge Node_6_Link_1_bytes_sent_total = Gauge.build().name("Node_6_Link_1_bytes_sent_total").help("Node_6_Link_1_bytes_sent_total").register();

    public static final Gauge Node_7_Link_1_bytes_sent_total = Gauge.build().name("Node_7_Link_1_bytes_sent_total").help("Node_7_Link_1_bytes_sent_total").register();

    public static final Gauge Node_8_Link_1_bytes_sent_total = Gauge.build().name("Node_8_Link_1_bytes_sent_total").help("Node_8_Link_1_bytes_sent_total").register();

    public static final Gauge Node_9_Link_1_bytes_sent_total = Gauge.build().name("Node_9_Link_1_bytes_sent_total").help("Node_9_Link_1_bytes_sent_total").register();

    public static final Gauge Node_10_Link_1_bytes_sent_total = Gauge.build().name("Node_10_Link_1_bytes_sent_total").help("Node_10_Link_1_bytes_sent_total").register();

    public static final Gauge Node_11_Link_1_bytes_sent_total = Gauge.build().name("Node_11_Link_1_bytes_sent_total").help("Node_11_Link_1_bytes_sent_total").register();

    public static final Gauge Node_12_Link_1_bytes_sent_total = Gauge.build().name("Node_12_Link_1_bytes_sent_total").help("Node_12_Link_1_bytes_sent_total").register();

    public static final Gauge Node_13_Link_1_bytes_sent_total = Gauge.build().name("Node_13_Link_1_bytes_sent_total").help("Node_13_Link_1_bytes_sent_total").register();

    public static final Gauge Node_14_Link_1_bytes_sent_total = Gauge.build().name("Node_14_Link_1_bytes_sent_total").help("Node_14_Link_1_bytes_sent_total").register();

    public static final Gauge Node_15_Link_1_bytes_sent_total = Gauge.build().name("Node_15_Link_1_bytes_sent_total").help("Node_15_Link_1_bytes_sent_total").register();

    public static final Gauge Node_16_Link_1_bytes_sent_total = Gauge.build().name("Node_16_Link_1_bytes_sent_total").help("Node_16_Link_1_bytes_sent_total").register();

    public static final Gauge Node_17_Link_1_bytes_sent_total = Gauge.build().name("Node_17_Link_1_bytes_sent_total").help("Node_17_Link_1_bytes_sent_total").register();

    public static final Gauge Node_18_Link_1_bytes_sent_total = Gauge.build().name("Node_18_Link_1_bytes_sent_total").help("Node_18_Link_1_bytes_sent_total").register();

    public static final Gauge Node_19_Link_1_bytes_sent_total = Gauge.build().name("Node_19_Link_1_bytes_sent_total").help("Node_19_Link_1_bytes_sent_total").register();

    public static final Gauge Node_20_Link_1_bytes_sent_total = Gauge.build().name("Node_20_Link_1_bytes_sent_total").help("Node_20_Link_1_bytes_sent_total").register();

    public static final Gauge Node_1_Link_2_bytes_sent_total = Gauge.build().name("Node_1_Link_2_bytes_sent_total").help("Node_1_Link_2_bytes_sent_total").register();

    public static final Gauge Node_2_Link_2_bytes_sent_total = Gauge.build().name("Node_2_Link_2_bytes_sent_total").help("Node_2_Link_2_bytes_sent_total").register();

    public static final Gauge Node_3_Link_2_bytes_sent_total = Gauge.build().name("Node_3_Link_2_bytes_sent_total").help("Node_3_Link_2_bytes_sent_total").register();

    public static final Gauge Node_4_Link_2_bytes_sent_total = Gauge.build().name("Node_4_Link_2_bytes_sent_total").help("Node_4_Link_2_bytes_sent_total").register();

    public static final Gauge Node_5_Link_2_bytes_sent_total = Gauge.build().name("Node_5_Link_2_bytes_sent_total").help("Node_5_Link_2_bytes_sent_total").register();

    public static final Gauge Node_6_Link_2_bytes_sent_total = Gauge.build().name("Node_6_Link_2_bytes_sent_total").help("Node_6_Link_2_bytes_sent_total").register();

    public static final Gauge Node_7_Link_2_bytes_sent_total = Gauge.build().name("Node_7_Link_2_bytes_sent_total").help("Node_7_Link_2_bytes_sent_total").register();

    public static final Gauge Node_8_Link_2_bytes_sent_total = Gauge.build().name("Node_8_Link_2_bytes_sent_total").help("Node_8_Link_2_bytes_sent_total").register();

    public static final Gauge Node_9_Link_2_bytes_sent_total = Gauge.build().name("Node_9_Link_2_bytes_sent_total").help("Node_9_Link_2_bytes_sent_total").register();

    public static final Gauge Node_10_Link_2_bytes_sent_total = Gauge.build().name("Node_10_Link_2_bytes_sent_total").help("Node_10_Link_2_bytes_sent_total").register();

    public static final Gauge Node_11_Link_2_bytes_sent_total = Gauge.build().name("Node_11_Link_2_bytes_sent_total").help("Node_11_Link_2_bytes_sent_total").register();

    public static final Gauge Node_12_Link_2_bytes_sent_total = Gauge.build().name("Node_12_Link_2_bytes_sent_total").help("Node_12_Link_2_bytes_sent_total").register();

    public static final Gauge Node_13_Link_2_bytes_sent_total = Gauge.build().name("Node_13_Link_2_bytes_sent_total").help("Node_13_Link_2_bytes_sent_total").register();

    public static final Gauge Node_14_Link_2_bytes_sent_total = Gauge.build().name("Node_14_Link_2_bytes_sent_total").help("Node_14_Link_2_bytes_sent_total").register();

    public static final Gauge Node_15_Link_2_bytes_sent_total = Gauge.build().name("Node_15_Link_2_bytes_sent_total").help("Node_15_Link_2_bytes_sent_total").register();

    public static final Gauge Node_16_Link_2_bytes_sent_total = Gauge.build().name("Node_16_Link_2_bytes_sent_total").help("Node_16_Link_2_bytes_sent_total").register();

    public static final Gauge Node_17_Link_2_bytes_sent_total = Gauge.build().name("Node_17_Link_2_bytes_sent_total").help("Node_17_Link_2_bytes_sent_total").register();

    public static final Gauge Node_18_Link_2_bytes_sent_total = Gauge.build().name("Node_18_Link_2_bytes_sent_total").help("Node_18_Link_2_bytes_sent_total").register();

    public static final Gauge Node_19_Link_2_bytes_sent_total = Gauge.build().name("Node_19_Link_2_bytes_sent_total").help("Node_19_Link_2_bytes_sent_total").register();

    public static final Gauge Node_20_Link_2_bytes_sent_total = Gauge.build().name("Node_20_Link_2_bytes_sent_total").help("Node_20_Link_2_bytes_sent_total").register();

    public static final Gauge Node_1_Link_3_bytes_sent_total = Gauge.build().name("Node_1_Link_3_bytes_sent_total").help("Node_1_Link_3_bytes_sent_total").register();

    public static final Gauge Node_2_Link_3_bytes_sent_total = Gauge.build().name("Node_2_Link_3_bytes_sent_total").help("Node_2_Link_3_bytes_sent_total").register();

    public static final Gauge Node_3_Link_3_bytes_sent_total = Gauge.build().name("Node_3_Link_3_bytes_sent_total").help("Node_3_Link_3_bytes_sent_total").register();

    public static final Gauge Node_4_Link_3_bytes_sent_total = Gauge.build().name("Node_4_Link_3_bytes_sent_total").help("Node_4_Link_3_bytes_sent_total").register();

    public static final Gauge Node_5_Link_3_bytes_sent_total = Gauge.build().name("Node_5_Link_3_bytes_sent_total").help("Node_5_Link_3_bytes_sent_total").register();

    public static final Gauge Node_6_Link_3_bytes_sent_total = Gauge.build().name("Node_6_Link_3_bytes_sent_total").help("Node_6_Link_3_bytes_sent_total").register();

    public static final Gauge Node_7_Link_3_bytes_sent_total = Gauge.build().name("Node_7_Link_3_bytes_sent_total").help("Node_7_Link_3_bytes_sent_total").register();

    public static final Gauge Node_8_Link_3_bytes_sent_total = Gauge.build().name("Node_8_Link_3_bytes_sent_total").help("Node_8_Link_3_bytes_sent_total").register();

    public static final Gauge Node_9_Link_3_bytes_sent_total = Gauge.build().name("Node_9_Link_3_bytes_sent_total").help("Node_9_Link_3_bytes_sent_total").register();

    public static final Gauge Node_10_Link_3_bytes_sent_total = Gauge.build().name("Node_10_Link_3_bytes_sent_total").help("Node_10_Link_3_bytes_sent_total").register();

    public static final Gauge Node_11_Link_3_bytes_sent_total = Gauge.build().name("Node_11_Link_3_bytes_sent_total").help("Node_11_Link_3_bytes_sent_total").register();

    public static final Gauge Node_12_Link_3_bytes_sent_total = Gauge.build().name("Node_12_Link_3_bytes_sent_total").help("Node_12_Link_3_bytes_sent_total").register();

    public static final Gauge Node_13_Link_3_bytes_sent_total = Gauge.build().name("Node_13_Link_3_bytes_sent_total").help("Node_13_Link_3_bytes_sent_total").register();

    public static final Gauge Node_14_Link_3_bytes_sent_total = Gauge.build().name("Node_14_Link_3_bytes_sent_total").help("Node_14_Link_3_bytes_sent_total").register();

    public static final Gauge Node_15_Link_3_bytes_sent_total = Gauge.build().name("Node_15_Link_3_bytes_sent_total").help("Node_15_Link_3_bytes_sent_total").register();

    public static final Gauge Node_16_Link_3_bytes_sent_total = Gauge.build().name("Node_16_Link_3_bytes_sent_total").help("Node_16_Link_3_bytes_sent_total").register();

    public static final Gauge Node_17_Link_3_bytes_sent_total = Gauge.build().name("Node_17_Link_3_bytes_sent_total").help("Node_17_Link_3_bytes_sent_total").register();

    public static final Gauge Node_18_Link_3_bytes_sent_total = Gauge.build().name("Node_18_Link_3_bytes_sent_total").help("Node_18_Link_3_bytes_sent_total").register();

    public static final Gauge Node_19_Link_3_bytes_sent_total = Gauge.build().name("Node_19_Link_3_bytes_sent_total").help("Node_19_Link_3_bytes_sent_total").register();

    public static final Gauge Node_20_Link_3_bytes_sent_total = Gauge.build().name("Node_20_Link_3_bytes_sent_total").help("Node_20_Link_3_bytes_sent_total").register();

    public static final Gauge Node_1_Link_4_bytes_sent_total = Gauge.build().name("Node_1_Link_4_bytes_sent_total").help("Node_1_Link_4_bytes_sent_total").register();

    public static final Gauge Node_2_Link_4_bytes_sent_total = Gauge.build().name("Node_2_Link_4_bytes_sent_total").help("Node_2_Link_4_bytes_sent_total").register();

    public static final Gauge Node_3_Link_4_bytes_sent_total = Gauge.build().name("Node_3_Link_4_bytes_sent_total").help("Node_3_Link_4_bytes_sent_total").register();

    public static final Gauge Node_4_Link_4_bytes_sent_total = Gauge.build().name("Node_4_Link_4_bytes_sent_total").help("Node_4_Link_4_bytes_sent_total").register();

    public static final Gauge Node_5_Link_4_bytes_sent_total = Gauge.build().name("Node_5_Link_4_bytes_sent_total").help("Node_5_Link_4_bytes_sent_total").register();

    public static final Gauge Node_6_Link_4_bytes_sent_total = Gauge.build().name("Node_6_Link_4_bytes_sent_total").help("Node_6_Link_4_bytes_sent_total").register();

    public static final Gauge Node_7_Link_4_bytes_sent_total = Gauge.build().name("Node_7_Link_4_bytes_sent_total").help("Node_7_Link_4_bytes_sent_total").register();

    public static final Gauge Node_8_Link_4_bytes_sent_total = Gauge.build().name("Node_8_Link_4_bytes_sent_total").help("Node_8_Link_4_bytes_sent_total").register();

    public static final Gauge Node_9_Link_4_bytes_sent_total = Gauge.build().name("Node_9_Link_4_bytes_sent_total").help("Node_9_Link_4_bytes_sent_total").register();

    public static final Gauge Node_10_Link_4_bytes_sent_total = Gauge.build().name("Node_10_Link_4_bytes_sent_total").help("Node_10_Link_4_bytes_sent_total").register();

    public static final Gauge Node_11_Link_4_bytes_sent_total = Gauge.build().name("Node_11_Link_4_bytes_sent_total").help("Node_11_Link_4_bytes_sent_total").register();

    public static final Gauge Node_12_Link_4_bytes_sent_total = Gauge.build().name("Node_12_Link_4_bytes_sent_total").help("Node_12_Link_4_bytes_sent_total").register();

    public static final Gauge Node_13_Link_4_bytes_sent_total = Gauge.build().name("Node_13_Link_4_bytes_sent_total").help("Node_13_Link_4_bytes_sent_total").register();

    public static final Gauge Node_14_Link_4_bytes_sent_total = Gauge.build().name("Node_14_Link_4_bytes_sent_total").help("Node_14_Link_4_bytes_sent_total").register();

    public static final Gauge Node_15_Link_4_bytes_sent_total = Gauge.build().name("Node_15_Link_4_bytes_sent_total").help("Node_15_Link_4_bytes_sent_total").register();

    public static final Gauge Node_16_Link_4_bytes_sent_total = Gauge.build().name("Node_16_Link_4_bytes_sent_total").help("Node_16_Link_4_bytes_sent_total").register();

    public static final Gauge Node_17_Link_4_bytes_sent_total = Gauge.build().name("Node_17_Link_4_bytes_sent_total").help("Node_17_Link_4_bytes_sent_total").register();

    public static final Gauge Node_18_Link_4_bytes_sent_total = Gauge.build().name("Node_18_Link_4_bytes_sent_total").help("Node_18_Link_4_bytes_sent_total").register();

    public static final Gauge Node_19_Link_4_bytes_sent_total = Gauge.build().name("Node_19_Link_4_bytes_sent_total").help("Node_19_Link_4_bytes_sent_total").register();

    public static final Gauge Node_20_Link_4_bytes_sent_total = Gauge.build().name("Node_20_Link_4_bytes_sent_total").help("Node_20_Link_4_bytes_sent_total").register();

//endregion

    //region instantaneous_transmitted_rate

    public static final Gauge Node_1_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_1_Link_1_instantaneous_transmitted_rate").help("Node_1_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_2_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_2_Link_1_instantaneous_transmitted_rate").help("Node_2_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_3_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_3_Link_1_instantaneous_transmitted_rate").help("Node_3_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_4_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_4_Link_1_instantaneous_transmitted_rate").help("Node_4_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_5_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_5_Link_1_instantaneous_transmitted_rate").help("Node_5_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_6_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_6_Link_1_instantaneous_transmitted_rate").help("Node_6_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_7_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_7_Link_1_instantaneous_transmitted_rate").help("Node_7_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_8_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_8_Link_1_instantaneous_transmitted_rate").help("Node_8_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_9_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_9_Link_1_instantaneous_transmitted_rate").help("Node_9_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_10_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_10_Link_1_instantaneous_transmitted_rate").help("Node_10_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_11_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_11_Link_1_instantaneous_transmitted_rate").help("Node_11_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_12_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_12_Link_1_instantaneous_transmitted_rate").help("Node_12_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_13_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_13_Link_1_instantaneous_transmitted_rate").help("Node_13_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_14_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_14_Link_1_instantaneous_transmitted_rate").help("Node_14_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_15_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_15_Link_1_instantaneous_transmitted_rate").help("Node_15_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_16_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_16_Link_1_instantaneous_transmitted_rate").help("Node_16_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_17_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_17_Link_1_instantaneous_transmitted_rate").help("Node_17_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_18_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_18_Link_1_instantaneous_transmitted_rate").help("Node_18_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_19_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_19_Link_1_instantaneous_transmitted_rate").help("Node_19_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_20_Link_1_instantaneous_transmitted_rate = Gauge.build().name("Node_20_Link_1_instantaneous_transmitted_rate").help("Node_20_Link_1_instantaneous_transmitted_rate").register();

    public static final Gauge Node_1_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_1_Link_2_instantaneous_transmitted_rate").help("Node_1_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_2_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_2_Link_2_instantaneous_transmitted_rate").help("Node_2_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_3_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_3_Link_2_instantaneous_transmitted_rate").help("Node_3_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_4_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_4_Link_2_instantaneous_transmitted_rate").help("Node_4_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_5_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_5_Link_2_instantaneous_transmitted_rate").help("Node_5_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_6_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_6_Link_2_instantaneous_transmitted_rate").help("Node_6_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_7_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_7_Link_2_instantaneous_transmitted_rate").help("Node_7_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_8_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_8_Link_2_instantaneous_transmitted_rate").help("Node_8_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_9_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_9_Link_2_instantaneous_transmitted_rate").help("Node_9_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_10_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_10_Link_2_instantaneous_transmitted_rate").help("Node_10_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_11_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_11_Link_2_instantaneous_transmitted_rate").help("Node_11_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_12_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_12_Link_2_instantaneous_transmitted_rate").help("Node_12_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_13_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_13_Link_2_instantaneous_transmitted_rate").help("Node_13_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_14_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_14_Link_2_instantaneous_transmitted_rate").help("Node_14_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_15_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_15_Link_2_instantaneous_transmitted_rate").help("Node_15_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_16_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_16_Link_2_instantaneous_transmitted_rate").help("Node_16_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_17_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_17_Link_2_instantaneous_transmitted_rate").help("Node_17_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_18_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_18_Link_2_instantaneous_transmitted_rate").help("Node_18_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_19_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_19_Link_2_instantaneous_transmitted_rate").help("Node_19_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_20_Link_2_instantaneous_transmitted_rate = Gauge.build().name("Node_20_Link_2_instantaneous_transmitted_rate").help("Node_20_Link_2_instantaneous_transmitted_rate").register();

    public static final Gauge Node_1_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_1_Link_3_instantaneous_transmitted_rate").help("Node_1_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_2_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_2_Link_3_instantaneous_transmitted_rate").help("Node_2_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_3_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_3_Link_3_instantaneous_transmitted_rate").help("Node_3_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_4_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_4_Link_3_instantaneous_transmitted_rate").help("Node_4_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_5_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_5_Link_3_instantaneous_transmitted_rate").help("Node_5_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_6_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_6_Link_3_instantaneous_transmitted_rate").help("Node_6_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_7_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_7_Link_3_instantaneous_transmitted_rate").help("Node_7_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_8_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_8_Link_3_instantaneous_transmitted_rate").help("Node_8_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_9_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_9_Link_3_instantaneous_transmitted_rate").help("Node_9_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_10_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_10_Link_3_instantaneous_transmitted_rate").help("Node_10_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_11_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_11_Link_3_instantaneous_transmitted_rate").help("Node_11_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_12_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_12_Link_3_instantaneous_transmitted_rate").help("Node_12_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_13_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_13_Link_3_instantaneous_transmitted_rate").help("Node_13_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_14_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_14_Link_3_instantaneous_transmitted_rate").help("Node_14_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_15_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_15_Link_3_instantaneous_transmitted_rate").help("Node_15_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_16_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_16_Link_3_instantaneous_transmitted_rate").help("Node_16_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_17_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_17_Link_3_instantaneous_transmitted_rate").help("Node_17_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_18_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_18_Link_3_instantaneous_transmitted_rate").help("Node_18_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_19_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_19_Link_3_instantaneous_transmitted_rate").help("Node_19_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_20_Link_3_instantaneous_transmitted_rate = Gauge.build().name("Node_20_Link_3_instantaneous_transmitted_rate").help("Node_20_Link_3_instantaneous_transmitted_rate").register();

    public static final Gauge Node_1_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_1_Link_4_instantaneous_transmitted_rate").help("Node_1_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_2_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_2_Link_4_instantaneous_transmitted_rate").help("Node_2_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_3_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_3_Link_4_instantaneous_transmitted_rate").help("Node_3_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_4_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_4_Link_4_instantaneous_transmitted_rate").help("Node_4_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_5_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_5_Link_4_instantaneous_transmitted_rate").help("Node_5_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_6_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_6_Link_4_instantaneous_transmitted_rate").help("Node_6_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_7_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_7_Link_4_instantaneous_transmitted_rate").help("Node_7_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_8_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_8_Link_4_instantaneous_transmitted_rate").help("Node_8_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_9_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_9_Link_4_instantaneous_transmitted_rate").help("Node_9_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_10_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_10_Link_4_instantaneous_transmitted_rate").help("Node_10_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_11_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_11_Link_4_instantaneous_transmitted_rate").help("Node_11_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_12_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_12_Link_4_instantaneous_transmitted_rate").help("Node_12_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_13_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_13_Link_4_instantaneous_transmitted_rate").help("Node_13_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_14_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_14_Link_4_instantaneous_transmitted_rate").help("Node_14_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_15_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_15_Link_4_instantaneous_transmitted_rate").help("Node_15_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_16_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_16_Link_4_instantaneous_transmitted_rate").help("Node_16_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_17_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_17_Link_4_instantaneous_transmitted_rate").help("Node_17_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_18_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_18_Link_4_instantaneous_transmitted_rate").help("Node_18_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_19_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_19_Link_4_instantaneous_transmitted_rate").help("Node_19_Link_4_instantaneous_transmitted_rate").register();

    public static final Gauge Node_20_Link_4_instantaneous_transmitted_rate = Gauge.build().name("Node_20_Link_4_instantaneous_transmitted_rate").help("Node_20_Link_4_instantaneous_transmitted_rate").register();

//endregion

    //region instantaneous_transmitted_rate_ratio

    public static final Gauge Node_1_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_1_Link_1_instantaneous_transmitted_rate_ratio").help("Node_1_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_2_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_2_Link_1_instantaneous_transmitted_rate_ratio").help("Node_2_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_3_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_3_Link_1_instantaneous_transmitted_rate_ratio").help("Node_3_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_4_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_4_Link_1_instantaneous_transmitted_rate_ratio").help("Node_4_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_5_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_5_Link_1_instantaneous_transmitted_rate_ratio").help("Node_5_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_6_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_6_Link_1_instantaneous_transmitted_rate_ratio").help("Node_6_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_7_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_7_Link_1_instantaneous_transmitted_rate_ratio").help("Node_7_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_8_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_8_Link_1_instantaneous_transmitted_rate_ratio").help("Node_8_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_9_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_9_Link_1_instantaneous_transmitted_rate_ratio").help("Node_9_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_10_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_10_Link_1_instantaneous_transmitted_rate_ratio").help("Node_10_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_11_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_11_Link_1_instantaneous_transmitted_rate_ratio").help("Node_11_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_12_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_12_Link_1_instantaneous_transmitted_rate_ratio").help("Node_12_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_13_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_13_Link_1_instantaneous_transmitted_rate_ratio").help("Node_13_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_14_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_14_Link_1_instantaneous_transmitted_rate_ratio").help("Node_14_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_15_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_15_Link_1_instantaneous_transmitted_rate_ratio").help("Node_15_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_16_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_16_Link_1_instantaneous_transmitted_rate_ratio").help("Node_16_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_17_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_17_Link_1_instantaneous_transmitted_rate_ratio").help("Node_17_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_18_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_18_Link_1_instantaneous_transmitted_rate_ratio").help("Node_18_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_19_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_19_Link_1_instantaneous_transmitted_rate_ratio").help("Node_19_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_20_Link_1_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_20_Link_1_instantaneous_transmitted_rate_ratio").help("Node_20_Link_1_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_1_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_1_Link_2_instantaneous_transmitted_rate_ratio").help("Node_1_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_2_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_2_Link_2_instantaneous_transmitted_rate_ratio").help("Node_2_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_3_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_3_Link_2_instantaneous_transmitted_rate_ratio").help("Node_3_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_4_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_4_Link_2_instantaneous_transmitted_rate_ratio").help("Node_4_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_5_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_5_Link_2_instantaneous_transmitted_rate_ratio").help("Node_5_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_6_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_6_Link_2_instantaneous_transmitted_rate_ratio").help("Node_6_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_7_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_7_Link_2_instantaneous_transmitted_rate_ratio").help("Node_7_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_8_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_8_Link_2_instantaneous_transmitted_rate_ratio").help("Node_8_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_9_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_9_Link_2_instantaneous_transmitted_rate_ratio").help("Node_9_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_10_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_10_Link_2_instantaneous_transmitted_rate_ratio").help("Node_10_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_11_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_11_Link_2_instantaneous_transmitted_rate_ratio").help("Node_11_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_12_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_12_Link_2_instantaneous_transmitted_rate_ratio").help("Node_12_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_13_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_13_Link_2_instantaneous_transmitted_rate_ratio").help("Node_13_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_14_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_14_Link_2_instantaneous_transmitted_rate_ratio").help("Node_14_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_15_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_15_Link_2_instantaneous_transmitted_rate_ratio").help("Node_15_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_16_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_16_Link_2_instantaneous_transmitted_rate_ratio").help("Node_16_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_17_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_17_Link_2_instantaneous_transmitted_rate_ratio").help("Node_17_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_18_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_18_Link_2_instantaneous_transmitted_rate_ratio").help("Node_18_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_19_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_19_Link_2_instantaneous_transmitted_rate_ratio").help("Node_19_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_20_Link_2_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_20_Link_2_instantaneous_transmitted_rate_ratio").help("Node_20_Link_2_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_1_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_1_Link_3_instantaneous_transmitted_rate_ratio").help("Node_1_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_2_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_2_Link_3_instantaneous_transmitted_rate_ratio").help("Node_2_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_3_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_3_Link_3_instantaneous_transmitted_rate_ratio").help("Node_3_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_4_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_4_Link_3_instantaneous_transmitted_rate_ratio").help("Node_4_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_5_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_5_Link_3_instantaneous_transmitted_rate_ratio").help("Node_5_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_6_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_6_Link_3_instantaneous_transmitted_rate_ratio").help("Node_6_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_7_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_7_Link_3_instantaneous_transmitted_rate_ratio").help("Node_7_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_8_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_8_Link_3_instantaneous_transmitted_rate_ratio").help("Node_8_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_9_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_9_Link_3_instantaneous_transmitted_rate_ratio").help("Node_9_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_10_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_10_Link_3_instantaneous_transmitted_rate_ratio").help("Node_10_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_11_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_11_Link_3_instantaneous_transmitted_rate_ratio").help("Node_11_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_12_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_12_Link_3_instantaneous_transmitted_rate_ratio").help("Node_12_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_13_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_13_Link_3_instantaneous_transmitted_rate_ratio").help("Node_13_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_14_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_14_Link_3_instantaneous_transmitted_rate_ratio").help("Node_14_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_15_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_15_Link_3_instantaneous_transmitted_rate_ratio").help("Node_15_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_16_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_16_Link_3_instantaneous_transmitted_rate_ratio").help("Node_16_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_17_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_17_Link_3_instantaneous_transmitted_rate_ratio").help("Node_17_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_18_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_18_Link_3_instantaneous_transmitted_rate_ratio").help("Node_18_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_19_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_19_Link_3_instantaneous_transmitted_rate_ratio").help("Node_19_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_20_Link_3_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_20_Link_3_instantaneous_transmitted_rate_ratio").help("Node_20_Link_3_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_1_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_1_Link_4_instantaneous_transmitted_rate_ratio").help("Node_1_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_2_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_2_Link_4_instantaneous_transmitted_rate_ratio").help("Node_2_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_3_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_3_Link_4_instantaneous_transmitted_rate_ratio").help("Node_3_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_4_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_4_Link_4_instantaneous_transmitted_rate_ratio").help("Node_4_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_5_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_5_Link_4_instantaneous_transmitted_rate_ratio").help("Node_5_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_6_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_6_Link_4_instantaneous_transmitted_rate_ratio").help("Node_6_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_7_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_7_Link_4_instantaneous_transmitted_rate_ratio").help("Node_7_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_8_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_8_Link_4_instantaneous_transmitted_rate_ratio").help("Node_8_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_9_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_9_Link_4_instantaneous_transmitted_rate_ratio").help("Node_9_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_10_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_10_Link_4_instantaneous_transmitted_rate_ratio").help("Node_10_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_11_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_11_Link_4_instantaneous_transmitted_rate_ratio").help("Node_11_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_12_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_12_Link_4_instantaneous_transmitted_rate_ratio").help("Node_12_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_13_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_13_Link_4_instantaneous_transmitted_rate_ratio").help("Node_13_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_14_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_14_Link_4_instantaneous_transmitted_rate_ratio").help("Node_14_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_15_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_15_Link_4_instantaneous_transmitted_rate_ratio").help("Node_15_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_16_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_16_Link_4_instantaneous_transmitted_rate_ratio").help("Node_16_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_17_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_17_Link_4_instantaneous_transmitted_rate_ratio").help("Node_17_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_18_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_18_Link_4_instantaneous_transmitted_rate_ratio").help("Node_18_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_19_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_19_Link_4_instantaneous_transmitted_rate_ratio").help("Node_19_Link_4_instantaneous_transmitted_rate_ratio").register();

    public static final Gauge Node_20_Link_4_instantaneous_transmitted_rate_ratio = Gauge.build().name("Node_20_Link_4_instantaneous_transmitted_rate_ratio").help("Node_20_Link_4_instantaneous_transmitted_rate_ratio").register();

//endregion



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


    public static void updateODLMetricsAll()
    {
        System.out.println("Updating ALL ODL Metrics");

        //region Update_packets_received_total

        Node_1_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_1_packets_received_total"));

        Node_2_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_1_packets_received_total"));

        Node_3_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_1_packets_received_total"));

        Node_4_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_1_packets_received_total"));

        Node_5_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_1_packets_received_total"));

        Node_6_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_1_packets_received_total"));

        Node_7_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_1_packets_received_total"));

        Node_8_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_1_packets_received_total"));

        Node_9_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_1_packets_received_total"));

        Node_10_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_1_packets_received_total"));

        Node_11_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_1_packets_received_total"));

        Node_12_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_1_packets_received_total"));

        Node_13_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_1_packets_received_total"));

        Node_14_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_1_packets_received_total"));

        Node_15_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_1_packets_received_total"));

        Node_16_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_1_packets_received_total"));

        Node_17_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_1_packets_received_total"));

        Node_18_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_1_packets_received_total"));

        Node_19_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_1_packets_received_total"));

        Node_20_Link_1_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_1_packets_received_total"));

        Node_1_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_2_packets_received_total"));

        Node_2_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_2_packets_received_total"));

        Node_3_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_2_packets_received_total"));

        Node_4_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_2_packets_received_total"));

        Node_5_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_2_packets_received_total"));

        Node_6_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_2_packets_received_total"));

        Node_7_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_2_packets_received_total"));

        Node_8_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_2_packets_received_total"));

        Node_9_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_2_packets_received_total"));

        Node_10_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_2_packets_received_total"));

        Node_11_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_2_packets_received_total"));

        Node_12_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_2_packets_received_total"));

        Node_13_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_2_packets_received_total"));

        Node_14_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_2_packets_received_total"));

        Node_15_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_2_packets_received_total"));

        Node_16_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_2_packets_received_total"));

        Node_17_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_2_packets_received_total"));

        Node_18_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_2_packets_received_total"));

        Node_19_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_2_packets_received_total"));

        Node_20_Link_2_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_2_packets_received_total"));

        Node_1_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_3_packets_received_total"));

        Node_2_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_3_packets_received_total"));

        Node_3_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_3_packets_received_total"));

        Node_4_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_3_packets_received_total"));

        Node_5_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_3_packets_received_total"));

        Node_6_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_3_packets_received_total"));

        Node_7_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_3_packets_received_total"));

        Node_8_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_3_packets_received_total"));

        Node_9_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_3_packets_received_total"));

        Node_10_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_3_packets_received_total"));

        Node_11_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_3_packets_received_total"));

        Node_12_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_3_packets_received_total"));

        Node_13_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_3_packets_received_total"));

        Node_14_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_3_packets_received_total"));

        Node_15_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_3_packets_received_total"));

        Node_16_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_3_packets_received_total"));

        Node_17_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_3_packets_received_total"));

        Node_18_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_3_packets_received_total"));

        Node_19_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_3_packets_received_total"));

        Node_20_Link_3_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_3_packets_received_total"));

        Node_1_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_4_packets_received_total"));

        Node_2_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_4_packets_received_total"));

        Node_3_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_4_packets_received_total"));

        Node_4_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_4_packets_received_total"));

        Node_5_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_4_packets_received_total"));

        Node_6_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_4_packets_received_total"));

        Node_7_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_4_packets_received_total"));

        Node_8_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_4_packets_received_total"));

        Node_9_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_4_packets_received_total"));

        Node_10_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_4_packets_received_total"));

        Node_11_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_4_packets_received_total"));

        Node_12_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_4_packets_received_total"));

        Node_13_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_4_packets_received_total"));

        Node_14_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_4_packets_received_total"));

        Node_15_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_4_packets_received_total"));

        Node_16_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_4_packets_received_total"));

        Node_17_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_4_packets_received_total"));

        Node_18_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_4_packets_received_total"));

        Node_19_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_4_packets_received_total"));

        Node_20_Link_4_packets_received_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_4_packets_received_total"));

//endregion

        //region Update_packets_sent_total

        Node_1_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_1_packets_sent_total"));

        Node_2_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_1_packets_sent_total"));

        Node_3_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_1_packets_sent_total"));

        Node_4_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_1_packets_sent_total"));

        Node_5_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_1_packets_sent_total"));

        Node_6_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_1_packets_sent_total"));

        Node_7_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_1_packets_sent_total"));

        Node_8_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_1_packets_sent_total"));

        Node_9_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_1_packets_sent_total"));

        Node_10_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_1_packets_sent_total"));

        Node_11_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_1_packets_sent_total"));

        Node_12_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_1_packets_sent_total"));

        Node_13_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_1_packets_sent_total"));

        Node_14_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_1_packets_sent_total"));

        Node_15_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_1_packets_sent_total"));

        Node_16_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_1_packets_sent_total"));

        Node_17_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_1_packets_sent_total"));

        Node_18_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_1_packets_sent_total"));

        Node_19_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_1_packets_sent_total"));

        Node_20_Link_1_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_1_packets_sent_total"));

        Node_1_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_2_packets_sent_total"));

        Node_2_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_2_packets_sent_total"));

        Node_3_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_2_packets_sent_total"));

        Node_4_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_2_packets_sent_total"));

        Node_5_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_2_packets_sent_total"));

        Node_6_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_2_packets_sent_total"));

        Node_7_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_2_packets_sent_total"));

        Node_8_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_2_packets_sent_total"));

        Node_9_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_2_packets_sent_total"));

        Node_10_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_2_packets_sent_total"));

        Node_11_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_2_packets_sent_total"));

        Node_12_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_2_packets_sent_total"));

        Node_13_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_2_packets_sent_total"));

        Node_14_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_2_packets_sent_total"));

        Node_15_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_2_packets_sent_total"));

        Node_16_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_2_packets_sent_total"));

        Node_17_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_2_packets_sent_total"));

        Node_18_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_2_packets_sent_total"));

        Node_19_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_2_packets_sent_total"));

        Node_20_Link_2_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_2_packets_sent_total"));

        Node_1_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_3_packets_sent_total"));

        Node_2_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_3_packets_sent_total"));

        Node_3_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_3_packets_sent_total"));

        Node_4_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_3_packets_sent_total"));

        Node_5_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_3_packets_sent_total"));

        Node_6_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_3_packets_sent_total"));

        Node_7_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_3_packets_sent_total"));

        Node_8_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_3_packets_sent_total"));

        Node_9_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_3_packets_sent_total"));

        Node_10_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_3_packets_sent_total"));

        Node_11_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_3_packets_sent_total"));

        Node_12_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_3_packets_sent_total"));

        Node_13_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_3_packets_sent_total"));

        Node_14_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_3_packets_sent_total"));

        Node_15_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_3_packets_sent_total"));

        Node_16_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_3_packets_sent_total"));

        Node_17_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_3_packets_sent_total"));

        Node_18_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_3_packets_sent_total"));

        Node_19_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_3_packets_sent_total"));

        Node_20_Link_3_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_3_packets_sent_total"));

        Node_1_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_4_packets_sent_total"));

        Node_2_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_4_packets_sent_total"));

        Node_3_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_4_packets_sent_total"));

        Node_4_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_4_packets_sent_total"));

        Node_5_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_4_packets_sent_total"));

        Node_6_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_4_packets_sent_total"));

        Node_7_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_4_packets_sent_total"));

        Node_8_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_4_packets_sent_total"));

        Node_9_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_4_packets_sent_total"));

        Node_10_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_4_packets_sent_total"));

        Node_11_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_4_packets_sent_total"));

        Node_12_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_4_packets_sent_total"));

        Node_13_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_4_packets_sent_total"));

        Node_14_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_4_packets_sent_total"));

        Node_15_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_4_packets_sent_total"));

        Node_16_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_4_packets_sent_total"));

        Node_17_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_4_packets_sent_total"));

        Node_18_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_4_packets_sent_total"));

        Node_19_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_4_packets_sent_total"));

        Node_20_Link_4_packets_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_4_packets_sent_total"));

//endregion

        //region Update_collision_count

        Node_1_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_1_collision_count"));

        Node_2_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_1_collision_count"));

        Node_3_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_1_collision_count"));

        Node_4_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_1_collision_count"));

        Node_5_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_1_collision_count"));

        Node_6_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_1_collision_count"));

        Node_7_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_1_collision_count"));

        Node_8_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_1_collision_count"));

        Node_9_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_1_collision_count"));

        Node_10_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_1_collision_count"));

        Node_11_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_1_collision_count"));

        Node_12_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_1_collision_count"));

        Node_13_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_1_collision_count"));

        Node_14_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_1_collision_count"));

        Node_15_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_1_collision_count"));

        Node_16_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_1_collision_count"));

        Node_17_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_1_collision_count"));

        Node_18_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_1_collision_count"));

        Node_19_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_1_collision_count"));

        Node_20_Link_1_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_1_collision_count"));

        Node_1_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_2_collision_count"));

        Node_2_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_2_collision_count"));

        Node_3_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_2_collision_count"));

        Node_4_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_2_collision_count"));

        Node_5_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_2_collision_count"));

        Node_6_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_2_collision_count"));

        Node_7_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_2_collision_count"));

        Node_8_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_2_collision_count"));

        Node_9_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_2_collision_count"));

        Node_10_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_2_collision_count"));

        Node_11_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_2_collision_count"));

        Node_12_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_2_collision_count"));

        Node_13_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_2_collision_count"));

        Node_14_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_2_collision_count"));

        Node_15_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_2_collision_count"));

        Node_16_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_2_collision_count"));

        Node_17_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_2_collision_count"));

        Node_18_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_2_collision_count"));

        Node_19_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_2_collision_count"));

        Node_20_Link_2_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_2_collision_count"));

        Node_1_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_3_collision_count"));

        Node_2_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_3_collision_count"));

        Node_3_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_3_collision_count"));

        Node_4_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_3_collision_count"));

        Node_5_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_3_collision_count"));

        Node_6_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_3_collision_count"));

        Node_7_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_3_collision_count"));

        Node_8_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_3_collision_count"));

        Node_9_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_3_collision_count"));

        Node_10_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_3_collision_count"));

        Node_11_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_3_collision_count"));

        Node_12_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_3_collision_count"));

        Node_13_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_3_collision_count"));

        Node_14_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_3_collision_count"));

        Node_15_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_3_collision_count"));

        Node_16_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_3_collision_count"));

        Node_17_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_3_collision_count"));

        Node_18_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_3_collision_count"));

        Node_19_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_3_collision_count"));

        Node_20_Link_3_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_3_collision_count"));

        Node_1_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_4_collision_count"));

        Node_2_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_4_collision_count"));

        Node_3_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_4_collision_count"));

        Node_4_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_4_collision_count"));

        Node_5_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_4_collision_count"));

        Node_6_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_4_collision_count"));

        Node_7_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_4_collision_count"));

        Node_8_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_4_collision_count"));

        Node_9_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_4_collision_count"));

        Node_10_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_4_collision_count"));

        Node_11_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_4_collision_count"));

        Node_12_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_4_collision_count"));

        Node_13_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_4_collision_count"));

        Node_14_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_4_collision_count"));

        Node_15_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_4_collision_count"));

        Node_16_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_4_collision_count"));

        Node_17_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_4_collision_count"));

        Node_18_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_4_collision_count"));

        Node_19_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_4_collision_count"));

        Node_20_Link_4_collision_count.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_4_collision_count"));

//endregion

        //region Update_bytes_received_total

        Node_1_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_1_bytes_received_total"));

        Node_2_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_1_bytes_received_total"));

        Node_3_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_1_bytes_received_total"));

        Node_4_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_1_bytes_received_total"));

        Node_5_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_1_bytes_received_total"));

        Node_6_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_1_bytes_received_total"));

        Node_7_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_1_bytes_received_total"));

        Node_8_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_1_bytes_received_total"));

        Node_9_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_1_bytes_received_total"));

        Node_10_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_1_bytes_received_total"));

        Node_11_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_1_bytes_received_total"));

        Node_12_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_1_bytes_received_total"));

        Node_13_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_1_bytes_received_total"));

        Node_14_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_1_bytes_received_total"));

        Node_15_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_1_bytes_received_total"));

        Node_16_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_1_bytes_received_total"));

        Node_17_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_1_bytes_received_total"));

        Node_18_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_1_bytes_received_total"));

        Node_19_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_1_bytes_received_total"));

        Node_20_Link_1_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_1_bytes_received_total"));

        Node_1_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_2_bytes_received_total"));

        Node_2_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_2_bytes_received_total"));

        Node_3_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_2_bytes_received_total"));

        Node_4_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_2_bytes_received_total"));

        Node_5_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_2_bytes_received_total"));

        Node_6_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_2_bytes_received_total"));

        Node_7_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_2_bytes_received_total"));

        Node_8_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_2_bytes_received_total"));

        Node_9_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_2_bytes_received_total"));

        Node_10_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_2_bytes_received_total"));

        Node_11_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_2_bytes_received_total"));

        Node_12_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_2_bytes_received_total"));

        Node_13_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_2_bytes_received_total"));

        Node_14_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_2_bytes_received_total"));

        Node_15_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_2_bytes_received_total"));

        Node_16_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_2_bytes_received_total"));

        Node_17_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_2_bytes_received_total"));

        Node_18_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_2_bytes_received_total"));

        Node_19_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_2_bytes_received_total"));

        Node_20_Link_2_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_2_bytes_received_total"));

        Node_1_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_3_bytes_received_total"));

        Node_2_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_3_bytes_received_total"));

        Node_3_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_3_bytes_received_total"));

        Node_4_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_3_bytes_received_total"));

        Node_5_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_3_bytes_received_total"));

        Node_6_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_3_bytes_received_total"));

        Node_7_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_3_bytes_received_total"));

        Node_8_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_3_bytes_received_total"));

        Node_9_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_3_bytes_received_total"));

        Node_10_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_3_bytes_received_total"));

        Node_11_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_3_bytes_received_total"));

        Node_12_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_3_bytes_received_total"));

        Node_13_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_3_bytes_received_total"));

        Node_14_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_3_bytes_received_total"));

        Node_15_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_3_bytes_received_total"));

        Node_16_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_3_bytes_received_total"));

        Node_17_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_3_bytes_received_total"));

        Node_18_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_3_bytes_received_total"));

        Node_19_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_3_bytes_received_total"));

        Node_20_Link_3_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_3_bytes_received_total"));

        Node_1_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_4_bytes_received_total"));

        Node_2_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_4_bytes_received_total"));

        Node_3_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_4_bytes_received_total"));

        Node_4_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_4_bytes_received_total"));

        Node_5_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_4_bytes_received_total"));

        Node_6_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_4_bytes_received_total"));

        Node_7_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_4_bytes_received_total"));

        Node_8_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_4_bytes_received_total"));

        Node_9_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_4_bytes_received_total"));

        Node_10_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_4_bytes_received_total"));

        Node_11_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_4_bytes_received_total"));

        Node_12_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_4_bytes_received_total"));

        Node_13_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_4_bytes_received_total"));

        Node_14_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_4_bytes_received_total"));

        Node_15_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_4_bytes_received_total"));

        Node_16_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_4_bytes_received_total"));

        Node_17_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_4_bytes_received_total"));

        Node_18_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_4_bytes_received_total"));

        Node_19_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_4_bytes_received_total"));

        Node_20_Link_4_bytes_received_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_4_bytes_received_total"));

//endregion

        //region Update_bytes_sent_total

        Node_1_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_1_bytes_sent_total"));

        Node_2_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_1_bytes_sent_total"));

        Node_3_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_1_bytes_sent_total"));

        Node_4_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_1_bytes_sent_total"));

        Node_5_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_1_bytes_sent_total"));

        Node_6_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_1_bytes_sent_total"));

        Node_7_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_1_bytes_sent_total"));

        Node_8_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_1_bytes_sent_total"));

        Node_9_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_1_bytes_sent_total"));

        Node_10_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_1_bytes_sent_total"));

        Node_11_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_1_bytes_sent_total"));

        Node_12_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_1_bytes_sent_total"));

        Node_13_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_1_bytes_sent_total"));

        Node_14_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_1_bytes_sent_total"));

        Node_15_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_1_bytes_sent_total"));

        Node_16_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_1_bytes_sent_total"));

        Node_17_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_1_bytes_sent_total"));

        Node_18_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_1_bytes_sent_total"));

        Node_19_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_1_bytes_sent_total"));

        Node_20_Link_1_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_1_bytes_sent_total"));

        Node_1_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_2_bytes_sent_total"));

        Node_2_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_2_bytes_sent_total"));

        Node_3_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_2_bytes_sent_total"));

        Node_4_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_2_bytes_sent_total"));

        Node_5_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_2_bytes_sent_total"));

        Node_6_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_2_bytes_sent_total"));

        Node_7_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_2_bytes_sent_total"));

        Node_8_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_2_bytes_sent_total"));

        Node_9_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_2_bytes_sent_total"));

        Node_10_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_2_bytes_sent_total"));

        Node_11_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_2_bytes_sent_total"));

        Node_12_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_2_bytes_sent_total"));

        Node_13_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_2_bytes_sent_total"));

        Node_14_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_2_bytes_sent_total"));

        Node_15_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_2_bytes_sent_total"));

        Node_16_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_2_bytes_sent_total"));

        Node_17_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_2_bytes_sent_total"));

        Node_18_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_2_bytes_sent_total"));

        Node_19_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_2_bytes_sent_total"));

        Node_20_Link_2_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_2_bytes_sent_total"));

        Node_1_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_3_bytes_sent_total"));

        Node_2_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_3_bytes_sent_total"));

        Node_3_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_3_bytes_sent_total"));

        Node_4_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_3_bytes_sent_total"));

        Node_5_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_3_bytes_sent_total"));

        Node_6_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_3_bytes_sent_total"));

        Node_7_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_3_bytes_sent_total"));

        Node_8_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_3_bytes_sent_total"));

        Node_9_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_3_bytes_sent_total"));

        Node_10_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_3_bytes_sent_total"));

        Node_11_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_3_bytes_sent_total"));

        Node_12_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_3_bytes_sent_total"));

        Node_13_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_3_bytes_sent_total"));

        Node_14_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_3_bytes_sent_total"));

        Node_15_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_3_bytes_sent_total"));

        Node_16_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_3_bytes_sent_total"));

        Node_17_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_3_bytes_sent_total"));

        Node_18_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_3_bytes_sent_total"));

        Node_19_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_3_bytes_sent_total"));

        Node_20_Link_3_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_3_bytes_sent_total"));

        Node_1_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_4_bytes_sent_total"));

        Node_2_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_4_bytes_sent_total"));

        Node_3_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_4_bytes_sent_total"));

        Node_4_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_4_bytes_sent_total"));

        Node_5_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_4_bytes_sent_total"));

        Node_6_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_4_bytes_sent_total"));

        Node_7_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_4_bytes_sent_total"));

        Node_8_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_4_bytes_sent_total"));

        Node_9_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_4_bytes_sent_total"));

        Node_10_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_4_bytes_sent_total"));

        Node_11_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_4_bytes_sent_total"));

        Node_12_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_4_bytes_sent_total"));

        Node_13_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_4_bytes_sent_total"));

        Node_14_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_4_bytes_sent_total"));

        Node_15_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_4_bytes_sent_total"));

        Node_16_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_4_bytes_sent_total"));

        Node_17_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_4_bytes_sent_total"));

        Node_18_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_4_bytes_sent_total"));

        Node_19_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_4_bytes_sent_total"));

        Node_20_Link_4_bytes_sent_total.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_4_bytes_sent_total"));

//endregion

        //region Update_instantaneous_transmitted_rate

        Node_1_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_1_instantaneous_transmitted_rate"));

        Node_2_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_1_instantaneous_transmitted_rate"));

        Node_3_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_1_instantaneous_transmitted_rate"));

        Node_4_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_1_instantaneous_transmitted_rate"));

        Node_5_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_1_instantaneous_transmitted_rate"));

        Node_6_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_1_instantaneous_transmitted_rate"));

        Node_7_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_1_instantaneous_transmitted_rate"));

        Node_8_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_1_instantaneous_transmitted_rate"));

        Node_9_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_1_instantaneous_transmitted_rate"));

        Node_10_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_1_instantaneous_transmitted_rate"));

        Node_11_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_1_instantaneous_transmitted_rate"));

        Node_12_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_1_instantaneous_transmitted_rate"));

        Node_13_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_1_instantaneous_transmitted_rate"));

        Node_14_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_1_instantaneous_transmitted_rate"));

        Node_15_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_1_instantaneous_transmitted_rate"));

        Node_16_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_1_instantaneous_transmitted_rate"));

        Node_17_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_1_instantaneous_transmitted_rate"));

        Node_18_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_1_instantaneous_transmitted_rate"));

        Node_19_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_1_instantaneous_transmitted_rate"));

        Node_20_Link_1_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_1_instantaneous_transmitted_rate"));

        Node_1_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_2_instantaneous_transmitted_rate"));

        Node_2_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_2_instantaneous_transmitted_rate"));

        Node_3_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_2_instantaneous_transmitted_rate"));

        Node_4_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_2_instantaneous_transmitted_rate"));

        Node_5_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_2_instantaneous_transmitted_rate"));

        Node_6_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_2_instantaneous_transmitted_rate"));

        Node_7_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_2_instantaneous_transmitted_rate"));

        Node_8_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_2_instantaneous_transmitted_rate"));

        Node_9_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_2_instantaneous_transmitted_rate"));

        Node_10_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_2_instantaneous_transmitted_rate"));

        Node_11_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_2_instantaneous_transmitted_rate"));

        Node_12_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_2_instantaneous_transmitted_rate"));

        Node_13_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_2_instantaneous_transmitted_rate"));

        Node_14_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_2_instantaneous_transmitted_rate"));

        Node_15_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_2_instantaneous_transmitted_rate"));

        Node_16_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_2_instantaneous_transmitted_rate"));

        Node_17_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_2_instantaneous_transmitted_rate"));

        Node_18_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_2_instantaneous_transmitted_rate"));

        Node_19_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_2_instantaneous_transmitted_rate"));

        Node_20_Link_2_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_2_instantaneous_transmitted_rate"));

        Node_1_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_3_instantaneous_transmitted_rate"));

        Node_2_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_3_instantaneous_transmitted_rate"));

        Node_3_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_3_instantaneous_transmitted_rate"));

        Node_4_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_3_instantaneous_transmitted_rate"));

        Node_5_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_3_instantaneous_transmitted_rate"));

        Node_6_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_3_instantaneous_transmitted_rate"));

        Node_7_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_3_instantaneous_transmitted_rate"));

        Node_8_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_3_instantaneous_transmitted_rate"));

        Node_9_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_3_instantaneous_transmitted_rate"));

        Node_10_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_3_instantaneous_transmitted_rate"));

        Node_11_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_3_instantaneous_transmitted_rate"));

        Node_12_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_3_instantaneous_transmitted_rate"));

        Node_13_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_3_instantaneous_transmitted_rate"));

        Node_14_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_3_instantaneous_transmitted_rate"));

        Node_15_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_3_instantaneous_transmitted_rate"));

        Node_16_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_3_instantaneous_transmitted_rate"));

        Node_17_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_3_instantaneous_transmitted_rate"));

        Node_18_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_3_instantaneous_transmitted_rate"));

        Node_19_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_3_instantaneous_transmitted_rate"));

        Node_20_Link_3_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_3_instantaneous_transmitted_rate"));

        Node_1_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_4_instantaneous_transmitted_rate"));

        Node_2_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_4_instantaneous_transmitted_rate"));

        Node_3_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_4_instantaneous_transmitted_rate"));

        Node_4_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_4_instantaneous_transmitted_rate"));

        Node_5_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_4_instantaneous_transmitted_rate"));

        Node_6_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_4_instantaneous_transmitted_rate"));

        Node_7_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_4_instantaneous_transmitted_rate"));

        Node_8_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_4_instantaneous_transmitted_rate"));

        Node_9_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_4_instantaneous_transmitted_rate"));

        Node_10_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_4_instantaneous_transmitted_rate"));

        Node_11_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_4_instantaneous_transmitted_rate"));

        Node_12_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_4_instantaneous_transmitted_rate"));

        Node_13_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_4_instantaneous_transmitted_rate"));

        Node_14_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_4_instantaneous_transmitted_rate"));

        Node_15_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_4_instantaneous_transmitted_rate"));

        Node_16_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_4_instantaneous_transmitted_rate"));

        Node_17_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_4_instantaneous_transmitted_rate"));

        Node_18_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_4_instantaneous_transmitted_rate"));

        Node_19_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_4_instantaneous_transmitted_rate"));

        Node_20_Link_4_instantaneous_transmitted_rate.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_4_instantaneous_transmitted_rate"));

//endregion

        //region Update_instantaneous_transmitted_rate_ratio

        Node_1_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_2_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_3_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_4_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_5_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_6_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_7_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_8_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_9_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_10_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_11_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_12_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_13_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_14_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_15_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_16_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_17_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_18_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_19_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_20_Link_1_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_1_instantaneous_transmitted_rate_ratio"));

        Node_1_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_2_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_3_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_4_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_5_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_6_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_7_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_8_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_9_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_10_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_11_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_12_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_13_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_14_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_15_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_16_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_17_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_18_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_19_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_20_Link_2_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_2_instantaneous_transmitted_rate_ratio"));

        Node_1_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_2_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_3_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_4_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_5_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_6_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_7_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_8_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_9_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_10_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_11_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_12_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_13_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_14_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_15_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_16_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_17_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_18_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_19_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_20_Link_3_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_3_instantaneous_transmitted_rate_ratio"));

        Node_1_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_1_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_2_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_2_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_3_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_3_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_4_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_4_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_5_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_5_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_6_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_6_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_7_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_7_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_8_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_8_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_9_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_9_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_10_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_10_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_11_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_11_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_12_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_12_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_13_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_13_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_14_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_14_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_15_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_15_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_16_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_16_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_17_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_17_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_18_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_18_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_19_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_19_Link_4_instantaneous_transmitted_rate_ratio"));

        Node_20_Link_4_instantaneous_transmitted_rate_ratio.set(ODLRESTHelper.getMetricValueByName("Node_20_Link_4_instantaneous_transmitted_rate_ratio"));

//endregion

    }

    public ODLNodeInstrumetation()
    {

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

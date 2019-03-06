/*
 Author: KMN
 Descr: Node Instrumentation
 Institution: LJMU
 */


package com.telemetryserver.Instrumentation;

import SDNRouting.Class1;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.telemetryserver.dao.ODLFlowHelper;
import com.telemetryserver.dao.ODLParamPollScheduler;
import com.telemetryserver.dao.ODLRESTHelper;
import io.prometheus.client.Gauge;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class ODLNodeInstrumetation extends HttpServlet
{
    public static int[][] prevTXBytes = new int[32][5]; //Updated every period!!!!
    public static JSONObject[][] jsonObjects = new JSONObject[21][5];
    public static int linkBW = (int) 8000000; //This is in Bits (1MB);
    public static int samplingPeriodMS;

    public static double alpha = 0;
    public static boolean alphaComputed = false;
    public static boolean pathsComputed = false;
    public static boolean flowSent = false;
    public static boolean pathsSent = false;
    public static MWNumericArray shortestPathMatrix = null;
    public static int[][] linkTerminationTable = new int[20][20]; //linkTermination

    public static Timer pollTimer = new Timer();

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
        //Deprecated
    }

    public static void updatePrevTXBytes()
    {
        ODLRESTHelper.updatePrevTXBytes();
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

    public static void computeShortestPathMatrix()
    {
        if(!pathsComputed || !alphaComputed) return;

        System.out.println("Alpha for node 10 is: " + ODLNodeInstrumetation.alpha);

        ODLNodeInstrumetation.pollTimer.cancel();

        computeShortestPathMatrix(ODLNodeInstrumetation.alpha);

    }

    private static void computeShortestPathMatrix(double rate)
    {
        try
        {
            SDNRouting.Class1 sdncls = new Class1();

            List input = new ArrayList(1);
            List result = new ArrayList(20 * 20 * 20);

            input.add(0.1);
            result.add(0);


            sdncls.getRoutingTable(result, input);

            ODLNodeInstrumetation.shortestPathMatrix = (MWNumericArray) result.get(0);

            sdncls.dispose();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static int computeNodeInShortestPath(int src, int dest)
    {
        try
        {
            SDNRouting.Class1 sdncls = new Class1();

            List pathInput = new ArrayList();
            pathInput.add(src);
            pathInput.add(dest);
            pathInput.add(ODLNodeInstrumetation.shortestPathMatrix);

            List pathResult = new ArrayList();
            pathResult.add(0);

            sdncls.getPath(pathResult, pathInput);
            MWNumericArray r = (MWNumericArray) pathResult.get(0);
            int nexthop = (int)(double) r.get(2);

            sdncls.dispose();

            return nexthop;

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    public static void sendAllODLFlows()
    {
        if(!ODLNodeInstrumetation.alphaComputed || !ODLNodeInstrumetation.pathsComputed) return;

        if(ODLNodeInstrumetation.flowSent) return;

        int nxtNode;
        for(int src = 1; src < 20; src++)
            for(int dest = 1; dest < 20; dest++)
            {
                if(src != dest){

                //Compute next node
                nxtNode = computeNodeInShortestPath(src, dest);

                //Send Flow
                String flowName = "Flow_" + src + "_" + dest;
                int terminationPort = ODLNodeInstrumetation.linkTerminationTable[src][dest];
                int priority = 10;

                if(terminationPort != 0)
                {
                    //Configure the xmlFlow
                    String xmlFlow = ODLFlowHelper.configureFromXMLFlow(flowName, ((Integer) dest).toString(), priority, terminationPort, dest);

                    //Send the flow to SRC node
                    ODLFlowHelper.sendFlow(xmlFlow, src, dest);
                }
            }
            }

        ODLNodeInstrumetation.pollTimer.scheduleAtFixedRate(
                new ODLParamPollScheduler(), 0, ODLNodeInstrumetation.samplingPeriodMS);
            ODLNodeInstrumetation.flowSent = true;

        System.out.print("Flows Sent!");

    }


    private static int TerminationPoint(int src, int dest)
    {
        return linkTerminationTable[src][dest];
    }

    public static void generateLinkTable()
    {
        //Node 1
        linkTerminationTable[1][4] = 2;

        //Node 2
        linkTerminationTable[2][13] = 2;
        linkTerminationTable[2][18] = 3;

        //Node 3
        linkTerminationTable[3][6] = 2;

        //Node 4
        linkTerminationTable[4][1] = 2;
        linkTerminationTable[4][5] = 3;
        linkTerminationTable[4][7] = 4;

        //Node 5
        linkTerminationTable[5][10] = 3;

        //Node 6
        linkTerminationTable[6][3] = 2;
        linkTerminationTable[6][7] = 3;
        linkTerminationTable[6][11] = 4;

        //Node 7
        linkTerminationTable[7][4] = 2;
        linkTerminationTable[7][6] = 3;
        linkTerminationTable[7][8] = 4;

        //Node 8
        linkTerminationTable[8][7] = 2;
        linkTerminationTable[8][9] = 3;
        linkTerminationTable[8][12] = 4;

        //Node 9
        linkTerminationTable[9][8] = 2;
        linkTerminationTable[9][16] = 3;
        linkTerminationTable[9][18] = 4;

        //Node 10
        linkTerminationTable[10][5] = 2;

        //Node 11
        linkTerminationTable[11][6] = 2;
        linkTerminationTable[11][12] = 3;
        linkTerminationTable[11][13] = 4;

        //Node 12
        linkTerminationTable[12][8] = 2;
        linkTerminationTable[12][12] = 3;

        //Node 13
        linkTerminationTable[13][2] = 2;
        linkTerminationTable[13][11] = 3;

        //Node 14
        linkTerminationTable[14][15] = 2;
        linkTerminationTable[14][16] = 3;

        //Node 15
        linkTerminationTable[15][14] = 2;
        linkTerminationTable[15][17] = 3;

        //Node 16
        linkTerminationTable[16][9] = 2;
        linkTerminationTable[16][14] = 3;
        linkTerminationTable[16][19] = 4;

        //Node 17
        linkTerminationTable[17][15] = 2;
        linkTerminationTable[17][18] = 3;

        //Node 18
        linkTerminationTable[18][2] = 2;
        linkTerminationTable[18][9] = 3;
        linkTerminationTable[18][17] = 4;

        //Node 19
        linkTerminationTable[19][16] = 2;
    }

    public static void updateLinkStateObjects()
    {
       for(int node = 1; node <= 20; node++)
           for(int link = 1; link <=4; link++)
               jsonObjects[node][link] = ODLRESTHelper.ODLPortStatisticsJSON(node, link);
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

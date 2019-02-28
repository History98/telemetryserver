package com.telemetryserver.dao;

import com.google.gson.*;
import com.telemetryserver.Instrumentation.ODLNodeInstrumetation;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ODLRESTHelper
{
    public static int ODLRESTNodeLinkPacketParamExtraction(int nodeNumber, int linkNumber, String param)
    {
        String nodeName = "openflow:" + nodeNumber;
        String nodeLink = nodeName + ":" + linkNumber;

        //Get current topology of currently running ODL instance and print it
        //feature:install odl-restconf-all must be installed
        try
        {
            String loginPassword = "admin" + ":" + "admin";
            String encoded = new sun.misc.BASE64Encoder().encode (loginPassword.getBytes());
            String responseType = "json";

            //Get Topology from ODL REST interface
            URL getUrl =
                    new URL("http://localhost:8181/restconf/operational/opendaylight-inventory:nodes/node/" +
                            nodeName +
                            "/node-connector/" +
                            nodeLink +
                            "/opendaylight-port-statistics:flow-capable-node-connector-statistics");

            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/" + responseType);
            connection.setRequestProperty("Authorization", "Basic " + encoded);


            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                JSONObject json = new JSONObject(readAll(reader));

                //System.out.println(json.toString());

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonParser jp = new JsonParser();
                JsonElement je = jp.parse(json.toString());
                String prettyJsonString = gson.toJson(je);

                //System.out.println(prettyJsonString);

          JSONObject connector =
                        (JSONObject) json.get("opendaylight-port-statistics:flow-capable-node-connector-statistics" +
                                //".packets" +
                                //".received" +
                                "");

            JSONObject packets = (JSONObject) connector.get("packets");

            int value = packets.getInt(param);

            connection.disconnect();

            return value;
        }
        catch   (Exception ex)
        {
            //ex.printStackTrace();
        }

        return 0;

    }

    private static int ODLRESTNodeLinkByteParamExtraction(int nodeNumber, int linkNumber, String param)
    {
           try {
               JSONObject resp = ODLRESTHelper.ODLPortStatisticsJSON(nodeNumber, linkNumber);
               JSONObject json_bytes = (JSONObject) resp.get("bytes");
               int result = json_bytes.getInt(param);
               return result;
           } catch (Exception ex)
           {
               return 0;
           }
    }

    private static int ODLRESTNodeLinkParamExtraction(int nodeNumber, int linkNumber, String param)
    {
        try
        {
            JSONObject resp = ODLRESTHelper.ODLPortStatisticsJSON(nodeNumber, linkNumber);
            int result = resp.getInt(param);
            return result;

        }catch (Exception ex)
        {
            return 0;
        }

    }

    private static int ODLRESTNodeLinkInstantaneousRateTXParamExtraction(int nodeNumber, int linkNumber, int prevTXBytes)
    {
        try {
            int currTXBytes = ODLRESTNodeLinkByteParamExtraction(nodeNumber, linkNumber, "transmitted");
            int result = (currTXBytes - prevTXBytes);
            return result;
        }
        catch(Exception ex)
        {
            return 0;
        }

    }

    private static int ODLRESTNodeLinkInstantaneousRateTXRatioParamExtraction(int nodeNumber, int linkNumber, int prevTXBytes, int max_bit_bandwidth)
    {
        try
        {
            int bytes_per_second = ODLRESTNodeLinkInstantaneousRateTXParamExtraction(nodeNumber, linkNumber, prevTXBytes);
            int bits_per_second = bytes_per_second * 8;

            //Return percentage between (0 and 100)
            int result = 100 * (bits_per_second / max_bit_bandwidth);
            return result;
        }
        catch(Exception ex)
        {
            return 0;
        }
    }

    private static JSONObject ODLPortStatisticsJSON(int nodeNumber, int linkNumber)
    {
        String nodeName = "openflow:" + nodeNumber;
        String nodeLink = nodeName + ":" + linkNumber;

        //Get current topology of currently running ODL instance and print it
        //feature:install odl-restconf-all must be installed
        try {
            String loginPassword = "admin" + ":" + "admin";
            String encoded = new sun.misc.BASE64Encoder().encode(loginPassword.getBytes());
            String responseType = "json";

            //Get Topology from ODL REST interface
            URL getUrl =
                    new URL("http://localhost:8181/restconf/operational/opendaylight-inventory:nodes/node/" +
                            nodeName +
                            "/node-connector/" +
                            nodeLink +
                            "/opendaylight-port-statistics:flow-capable-node-connector-statistics");

            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/" + responseType);
            connection.setRequestProperty("Authorization", "Basic " + encoded);


            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            JSONObject json = new JSONObject(readAll(reader));

            connection.disconnect();

            //System.out.println(json.toString());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(json.toString());
            String prettyJsonString = gson.toJson(je);

            //System.out.println(prettyJsonString);

            JSONObject connector =
                    (JSONObject) json.get("opendaylight-port-statistics:flow-capable-node-connector-statistics");


            return connector;
        }
        catch(Exception e)
        {

        }
        finally
        {

        }

        return new JSONObject();
    }


    public static int getMetricValueByName(String metric_name)
    {
        int nodeNumber = getNodeNumberFromMetricName(metric_name);
        int linkNumber = getLinkNumberFromLinkName(metric_name);
        metric_name = getMetricType(metric_name);
        int prevTXBytes;
        int result = 0;

        switch (metric_name)
        {
            case "packets_received_total":
                result =  ODLRESTHelper.ODLRESTNodeLinkPacketParamExtraction(nodeNumber, linkNumber, "received");
                break;

            case "packets_sent_total":
                result = ODLRESTHelper.ODLRESTNodeLinkPacketParamExtraction(nodeNumber, linkNumber, "transmitted");
                break;

            case "collision_count":
                result = ODLRESTHelper.ODLRESTNodeLinkParamExtraction(nodeNumber, linkNumber, "collision-count");
                break;

            case "bytes_received_total":
                result = ODLRESTHelper.ODLRESTNodeLinkByteParamExtraction(nodeNumber, linkNumber, "received");
                break;

            case "bytes_sent_total":
                result = ODLRESTHelper.ODLRESTNodeLinkByteParamExtraction(nodeNumber, linkNumber, "transmitted");
                break;

            case "instantaneous_transmitted_rate":
                prevTXBytes = ODLNodeInstrumetation.prevTXBytes[nodeNumber][linkNumber];
                if(prevTXBytes == 0) return 0;
                result = ODLRESTHelper.ODLRESTNodeLinkInstantaneousRateTXParamExtraction(nodeNumber, linkNumber,
                        prevTXBytes);

                break;

            case "instantaneous_transmitted_rate_ratio":
                prevTXBytes = ODLNodeInstrumetation.prevTXBytes[nodeNumber][linkNumber];
                if(prevTXBytes == 0) return 0;
                result = ODLRESTHelper.ODLRESTNodeLinkInstantaneousRateTXRatioParamExtraction(nodeNumber, linkNumber,
                        prevTXBytes, ODLNodeInstrumetation.linkBW);
                break;
        }

        return result;
    }

    public static void updatePrevTXBytes()
    {
        for(int node = 1; node <= 20; node++)
            for(int link = 1; link <= 4; link++)
            {
                try {
                    ODLNodeInstrumetation.prevTXBytes[node][link] =
                            ODLRESTHelper.ODLRESTNodeLinkByteParamExtraction(node, link, "transmitted");
                }
                catch(Exception e)
                {
                    ODLNodeInstrumetation.prevTXBytes[node][link] = 0;
                }
            }

    }


    public static int getNodeNumberFromMetricName(String metric_name)
    {
        int startIndex = 5;
        String processedString = metric_name.substring(startIndex);
        processedString = processedString.substring(0, processedString.indexOf("_"));
        return Integer.parseInt(processedString);
    }

    public static int getLinkNumberFromLinkName(String metric_name)
    {
        int startIndex = 12 + (ODLRESTHelper.getNodeNumberFromMetricName(metric_name) > 9 ? 1 : 0);
        return Integer.parseInt(metric_name.substring(startIndex, startIndex + 1));
    }

    public static String getMetricType(String metric_name)
    {
        int startIndex = 14 + (ODLRESTHelper.getNodeNumberFromMetricName(metric_name) > 9 ? 1 : 0);
        return metric_name.substring(startIndex);
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}

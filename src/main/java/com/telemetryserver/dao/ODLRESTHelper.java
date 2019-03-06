/*
    Author: KMN
    Description: Gets Data from MDSAL datastore via RESTConf.

 */

package com.telemetryserver.dao;

import com.google.gson.*;
import com.telemetryserver.Instrumentation.ODLNodeInstrumetation;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ODLRESTHelper
{
    public static int ODLRESTNodeLinkPacketParamExtraction(JSONObject resp, String param)
    {
        try
        {
            JSONObject json_bytes = (JSONObject) resp.get("packets");
            return json_bytes.getInt(param);
        }
        catch(Exception ex)
        {
            return 0;
        }
    }

    public static int ODLRESTNodeLinkByteParamExtraction(JSONObject resp, String param)
    {

           try
           {
               JSONObject json_bytes = (JSONObject) resp.get("bytes");
               return json_bytes.getInt(param);
           }
           catch(Exception ex) {
               return 0;
           }
    }

    public static int ODLRESTNodeLinkParamExtraction(JSONObject resp, String param)
    {
        try
        {
            return resp.getInt(param);
        } catch (Exception ex)
        {
            return 0;
        }
    }

    public static int ODLRESTNodeLinkInstantaneousRateTXParamExtraction(JSONObject resp, int prevTXBytes)
    {

            int currTXBytes = ODLRESTNodeLinkByteParamExtraction(resp, "transmitted");

            //This situation occurs if mininet restarted
            if(currTXBytes < prevTXBytes) return 0;

            return (currTXBytes - prevTXBytes);
    }

    public static int ODLRESTNodeLinkInstantaneousRateTXRatioParamExtraction(JSONObject resp, int prevTXBytes, int max_bit_bandwidth)
    {

        int bytes_per_period = ODLRESTNodeLinkInstantaneousRateTXParamExtraction(resp, prevTXBytes);
        double seconds_per_period = ODLNodeInstrumetation.samplingPeriodMS / 1000;
        double bytes_per_second = bytes_per_period / seconds_per_period;
        double bits_per_second = bytes_per_second * 8;
        int ratio = (int) (100 * bits_per_second / max_bit_bandwidth);
        //Return percentage between (0 and 100)
        return ratio;
    }

    public static JSONObject ODLPortStatisticsJSON(int nodeNumber, int linkNumber)
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


            connection.disconnect();

            return connector;
        }
        catch(Exception e)
        {
                //e.printStackTrace();
                return new JSONObject();
        }
        finally
        {

        }

    }


    public static int getMetricValueByName(String input_metric_name)
    {
        int nodeNumber = getNodeNumberFromMetricName(input_metric_name);
        int linkNumber = getLinkNumberFromLinkName(input_metric_name);
        String metric_name = getMetricType(input_metric_name);
        int prevTXBytes;
        int result = 0;

        JSONObject resp = ODLNodeInstrumetation.jsonObjects[nodeNumber][linkNumber];
        switch (metric_name)
        {
            case "packets_received_total":
                result =  ODLRESTHelper.ODLRESTNodeLinkPacketParamExtraction(resp, "received");
                break;

            case "packets_sent_total":
                result = ODLRESTHelper.ODLRESTNodeLinkPacketParamExtraction(resp, "transmitted");
                break;

            case "collision_count":
                result = ODLRESTHelper.ODLRESTNodeLinkParamExtraction(resp, "collision-count");
                break;

            case "bytes_received_total":
                result = ODLRESTHelper.ODLRESTNodeLinkByteParamExtraction(resp, "received");
                break;

            case "bytes_sent_total":
                result = ODLRESTHelper.ODLRESTNodeLinkByteParamExtraction(resp, "transmitted");
                break;

            case "instantaneous_transmitted_rate":
                prevTXBytes = ODLNodeInstrumetation.prevTXBytes[nodeNumber][linkNumber];
                if(prevTXBytes == 0) return 0;
                result = ODLRESTHelper.ODLRESTNodeLinkInstantaneousRateTXParamExtraction(resp,
                        prevTXBytes);

                //This should only be run once (for one node : 2) - (path computation)
                if(!ODLNodeInstrumetation.pathsComputed && nodeNumber == 10)
                {
                    double bytes = (double) result / (ODLNodeInstrumetation.samplingPeriodMS / 1000);
                    double d_alpha = ((bytes * 8) / ODLNodeInstrumetation.linkBW);
                    ODLNodeInstrumetation.alpha += d_alpha;
                    System.out.println("d_alpha " + d_alpha);
                    ODLNodeInstrumetation.alphaComputed = true;
                }

                if(!ODLNodeInstrumetation.pathsComputed && nodeNumber == 11)
                {
                    ODLNodeInstrumetation.pathsComputed = true;
                }

                break;

            case "instantaneous_transmitted_rate_ratio":
                prevTXBytes = ODLNodeInstrumetation.prevTXBytes[nodeNumber][linkNumber];
                if(prevTXBytes == 0) return 0;
                result = ODLRESTHelper.ODLRESTNodeLinkInstantaneousRateTXRatioParamExtraction(resp,
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
                    JSONObject resp = ODLNodeInstrumetation.jsonObjects[node][link];
                    ODLNodeInstrumetation.prevTXBytes[node][link] =
                            ODLRESTHelper.ODLRESTNodeLinkByteParamExtraction(resp, "transmitted");
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

    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}

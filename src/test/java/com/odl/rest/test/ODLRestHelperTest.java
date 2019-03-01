package com.odl.rest.test;

/*
  Author: KMN
  Description: Tests ODL REST API
  Note: In order tests in this file to run, ODL has to be running and connected to some network
  In order to access any prometheus based stuff and run them prometheus also has to be running
 */

import com.google.gson.*;
import com.odl.rest.XmlFormatter;
import com.telemetryserver.Instrumentation.ODLNodeInstrumetation;
import com.telemetryserver.dao.ODLRESTHelper;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class ODLRestHelperTest
{
    @Test
    public void testODLRESTTopologyInterface()
    {
        //Get current topology of currently running ODL instance and print it
        //feature:install odl-restconf-all must be installed
        try
        {
            String loginPassword = "admin" + ":" + "admin";
            String encoded = new sun.misc.BASE64Encoder().encode (loginPassword.getBytes());
            String responseType = "json";

            //Get Topology from ODL REST interface
            URL getUrl = new URL("http://localhost:8181/restconf/operational/network-topology:network-topology/");
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/" + responseType);
            connection.setRequestProperty("Authorization", "Basic " + encoded);


            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

           if(responseType.equals("json"))
           {
               JSONObject json = new JSONObject(readAll(reader));

               //System.out.println(json.toString());

               Gson gson = new GsonBuilder().setPrettyPrinting().create();
               JsonParser jp = new JsonParser();
               JsonElement je = jp.parse(json.toString());
               String prettyJsonString = gson.toJson(je);

               System.out.println(prettyJsonString);
           }
           else
           {
               String line = reader.readLine();
               if (line != null)
               {
                    System.out.println(line);
                    System.out.println();
               }
           }



            Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
            connection.disconnect();
        }
        catch   (Exception ex)
        {
            ex.printStackTrace();
        }


    }

    @Test
    public void testODLRESTNodesInterface()
    {
        //Get current topology of currently running ODL instance and print it
        //feature:install odl-restconf-all must be installed
        try
        {
            String loginPassword = "admin" + ":" + "admin";
            String encoded = new sun.misc.BASE64Encoder().encode (loginPassword.getBytes());
            String responseType = "json";

            //Get Topology from ODL REST interface
            URL getUrl = new URL("http://localhost:8181/restconf/operational/opendaylight-inventory:nodes");
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/" + responseType);
            connection.setRequestProperty("Authorization", "Basic " + encoded);


            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            if(responseType.equals("json"))
            {
                JSONObject json = new JSONObject(readAll(reader));

                //System.out.println(json.toString());

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonParser jp = new JsonParser();
                JsonElement je = jp.parse(json.toString());
                String prettyJsonString = gson.toJson(je);

                System.out.println(prettyJsonString);
            }
            else
            {
                String line = reader.readLine();
                if (line != null)
                {
                    System.out.println(line);
                    System.out.println();
                }
            }



            Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
            connection.disconnect();
        }
        catch   (Exception ex) {
            ex.printStackTrace();
        }

    }


    @Test
    public void testODLRESTNodeLinkInterface()
    {
        String nodeName = "openflow:1";
        String nodeLink = "openflow:1:1";

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

            if(responseType.equals("json"))
            {
                JSONObject json = new JSONObject(readAll(reader));

                //System.out.println(json.toString());

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonParser jp = new JsonParser();
                JsonElement je = jp.parse(json.toString());
                String prettyJsonString = gson.toJson(je);

                System.out.println(prettyJsonString);
            }
            else
            {
                String line = reader.readLine();
                if (line != null)
                {
                    System.out.println(line);
                    System.out.println();
                }
            }



            Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
            connection.disconnect();
        }
        catch   (Exception ex)
        {
            ex.printStackTrace();
        }





    }

    @Test
    public void testODLRESTNodeLinkParamExtraction()
    {
        String nodeName = "openflow:1";
        String nodeLink = "openflow:1:1";

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

            if(responseType.equals("json"))
            {
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

                int receivedPackets = packets.getInt("received");



                System.out.println("\n Number of Received Packets on link : " + receivedPackets);
            }
            else
            {
                String line = reader.readLine();
                if (line != null)
                {
                    System.out.println(line);
                    System.out.println();
                }
            }



            Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
            connection.disconnect();
        }
        catch   (Exception ex)
        {
            ex.printStackTrace();
        }


    }

    @Test
    public void testODLRESTNodeLinkByteParamExtraction()
    {
        int nodeNumber = 1;
        int linkNumber = 1;

        JSONObject resp = ODLRESTHelper.ODLPortStatisticsJSON(nodeNumber, linkNumber);
        int txBytes = ODLRESTHelper.ODLRESTNodeLinkByteParamExtraction(resp, "transmitted");
        int rxBytes = ODLRESTHelper.ODLRESTNodeLinkByteParamExtraction(resp, "received");

        Assert.assertNotEquals(txBytes, -1);
        Assert.assertNotEquals(rxBytes, -1);

    }

    @Test
    public void testODLRESTNodeLinkInstantaneousRateTXParamExtraction()
    {
        int nodeNumber = 1;
        int linkNumber = 1;

        int prevTXBytes = ODLNodeInstrumetation.prevTXBytes[nodeNumber][linkNumber];
        JSONObject resp = ODLRESTHelper.ODLPortStatisticsJSON(nodeNumber, linkNumber);
        int rate = ODLRESTHelper.ODLRESTNodeLinkInstantaneousRateTXParamExtraction(resp,  prevTXBytes);

        Assert.assertNotEquals(rate, -1);
    }

    @Test
    public void testODLRESTNodeLinkInstantaneousRateTXRatioParamExtraction()
    {
        int nodeNumber = 1;
        int linkNumber = 1;

        int samplingPeriodMS = 10000;
        ODLNodeInstrumetation.samplingPeriodMS = samplingPeriodMS;

        int prevTXBytes = ODLNodeInstrumetation.prevTXBytes[nodeNumber][linkNumber];
        JSONObject resp = ODLRESTHelper.ODLPortStatisticsJSON(nodeNumber, linkNumber);
        int ratio = ODLRESTHelper.ODLRESTNodeLinkInstantaneousRateTXRatioParamExtraction(resp,
                prevTXBytes, ODLNodeInstrumetation.linkBW);

        Assert.assertNotEquals(ratio, -1);
    }

    @Test
    public void ODLRESTHelper_getNodeNumberFromMetricName()
    {
        String test_String = "Node_23_Link_1_dummy_metric";
        int extractedNode = ODLRESTHelper.getNodeNumberFromMetricName(test_String);

        Assert.assertEquals(extractedNode, 23);

    }

    @Test
    public void ODLRESTHelper_getLinkNumberFromLinkName()
    {
        String test_String = "Node_23_Link_1_dummy_metric";
        int extractedLink = ODLRESTHelper.getLinkNumberFromLinkName(test_String);

        Assert.assertEquals(extractedLink, 1);

    }

    @Test
    public void ODLRESTHelper_getMetricType()
    {
        String test_String = "Node_23_Link_1_dummy_metric";
        String extractedMetric = ODLRESTHelper.getMetricType(test_String);

        Assert.assertEquals(extractedMetric , "dummy_metric");

    }
    
    private static String readAll(Reader rd) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}

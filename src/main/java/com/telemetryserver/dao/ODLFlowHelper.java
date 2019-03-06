/*
    Author: KMN
    Description: {Creates, Modifies, Gets ,Sends} Flows and Flow tables
    from ODL enabled switches.
 */

package com.telemetryserver.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.util.ajax.JSON;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Assert;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

public class ODLFlowHelper {

    public static JSONObject getFirstODLFlow(int nodeNumber, int linkNumber) {
        //http://localhost:8181/restconf/operational/opendaylight-inventory:nodes/node/openflow:1/flow-node-inventory:table/0/
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
                            "/flow-node-inventory:table/0/"
                    );

            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/" + responseType);
            connection.setRequestProperty("Authorization", "Basic " + encoded);


            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            if (responseType.equals("json")) {
                //JSONArray jsonArray = new JSONArray(readAll(reader));
                //JSONObject json = jsonArray.getJSONObject(1);

                JSONObject json0 = new JSONObject(ODLRESTHelper.readAll(reader));
                JSONObject json = json0.getJSONArray("flow-node-inventory:table")
                        .getJSONObject(0)
                        .getJSONArray("flow")
                        .getJSONObject(0);


                //System.out.println(json.toString());

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonParser jp = new JsonParser();
                JsonElement je = jp.parse(json.toString());
                String prettyJsonString = gson.toJson(je);


                //System.out.println(prettyJsonString);
                return json;

            } else {
                String line = reader.readLine();
                if (line != null) {
                    System.out.println(line);
                    System.out.println();
                }
            }


            Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
            connection.disconnect();


        } catch (Exception ex) {
            ex.printStackTrace();
            return new JSONObject();
        }

        return new JSONObject();
    }

    public static JSONObject configureFlow(String id, int priority, int dest_linkNumber, int nodeNumber, int tableNumber) {
        JSONObject flow = getFirstODLFlow(1, 1);
        flow.put("cookie", 0);
        flow.getJSONObject("opendaylight-flow-statistics:flow-statistics").put("packet-count", 0);
        flow.getJSONObject("opendaylight-flow-statistics:flow-statistics").put("byte-count", 0);

        flow.put("id", id);
        flow.put("priority", priority);

        //Set Match
        flow.getJSONObject("match").remove("in-port");
        flow.getJSONObject("match").put("ipv4-destination", "10.0.0." + nodeNumber + "/24");

        //Reset opendaylight-flow-statistics:flow-statistics
        flow.getJSONObject("opendaylight-flow-statistics:flow-statistics")
                .getJSONObject("duration")
                .put("nanosecond", 0);


        flow.getJSONObject("opendaylight-flow-statistics:flow-statistics")
                .getJSONObject("duration")
                .put("second", 0);


        JSONObject test = flow.getJSONObject("instructions").getJSONArray("instruction")
                .getJSONObject(0);
        //Set Instruction

        flow.getJSONObject("instructions").getJSONArray("instruction")
                .getJSONObject(0)
                .getJSONObject("apply-actions")
                .getJSONArray("action")
                .getJSONObject(0)
                .getJSONObject("output-action")
                .put("output-node-connector", dest_linkNumber);

        return flow;
    }

    public static String configureFromXMLFlow(String flowName, String id, int priority, int dest_linkNumber, int dest_nodeNumber) {
        String xmlFlow =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                        "<flow xmlns=\"urn:opendaylight:flow:inventory\">\n" +
                        "    <priority>" + priority + "</priority>\n" +
                        "    <flow-name>" + flowName + "</flow-name>\n" +
                        "    <match>\n" +
                        "        <ethernet-match>\n" +
                        "            <ethernet-type>\n" +
                        "                <type>2048</type>\n" +
                        "            </ethernet-type>\n" +
                        "        </ethernet-match>\n" +
                        "        <ipv4-destination>10.0.0." + dest_nodeNumber + "/24</ipv4-destination>\n" +
                        "    </match>\n" +
                        "    <id>" + id + "</id>\n" +
                        "    <table_id>0</table_id>\n" +
                        "    <instructions>\n" +
                        "        <instruction>\n" +
                        "            <order>0</order>\n" +
                        "            <apply-actions>\n" +
                        "                <action>\n" +
                        "                    <order>0</order>\n" +
                        "                    <output-action>\n" +
                        "                        <output-node-connector>"+ dest_linkNumber +"</output-node-connector>\n" +
                        "                    </output-action>\n" +
                        //"                     <dec-nw-ttl/>\n" +
                        "                </action>\n" +
                        "            </apply-actions>\n" +
                        "        </instruction>\n" +
                        "    </instructions>\n" +
                        "</flow>";


        return xmlFlow;


    }

    /*


     */

    public static int sendFlow(String flow, int nodeNumber, int destNodeOfFlow) {
        try {
            String nodeName = "openflow:" + nodeNumber;
            String loginPassword = "admin" + ":" + "admin";
            String encoded = new sun.misc.BASE64Encoder().encode(loginPassword.getBytes());
            String responseType = "xml";


            String urlString = "http://localhost:8181/restconf/config/opendaylight-inventory:nodes/node/" +
                    nodeName +
                    "/table/0/flow/" + destNodeOfFlow;


            URL url = new URL(urlString);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");
            httpCon.setRequestProperty("Authorization", "Basic " + encoded);
            httpCon.setRequestProperty("Content-type", "application/" + responseType);
            httpCon.setRequestProperty("Accept", "application/" + responseType);

            OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());

            out.write(flow);
            out.flush();
            out.close();

            //int responseCode = httpCon.getResponseCode();
            InputStream inputStream = httpCon.getInputStream();

            return httpCon.getResponseCode();

        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println(flow);
            return -1;
        }

    }

}

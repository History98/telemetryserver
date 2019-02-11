package com.telemetryserver.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
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
            ex.printStackTrace();
        }

        return -1;

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

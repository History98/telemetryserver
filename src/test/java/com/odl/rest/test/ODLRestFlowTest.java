package com.odl.rest.test;

import com.google.gson.*;
import com.telemetryserver.dao.ODLFlowHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ODLRestFlowTest
{
    @Test
    public void testODLGetFlowTable()
    {

    }

    @Test
    public void testODLGetFlow()
    {
        //http://localhost:8181/restconf/operational/opendaylight-inventory:nodes/node/openflow:1/flow-node-inventory:table/0/
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
                            "/flow-node-inventory:table/0/"
                    );

            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/" + responseType);
            connection.setRequestProperty("Authorization", "Basic " + encoded);


            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            if(responseType.equals("json"))
            {
                //JSONArray jsonArray = new JSONArray(readAll(reader));
                //JSONObject json = jsonArray.getJSONObject(1);

                JSONObject json0 = new JSONObject(readAll(reader));
                JSONObject json = json0.getJSONArray("flow-node-inventory:table")
                        .getJSONObject(0)
                        .getJSONArray("flow")
                        .getJSONObject(0)
                        ;





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
    public void testConfigureFlow()
    {
        //JSONObject result = ODLFlowHelper.configureFlow("TEST", 10, 4, 1, 0);

        String result = ODLFlowHelper.configureFromXMLFlow("testFlow", "1", 2, 1, 1);

        System.out.println(result);

        int responseCode = ODLFlowHelper.sendFlow(result, 1, 1);

        Assert.assertEquals(200, responseCode);
    }




    public static String readAll(Reader rd) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}

package com.odl.rest.test;

/*
  Author: KMN
  Description: Tests ODL REST API
  Note: In order tests in this file to run, ODL has to be running and connected to some network
  In order to access any prometheus based stuff and run them prometheus also has to be running
 */

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ODLRestTest
{
    @Test
    public void testODLRESTInterfaces()
    {
        //Get current topology of currently running ODL instance and print it
        //feature:install odl-restconf-all must be installed
        try
        {
            String loginPassword = "admin" + ":" + "admin";
            String encoded = new sun.misc.BASE64Encoder().encode (loginPassword.getBytes());

            //Get Topology from ODL REST interface
            URL getUrl = new URL("http://localhost:8181/restconf/operational/network-topology:network-topology/");
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/xml");
            connection.setRequestProperty("Authorization", "Basic " + encoded);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = reader.readLine();
            while (line != null)
            {
                System.out.println(line);
                line = reader.readLine();
            }
            Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
            connection.disconnect();
        }
        catch   (Exception ex)
        {
            ex.printStackTrace();
        }


    }
}

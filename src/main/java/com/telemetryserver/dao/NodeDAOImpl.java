/*
  Author: KMN
  Desrc: Data Access Object for Accessing PostgreSQL database.
         only run if PostgresQL Telemtery database is running.

 */


package com.telemetryserver.dao;

import com.telemetryserver.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.util.Properties;

public class NodeDAOImpl implements NodeDAO
{

    private static Properties readProperties()
    {
        Properties props = new Properties();
        Path myPath = Paths.get("src/main/resources/database.properties");

        try
        {
            BufferedReader bf = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);
            props.load(bf);
        }
        catch (IOException ex)
        {
        }

        return props;
    }

    //SQL Methods
    public Connection getConnection() throws SQLException
    {
        Properties props = readProperties();

        String url = props.getProperty("Telemetry_Database.url");
        String user = props.getProperty("Telemetry_Database.user");
        String password = props.getProperty("Telemetry_Database.password");

        return DriverManager.getConnection(url, user, password);
    }


    public Connection getRemoteConnection() throws SQLException
    {
        Properties props = readProperties();

        String url = props.getProperty("Remote_Telemetry_Database.url");
        String user = props.getProperty("Remote_Telemetry_Database.user");
        String password = props.getProperty("Remote_Telemetry_Database.password");

        String test = "foo";
        String remoteURL = props.getProperty("Remote_Telemetry_Database.fullurl");

        return DriverManager.getConnection(url, user, password);
    }



    private void closeConnection(Connection connection)
    {
        if (connection == null)
            return;
        try
        {
            connection.close();
        }
        catch (SQLException ex)
        {

        }
    }

    public List<Node> getAllNodes()
    {
        List<Node> result = new ArrayList<>();
        Connection conn = null;
        String sql = "select * from nodes";

        try
        {
            conn = getRemoteConnection(); //getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet nodeResultSet = statement.executeQuery();
            while (nodeResultSet.next())
            {
                Node node = new Node();
                int id = nodeResultSet.getInt("id");
                node.set_id(id);

                String name = nodeResultSet.getString("name");
                node.set_name(name);

                String type = nodeResultSet.getString("type");
                node.set_type(type);

                /*Add Link metrics*/
                List<LinkMetrics> listLinkMetrics = new ArrayList<>();
                sql = "select * from link_metrics where node_dest_id=" + id;
                statement = conn.prepareStatement(sql);
                ResultSet linkResultSet = statement.executeQuery();
                while(linkResultSet.next())
                {
                    LinkMetrics link = new LinkMetrics();
                    int link_id = linkResultSet.getInt("link_id");
                    int node_src_id = linkResultSet.getInt("node_src_id");;
                    int node_dest_id = linkResultSet.getInt("node_dest_id");;
                    float signal_to_noise_ratio = linkResultSet.getFloat("signal_to_noise_ratio");;
                    float packet_error_rate = linkResultSet.getFloat("link_id");;
                    int time = linkResultSet.getInt("time");

                    link.setAll(link_id, node_src_id, node_dest_id,signal_to_noise_ratio,packet_error_rate,time);
                    listLinkMetrics.add(link);
                }
                node.set_linkMetrics(listLinkMetrics);


                /*Add Node metrics*/
                NodeMetrics nodeMetrics = new NodeMetrics();
                sql = "select * from node_metrics where id=" + id;
                statement = conn.prepareStatement(sql);
                ResultSet nodeMetricsResultSet = statement.executeQuery();
                if(nodeMetricsResultSet.next());
                {
                    //id = nodeMetricsResultSet.getInt("id");
                    float utilisation = nodeMetricsResultSet.getFloat("utilisation");
                    float average_power = nodeMetricsResultSet.getFloat("average_power");

                    int time = nodeMetricsResultSet.getInt("time");

                    nodeMetrics.setAll(id, utilisation, average_power, time);

                }
                node.set_nodeMetrics(nodeMetrics);

                result.add(node);
            }

        }
        catch (Exception ex)
        {
            System.out.print("Excepion of type" + ex.toString());
            ex.printStackTrace();
        }
        finally
        {
            closeConnection(conn);
        }




        return result;
    }



    public List<MetricCatergory> findAllCategories()
    {
        return null;
    }

    public Node getNodeByID(int nodeID)
    {
        return null;
    }

    public void insert(Node node)
    {

    }

    public void delete(int nodeID)
    {

    }
}

package com.telemetryserver.Instrumentation;

import io.prometheus.client.Gauge;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Node_1_Instrumented_Average_Power extends HttpServlet
{
    private static Node_1_Instrumented_Average_Power  _instance = null;


    public static final Gauge node_1_average_power = Gauge.build()
            .name("node_1_average_power").help("node_1_average_power").register();

    public void setNode_1_average_power(float val)
    {
        node_1_average_power.set(val);
    }

    public static Node_1_Instrumented_Average_Power getInstance()
    {
        if (_instance == null)
            _instance = new Node_1_Instrumented_Average_Power();

        return _instance;
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException
    {
        resp.getWriter().println("Hello from Node_1_Instrumented_Average_Power!!!" + node_1_average_power.get());
    }
}

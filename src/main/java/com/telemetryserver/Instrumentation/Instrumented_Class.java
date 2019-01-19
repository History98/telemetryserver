package com.telemetryserver.Instrumentation;

import io.prometheus.client.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Instrumented_Class extends HttpServlet
{
    private static Instrumented_Class _instance = null;

    public static final Gauge metric_1 = Gauge.build().name("metric_1").help("metric_1").register();
    //public static final Gauge metric_2 = Gauge.build().name("metric_2").help("metric_2").register();

    public static Instrumented_Class getInstance()
    {
        if (_instance == null)
            _instance = new Instrumented_Class();

        return _instance;
    }

    //Getters and Setters

    public static void setMetric_1(double val) { metric_1.set(val); }

    public static double getMetric_1() { return metric_1.get(); }

    //public static void setMetric_2(double val) { metric_2.set(val); }

    //public static double getMetric_2() { return metric_2.get(); }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {

        double metric_1 = Instrumented_Class.getMetric_1();
        Instrumented_Class.setMetric_1(metric_1 + 1);

        //double metric_2 = Instrumented_Class.getMetric_2();
        //Instrumented_Class.setMetric_2(metric_2 + 2);

        resp.getWriter().println("Hello from Instrumented_Class!!!," +
                " metric_1 = " + metric_1
                //+ " metric_2 = " + metric_2
        );
    }
}

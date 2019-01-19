package com.telemetryserver.Instrumentation;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomMetricsServlet extends HttpServlet {
    private CollectorRegistry registry;

    public CustomMetricsServlet() {
        this(CollectorRegistry.defaultRegistry);
    }

    public CustomMetricsServlet(CollectorRegistry registry) {
        this.registry = registry;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("text/plain; version=0.0.4; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        try {
            //TextFormat.write004(writer, this.registry.metricFamilySamples());
            String[] var1 = {"foo1"};
            String[] var2 = {"foo2"};
            String[] bothVars = {"foo1", "foo2"};

            //TextFormat.write004(writer, this.registry.filteredMetricFamilySamples(this.parse(req)));
            //TextFormat.write004(writer, this.registry.filteredMetricFamilySamples(new HashSet(Arrays.asList(var1))));
            //TextFormat.write004(writer, this.registry.filteredMetricFamilySamples(new HashSet(Arrays.asList(var2))));
            TextFormat.write004(writer, this.registry.filteredMetricFamilySamples(new HashSet(Arrays.asList(bothVars))));
            writer.flush();
        } finally {
            writer.close();
        }

    }

    private Set<String> parse(HttpServletRequest req)
    {
        String[] includedParam = {"foo2"};//req.getParameterValues("name[]");
        return (Set)(includedParam == null ? Collections.emptySet() : new HashSet(Arrays.asList(includedParam)));
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
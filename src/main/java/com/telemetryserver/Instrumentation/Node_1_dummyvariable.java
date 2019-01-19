package com.telemetryserver.Instrumentation;

import io.prometheus.client.*;

public class Node_1_dummyvariable {

    static final Gauge inprogressRequests = Gauge.build()
                    .name("inprogress_requests").help("Inprogress requests.").register();

    //static final Gauge inprogressRequests2 = Gauge.build()
    //        .name("inprogress_requests2").help("Inprogress requests2.").register();

    public static void setProgress1(double value)
    {
        inprogressRequests.set(value);
    }

    public static void setProgress2(double value)
    {
        //inprogressRequests2.set(value);
    }

}

package com.telemetryserver.dao;

import com.telemetryserver.Instrumentation.NodeMonitoring;
import com.telemetryserver.Instrumentation.ODLNodeInstrumetation;
import com.telemetryserver.model.Node;

import java.util.List;
import java.util.TimerTask;

public class ODLParamPollScheduler extends TimerTask
{
    @Override
    public void run()
    {
        System.out.print("\nRunning ODLParamPollScheduler\n");

        //Get Network States
        ODLNodeInstrumetation.updateLinkStateObjects();

        //only for the first 20 nodes
        ODLNodeInstrumetation.updateODLMetricsAll();

        //Update prevTX
        ODLNodeInstrumetation.updatePrevTXBytes();
    }

    @Override
    public boolean cancel()
    {
        return super.cancel();
    }

    @Override
    public long scheduledExecutionTime()
    {
        return super.scheduledExecutionTime();
    }
}

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

        //Only for first 3 nodes for now
        ODLNodeInstrumetation.updateODLPacketMetrics();
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

/*
  Author: KMN
  Description: Polls the database and displays the

 */



package com.telemetryserver.dao;


import java.util.TimerTask;

import com.telemetryserver.Instrumentation.NodeMonitoring;
import com.telemetryserver.model.*;
import com.telemetryserver.dao.*;
import java.util.*;

public class DatabasePollScheduler extends TimerTask
{

    @Override
    public void run()
    {
        System.out.print("\nRunning DatabasePollScheduler\n");

        List<Node> nodes = new NodeDAOImpl().getAllNodes();
        NodeMonitoring.updateNodeInstrumentation(nodes);
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

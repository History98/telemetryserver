package com.telemetryserver.dao;

import java.sql.SQLException;
import java.util.List;

import com.telemetryserver.model.*;

public interface NodeDAO
{
    public List<Node> getAllNodes();

    public List<MetricCatergory>findAllCategories();

    public Node getNodeByID(int nodeID);

    public void insert(Node node);

    public void delete(int nodeID);
}

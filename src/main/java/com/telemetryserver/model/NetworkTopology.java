/*
  Author: KMN
  Institution: LJMU
  Descr:
 */

package com.telemetryserver.model;

import java.util.ArrayList;
import java.util.List;
import com.telemetryserver.model.*;

public class NetworkTopology
{
    private static List<Node> _nodes = null;

    public static List<TypeCategory> typeCategoryList = getTypes();

    void NetworkTopology(List<Node> nodes)
    {
        _nodes = nodes;
    }

    private static List<TypeCategory> getTypes()
    {
        List<TypeCategory> result = new ArrayList<>();

        result.add(new TypeCategory(1, "Client"));
        result.add(new TypeCategory(2, "Distribution"));
        result.add(new TypeCategory(3, "PoP"));

        return result;
    }
}

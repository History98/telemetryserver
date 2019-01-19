/*
  Author: KMN
  Institution: LJMU
  Descr: 5G Node Model
 */


package com.telemetryserver.model;

import java.util.*;

public class Node
{
    //Private Members :- these map to database columns
    private int _id;
    private String _name;
    private String _type;

    private NodeMetrics _nodeMetrics = null;
    private List<LinkMetrics> _linkMetrics = null;
    private NodePosition _nodePosition = null;

    public int get_id() {
        return _id;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String type) {
        this._type = type;
    }

    public NodeMetrics get_nodeMetrics() {
        return _nodeMetrics;
    }

    public void set_nodeMetrics(NodeMetrics nodeMetrics) {
        this._nodeMetrics = nodeMetrics;
    }

    public List<LinkMetrics> get_linkMetrics() {
        return _linkMetrics;
    }

    public void set_linkMetrics(List<LinkMetrics> linkMetrics) {
        this._linkMetrics = linkMetrics;
    }

    public NodePosition get_nodePosition() { return _nodePosition; }

    public void set_nodePosition(NodePosition nodePosition) { this._nodePosition = nodePosition; }

    @Override
    public String toString() {
        return "Node{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _type='" + _type +
                '}';
    }
}

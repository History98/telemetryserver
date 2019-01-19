package com.telemetryserver.model;

public class NodePosition
{
    private int _node_id;
    private String _node_name;
    private int _Xposition;
    private int _Yposition;
    private int _Zposition;
    private int _time;

    public int get_node_id() {
        return _node_id;
    }

    public void set_node_id(int node_id) {
        this._node_id = node_id;
    }

    public String get_node_name() {
        return _node_name;
    }

    public void set_node_name(String node_name) {
        this._node_name = node_name;
    }

    public int get_Xposition() {
        return _Xposition;
    }

    public void set_Xposition(int Xposition) {
        this._Xposition = Xposition;
    }

    public int get_Yposition() {
        return _Yposition;
    }

    public void set_Yposition(int Yposition) {
        this._Yposition = Yposition;
    }

    public int get_Zposition() {
        return _Zposition;
    }

    public void set_Zposition(int Zposition) {
        this._Zposition = Zposition;
    }

    public int get_time() {
        return _time;
    }

    public void set_time(int time) {
        this._time = time;
    }
}

package com.telemetryserver.model;

public class TypeCategory
{
    private int _id;
    private String _name;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    TypeCategory(int id , String name)
    {
        this._id = id;
        this._name = name;
    }
}

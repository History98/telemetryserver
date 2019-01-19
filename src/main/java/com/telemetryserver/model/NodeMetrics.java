/*
  Author: KMN
  Institution: LJMU
  Descr:
 */

package com.telemetryserver.model;

public class NodeMetrics
{
    private int _id;
    private float _utilisation;
    private float _average_power;
    private int _time;

    public int get_id() {
        return _id;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public float get_utilisation() {
        return _utilisation;
    }

    public void set_utilisation(float utilisation) {
        this._utilisation = utilisation;
    }

    public float get_average_power() {
        return _average_power;
    }

    public void set_average_power(float average_power) {
        this._average_power = average_power;
    }

    public int get_time() {
        return _time;
    }

    public void set_time(int time) {
        this._time = time;
    }

    public void setAll(int id, float utilisation, float average_power, int time)
    {
        _id = id;
        _utilisation = utilisation;
        _average_power = average_power;
        _time = time;
    }

    @Override
    public String toString() {
        return "NodeMetrics{" +
                "_id=" + _id +
                ", _utilisation=" + _utilisation +
                ", _average_power=" + _average_power +
                ", _time=" + _time +
                '}';
    }
}

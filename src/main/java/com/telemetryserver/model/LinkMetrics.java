/*
  Author: KMN
  Institution: LJMU
  Descr:
 */

package com.telemetryserver.model;

public class LinkMetrics
{
    private int  _link_id;
    private int _node_src_id;
    private int _node_dest_id;
    private float _signal_to_noise_ratio;
    private float _packet_error_rate;
    private int _time;

    public int get_link_id() {
        return _link_id;
    }

    public void set_link_id(int link_id) {
        this._link_id = link_id;
    }

    public int get_node_src_id() {
        return _node_src_id;
    }

    public void set_node_src_id(int node_src_id) {
        this._node_src_id = node_src_id;
    }

    public int get_node_dest_id() {
        return _node_dest_id;
    }

    public void set_node_dest_id(int node_dest_id) {
        this._node_dest_id = node_dest_id;
    }

    public float get_signal_to_noise_ratio() {
        return _signal_to_noise_ratio;
    }

    public void set_signal_to_noise_ratio(float signal_to_noise_ratio) {
        this._signal_to_noise_ratio = signal_to_noise_ratio;
    }

    public float get_packet_error_rate() {
        return _packet_error_rate;
    }

    public void set_packet_error_rate(float packet_error_rate) {
        this._packet_error_rate = packet_error_rate;
    }

    public int get_time() {
        return _time;
    }

    public void set_time(int time) {
        this._time = time;
    }

    public void setAll(int link_id, int node_src_id, int node_dest_id, float signal_to_noise_ratio, float packet_error_rate, int time)
    {
        _link_id = link_id;
        _node_src_id = node_src_id;
        _node_dest_id = node_dest_id;
        _signal_to_noise_ratio = signal_to_noise_ratio;
        _packet_error_rate = packet_error_rate;
        _time = time;
    }

    @Override
    public String toString() {
        return "LinkMetrics{" +
                "_link_id=" + _link_id +
                ", _node_src_id=" + _node_src_id +
                ", _node_dest_id=" + _node_dest_id +
                ", _signal_to_noise_ratio=" + _signal_to_noise_ratio +
                ", _packet_error_rate=" + _packet_error_rate +
                ", _time=" + _time +
                '}';
    }
}

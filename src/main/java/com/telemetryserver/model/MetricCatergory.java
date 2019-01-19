package com.telemetryserver.model;

public class MetricCatergory
{
    private String _metric_name;
    private String _category;

    public String get_metric_name() {
        return _metric_name;
    }

    public void set_metric_name(String metric_name) {
        this._metric_name = metric_name;
    }

    public String get_category() {
        return _category;
    }

    public void set_category(String category) {
        this._category = category;
    }
}

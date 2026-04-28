package com.mycompany.smartcampusapi.model;

public class Reading {
    private double value;
    private long timestamp;

    public Reading() {}

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
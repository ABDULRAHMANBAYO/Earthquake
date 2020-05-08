package com.example.earthquake.model;

public class EarthQuake {
    private String place;
    private double magnitude;
    private String detailLink;
    private long time;
    private String type;
    private double lat;
    private double log;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getDetailLink() {
        return detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }

    public EarthQuake(String place, double magnitude, String detailLink, long time, String type, double lat, double log) {
        this.place = place;
        this.magnitude = magnitude;
        this.detailLink = detailLink;
        this.time = time;
        this.type = type;
        this.lat = lat;
        this.log = log;
    }

    public EarthQuake() {
    }
}

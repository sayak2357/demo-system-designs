package com.uber.model;

public class Location {
    private double latitude, longitued;

    public Location(double latitude, double longitued) {
        this.latitude = latitude;
        this.longitued = longitued;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitued() {
        return longitued;
    }

}

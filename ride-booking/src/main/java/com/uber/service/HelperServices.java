package com.uber.service;

import com.uber.model.Location;

/**
 * Helper methods: distance calculation.
 * Replace Euclidean with Haversine for real lat/lon.
 */
public class HelperServices {

    public double distanceTo(Location a, Location b) {
        double dx = a.getLatitude() - b.getLatitude();
        double dy = a.getLongitude() - b.getLongitude();
        return Math.sqrt(dx * dx + dy * dy);
    }
}

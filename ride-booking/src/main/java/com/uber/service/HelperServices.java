package com.uber.service;

import com.uber.model.Location;

public class HelperServices {

    public  double distanceTo(Location from, Location to){

        double distance = Math.sqrt(Math.pow(from.getLatitude()-to.getLatitude(),2) +
                                        Math.pow(from.getLongitued()-to.getLongitued(), 2));
        return distance;
    }
}

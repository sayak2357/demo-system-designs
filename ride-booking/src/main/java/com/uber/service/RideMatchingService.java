package com.uber.service;

import com.uber.model.Driver;
import com.uber.model.Location;

import java.util.List;

public class RideMatchingService {
    private HelperServices helperServices;
    private List<Driver> drivers;

    public RideMatchingService(List<Driver> drivers) {
        this.helperServices = new HelperServices();
        this.drivers = drivers;
    }
    public Driver findNearestAvailableDriver(Location pickup){
        Driver matchedDriver = null;
        double minDistance = Double.MAX_VALUE;
        for(Driver driver:this.drivers){
            double dist = helperServices.distanceTo(pickup,driver.getLocation());
            if(dist<minDistance && driver.isAvailable()){
                matchedDriver = driver;
                minDistance = dist;
                break;
            }
        }
        return matchedDriver;
    }
}

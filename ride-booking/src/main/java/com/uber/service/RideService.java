package com.uber.service;

import com.uber.model.Driver;
import com.uber.model.Ride;
import com.uber.model.RideRequest;
import com.uber.model.RideStatus;

import java.util.HashMap;
import java.util.Map;

public class RideService {
    private RideMatchingService rideMatchingService;
    private Map<String, Ride> rides = new HashMap<>();

    public RideService(RideMatchingService rideMatchingService) {
        this.rideMatchingService = rideMatchingService;
    }

    public Ride requestRide(RideRequest request){
        Driver driver = rideMatchingService.findNearestAvailableDriver(request.getPickup());
        if(driver==null)
            throw new RuntimeException("No driver available");
        driver.setAvailable(false);
        Ride ride = new Ride(request.getRider(),driver,request.getPickup(),request.getDestination());
        rides.put(ride.getId(), ride);
        return ride;
    }
    public void updateRideStatus(String rideId, RideStatus rideStatus){
        Ride ride = rides.get(rideId);
        ride.setRideStatus(rideStatus);
        if(rideStatus==RideStatus.COMPLETED || rideStatus==RideStatus.CANCELLED){
            ride.getDriver().setAvailable(true);
        }
    }
}

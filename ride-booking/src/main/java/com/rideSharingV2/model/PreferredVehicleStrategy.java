package com.rideSharingV2.model;

import com.rideSharingV2.Dao.RideManager;

import java.util.Map;
import java.util.UUID;

public class PreferredVehicleStrategy implements SelectionStrategy {

    @Override
    public Ride findRides(String origin, String destination, int seats, RideManager rideManager, String vehicle) {
        Map<UUID, Ride> activeRides = rideManager.getActiveRides();

        Ride potentialRide = null;
        for(Ride r: activeRides.values()){
            if(r.getDestination().equals(destination) && r.getOrigin().equals(origin) && r.getAvailableSeats()>=seats){
                if(r.getVehicleModel().equals(vehicle)){
                    potentialRide = r;
                }
            }
        }
        return potentialRide;
    }
}

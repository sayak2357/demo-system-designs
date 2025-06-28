package com.rideSharingV2.model;

import com.rideSharingV2.Dao.RideManager;
import com.rideSharingV2.Exceptions.NoRideFound;

import java.util.Map;
import java.util.UUID;

public class MostVacantStrategy implements SelectionStrategy {

    @Override
    public Ride findRides(String origin, String destination, int seats, RideManager rideManager, String vehicleNumber) {
        Map<UUID, Ride> activeRides = rideManager.getActiveRides();

        Ride potentialRide = null;
        int maxAvailability = 0;
        for(Ride r: activeRides.values()){
            if(r.getDestination().equals(destination) && r.getOrigin().equals(origin) && r.getAvailableSeats()>=seats){
                if(r.getAvailableSeats()>=maxAvailability){
                    maxAvailability = r.getAvailableSeats();
                    potentialRide = r;
                }
            }
        }
        if(potentialRide==null)
            throw new NoRideFound();

        return potentialRide;
    }
}

package com.rideSharingV2.Dao;

import com.rideSharingV2.Exceptions.AlreadyRideExists;
import com.rideSharingV2.model.Ride;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RideManager {

    private Map<UUID, Ride> activeRides;

    public RideManager() {
        this.activeRides = new HashMap<>();
    }

    public Map<UUID,Ride> getActiveRides(){
        return activeRides;
    }
    public void addOfferRide(Ride ride, String userName){
        for(Ride r: activeRides.values()){
            if(r.getVehicleModel().equals(ride.getVehicleNumber()))
                throw new AlreadyRideExists();
        }
        activeRides.put(ride.getId(),ride);
    }

    public Ride endRide(String vehicleNumber){
        for(Ride r: activeRides.values()){
            if(r.getVehicleNumber().equals(vehicleNumber)){
                return r;
            }
        }
        return null;
    }

}

package com.rideSharingV2.model;

import com.rideSharingV2.Dao.RideManager;

public interface SelectionStrategy {
    public Ride findRides(String origin, String destination, int seats, RideManager rideManager, String vehicleNumber);
}

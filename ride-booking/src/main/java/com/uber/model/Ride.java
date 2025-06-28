package com.uber.model;

import java.util.UUID;

public class Ride {
    private String id;
    private User user;
    private Driver driver;
    private Location pickup;
    private Location destination;
    private RideStatus rideStatus;

    public Ride(User user, Driver driver, Location pickup, Location destination) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.driver = driver;
        this.pickup = pickup;
        this.destination = destination;
        this.rideStatus = RideStatus.REQUESTED;
    }

    public void updateRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Driver getDriver() {
        return driver;
    }

    public Location getPickup() {
        return pickup;
    }

    public Location getDestination() {
        return destination;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }
}

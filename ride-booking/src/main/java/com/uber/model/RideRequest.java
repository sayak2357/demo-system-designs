package com.uber.model;

public class RideRequest {
    private User rider;
    private Location pickup, destination;

    public RideRequest(User rider, Location pickup, Location destination) {
        this.rider = rider;
        this.pickup = pickup;
        this.destination = destination;
    }

    public User getRider() {
        return rider;
    }

    public Location getPickup() {
        return pickup;
    }

    public Location getDestination() {
        return destination;
    }
}

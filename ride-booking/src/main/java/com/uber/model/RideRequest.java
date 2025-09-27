package com.uber.model;

public class RideRequest {
    private final User rider;
    private final Location pickup;
    private final Location destination;

    public RideRequest(User rider, Location pickup, Location destination) {
        this.rider = rider;
        this.pickup = pickup;
        this.destination = destination;
    }

    public User getRider() { return rider; }
    public Location getPickup() { return pickup; }
    public Location getDestination() { return destination; }
}

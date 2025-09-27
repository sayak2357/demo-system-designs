package com.uber.service;

import com.uber.model.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Coordinates ride requests, matching, lifecycle, and notifications.
 */
public class RideService {
    private final RideMatchingService matchingService;
    private final Map<String, Ride> rides = new ConcurrentHashMap<>();
    private final NotificationService notificationService;
    private final double maxAcceptDistance = 1000.0; // arbitrary max for demo

    public RideService(RideMatchingService matchingService, NotificationService notificationService) {
        this.matchingService = matchingService;
        this.notificationService = notificationService;
    }

    /**
     * Request a ride: try to find & claim a driver, create ride entry.
     */
    public Ride requestRide(RideRequest request) {
        Driver driver = matchingService.findAndClaimNearestDriver(request.getPickup(), maxAcceptDistance);

        if (driver == null) {
            notificationService.notifyUser(request.getRider(), "No drivers available nearby.");
            throw new RuntimeException("No driver available");
        }

        Ride ride = new Ride(request.getRider(), driver, request.getPickup(), request.getDestination());
        rides.put(ride.getId(), ride);

        // notify driver & user (simple)
        notificationService.notifyDriver(driver, "You have been assigned ride " + ride.getId());
        notificationService.notifyUser(request.getRider(), "Driver " + driver.getName() + " assigned. RideId=" + ride.getId());

        // return created ride
        return ride;
    }

    /**
     * Update ride status with validation. On COMPLETED/CANCELLED we free the driver.
     */
    public boolean updateRideStatus(String rideId, RideStatus newStatus) {
        Ride ride = rides.get(rideId);
        if (ride == null) return false;

        boolean ok = ride.updateStatus(newStatus);
        if (!ok) return false;

        // if ride completed or cancelled, release driver
        if (newStatus == RideStatus.COMPLETED || newStatus == RideStatus.CANCELLED) {
            Driver driver = ride.getDriver();
            driver.setAvailable(true);
            notificationService.notifyUser(ride.getUser(), "Ride " + rideId + " is " + newStatus);
            notificationService.notifyDriver(driver, "Ride " + rideId + " is " + newStatus);
        } else {
            // notify intermediate status
            notificationService.notifyUser(ride.getUser(), "Ride " + rideId + " is " + newStatus);
        }
        return true;
    }

    public Ride getRide(String id) { return rides.get(id); }
}

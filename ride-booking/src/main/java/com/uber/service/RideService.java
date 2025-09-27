package com.uber.service;

import com.uber.model.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RideService {
    private RideMatchingService rideMatchingService;
    private Map<String, Ride> rides = new ConcurrentHashMap<>();
    private NotificationService notificationService;
    public RideService(RideMatchingService rideMatchingService, NotificationService notificationService) {
        this.rideMatchingService = rideMatchingService;
        this.notificationService = notificationService;
    }

    public Ride requestRide(RideRequest request, double maxDistanceAllowed) {
        Driver driver = rideMatchingService.findAndClaimNearestDriver(request.getPickup(), maxDistanceAllowed);
        if(driver == null)
            throw new RuntimeException("No driver available within allowed distance");

        Ride ride = new Ride(request.getRider(), driver, request.getPickup(), request.getDestination());
        rides.put(ride.getId(), ride);

        // Notify driver and user
        notificationService.registerObserver(driver);
        notificationService.registerObserver(request.getRider());
        notificationService.notifyObservers(
                "Ride created: " + ride.getId() + " for user " + request.getRider().getName()
        );
        notificationService.removeObserver(driver);
        notificationService.removeObserver(request.getRider());

        return ride;
    }

    public void updateRideStatus(String rideId, RideStatus rideStatus) {
        Ride ride = rides.get(rideId);
        ride.updateStatus(rideStatus);

        // Notify driver and user
        notificationService.registerObserver(ride.getDriver());
        notificationService.registerObserver(ride.getUser());
        notificationService.notifyObservers(
                "Ride updated: " + rideId + " status -> " + rideStatus
        );
        notificationService.removeObserver(ride.getDriver());
        notificationService.removeObserver(ride.getUser());

        if (rideStatus == RideStatus.COMPLETED || rideStatus == RideStatus.CANCELLED) {
            ride.getDriver().setAvailable(true);
        }
    }
}

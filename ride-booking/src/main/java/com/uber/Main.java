package com.uber;

import com.uber.model.*;
import com.uber.service.NotificationService;
import com.uber.service.RideMatchingService;
import com.uber.service.RideService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Uber ride booking service!");

        // Create drivers
        Driver d1 = new Driver("d1", "Anil", new Location(1.0, 5.0));
        Driver d2 = new Driver("d2", "Mohit", new Location(5.0, 5.0));
        // Initialize notification service
        NotificationService notificationService = new NotificationService();
        // Initialize ride matching service
        RideMatchingService matcher = new RideMatchingService(List.of(d1, d2));

        // Initialize ride service
        RideService rideService = new RideService(matcher,notificationService);

        // Create a user and ride request
        User user = new User("u1", "Sayak", new Location(1.2, 6.6));
        RideRequest request = new RideRequest(user, user.getLocation(), new Location(10, 10));

        // Request ride with a max allowed distance of 10 units
        Ride ride = rideService.requestRide(request, 10.0);
        System.out.println("Ride assigned to driver: " + ride.getDriver().getName());

        // Update ride status
        rideService.updateRideStatus(ride.getId(), RideStatus.IN_PROGRESS);
        rideService.updateRideStatus(ride.getId(), RideStatus.COMPLETED);
    }
}

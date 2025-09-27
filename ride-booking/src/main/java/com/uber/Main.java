package com.uber;

import com.uber.model.*;
import com.uber.service.*;

import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Welcome to Quick ride demo!");

        Driver d1 = new Driver("d1", "Anil", new Location(1.0, 5.0));
        Driver d2 = new Driver("d2", "Mohit", new Location(5.0, 5.0));
        Driver d3 = new Driver("d3", "Ravi", new Location(1.5, 5.2));

        RideMatchingService matchingService = new RideMatchingService(List.of(d1, d2, d3));
        NotificationService notifications = new NotificationService();

        // simple listener that prints notifications
        Consumer<String> printer = System.out::println;
        notifications.registerListener(printer);

        RideService rideService = new RideService(matchingService, notifications);

        User user = new User("u1","Sayak", new Location(1.2, 5.1));
        RideRequest req = new RideRequest(user, user.getLocation(), new Location(10,10));

        try {
            Ride ride = rideService.requestRide(req);
            System.out.println("Ride created: " + ride);

            // Accepting/starting/completing
            boolean s1 = rideService.updateRideStatus(ride.getId(), RideStatus.ACCEPTED);
            System.out.println("Accepted ok: " + s1 + ", status: " + ride.getStatus());

            boolean s2 = rideService.updateRideStatus(ride.getId(), RideStatus.IN_PROGRESS);
            System.out.println("In progress ok: " + s2 + ", status: " + ride.getStatus());

            boolean s3 = rideService.updateRideStatus(ride.getId(), RideStatus.COMPLETED);
            System.out.println("Completed ok: " + s3 + ", status: " + ride.getStatus());

        } catch (RuntimeException e) {
            System.out.println("Could not create ride: " + e.getMessage());
        } finally {
            notifications.removeListener(printer);
        }
    }
}

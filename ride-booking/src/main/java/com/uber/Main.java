package com.uber;

import com.uber.model.*;
import com.uber.service.RideMatchingService;
import com.uber.service.RideService;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to Uber ride booking service!");

        Driver d1 = new Driver("d1","Anil",new Location(1.0,5.0));
        Driver d2 = new Driver("d2","Mohit",new Location(5.0,5.0));

        RideMatchingService matcher = new RideMatchingService(List.of(d1,d2));

        RideService rideService = new RideService(matcher);
        User user = new User("u1","Sayak",new Location(1.2,6.6));
        RideRequest request = new RideRequest(user,user.getLocation(),new Location(10,10));

        Ride ride = rideService.requestRide(request);
        System.out.println("Ride assigned to driver: "+ride.getDriver().getName());

        rideService.updateRideStatus(ride.getId(), RideStatus.IN_PROGRESS);
        rideService.updateRideStatus(ride.getId(), RideStatus.COMPLETED);
    }
}
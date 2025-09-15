package com.parkingLot;

import com.parkingLot.feeStrategy.Strategy;
import com.parkingLot.model.*;
import com.parkingLot.service.ParkingService;

import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        List<ParkingSlot> floor1Slots = Arrays.asList(new ParkingSlot(1, VehicleType.CAR),
                                                        new ParkingSlot(2,VehicleType.CAR),
                                                        new ParkingSlot(3,VehicleType.BIKE));
        ParkingFloor parkingFloor1 = new ParkingFloor(1,floor1Slots);
        ParkingLot parkingLot = new ParkingLot(List.of(parkingFloor1));

        ParkingService parkingService = new ParkingService(parkingLot, Strategy.FLAT,100d);

        Vehicle car = new Car("FG123");
        Ticket ticket = parkingService.parkVehicle(car);
        System.out.println("Ticket ID:"+ticket.getTicketId());
        parkingService.unparkVehicle(ticket.getTicketId());
    }
}
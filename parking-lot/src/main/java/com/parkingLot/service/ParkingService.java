package com.parkingLot.service;

import com.parkingLot.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ParkingService {
    private ParkingLot parkingLot;
    private Map<String, Ticket> activeTickets;
    public ParkingService(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
        this.activeTickets = new HashMap<>();
    }
    public Ticket parkVehicle(Vehicle vehicle){
        Optional<ParkingSlot> parkingSlot = parkingLot.getNearestParkingSlot(vehicle.getVehicleType());
        if(parkingSlot.isEmpty())
            throw new RuntimeException("No slot available");
        ParkingSlot slot = parkingSlot.get();
        slot.setVehicle(vehicle);
        slot.setOccupied(true);
        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(ticketId,slot,vehicle);
        activeTickets.put(ticketId,ticket);
        return ticket;
    }
    public void unparkVehicle(String ticketId){
        Ticket ticket = activeTickets.remove(ticketId);
        ParkingSlot slot = ticket.getParkingSlot();
        slot.setVehicle(null);
        slot.setOccupied(false);
        System.out.println("vehicle unparked "+ticket.getVehicle().getVehicleNumber());
    }
}

package com.parkingLot.model;

import java.time.LocalDateTime;

public class Ticket {
    String ticketId;
    Vehicle vehicle;
    ParkingSlot parkingSlot;
    LocalDateTime entryTime;
    LocalDateTime exitTime;

    public Ticket(String ticketId, ParkingSlot parkingSlot, Vehicle vehicle) {
        this.ticketId = ticketId;
        this.parkingSlot = parkingSlot;
        this.vehicle = vehicle;
        this.entryTime = LocalDateTime.now();
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }
}

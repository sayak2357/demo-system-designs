package com.parkingLot.service;

import com.parkingLot.feeStrategy.FeesStrategy;
import com.parkingLot.feeStrategy.FixedFees;
import com.parkingLot.feeStrategy.HourlyFees;
import com.parkingLot.feeStrategy.Strategy;
import com.parkingLot.model.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ParkingService {
    private ParkingLot parkingLot;
    private Map<String, Ticket> activeTickets;
    private FeesStrategy feesStrategy;
    public ParkingService(ParkingLot parkingLot, Strategy strategy,double rate){
        this.parkingLot = parkingLot;
        this.activeTickets = new HashMap<>();
        switch (strategy){
            case FLAT -> this.feesStrategy = new FixedFees(rate);
            case HOURLY -> this.feesStrategy = new HourlyFees(rate);
        }
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
        ticket.setExitTime(LocalDateTime.now());
        ParkingSlot slot = ticket.getParkingSlot();
        slot.setVehicle(null);
        slot.setOccupied(false);
        System.out.println("vehicle unparked "+ticket.getVehicle().getVehicleNumber());
        calculateFee(ticket);
    }
    private void calculateFee(Ticket ticket){
        LocalDateTime start = ticket.getEntryTime();
        LocalDateTime end = ticket.getExitTime();
        long hours = Duration.between(start, end).toHours();
        double amount = this.feesStrategy.calculateFee(hours);
        System.out.println("Please pay an amount of: "+amount);
    }
}

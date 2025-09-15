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
    private MonitoringService monitoringService;
    public ParkingService(ParkingLot parkingLot, Strategy strategy,double rate, MonitoringService monitoringService){
        this.parkingLot = parkingLot;
        this.activeTickets = new HashMap<>();
        switch (strategy){
            case FLAT -> this.feesStrategy = new FixedFees(rate);
            case HOURLY -> this.feesStrategy = new HourlyFees(rate);
        }
        this.monitoringService = monitoringService;
    }
    public synchronized Ticket parkVehicle(Vehicle vehicle){
        Optional<ParkingSlot> parkingSlot = parkingLot.getNearestParkingSlot(vehicle.getVehicleType());
        if(parkingSlot.isEmpty())
            throw new RuntimeException("No slot available");
        ParkingSlot slot = parkingSlot.get();
        slot.setVehicle(vehicle);
        slot.setOccupied();
        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(ticketId,slot,vehicle);
        activeTickets.put(ticketId,ticket);
        monitoringService.notifyChange(activeTickets.size(),parkingLot.getCapacity());
        return ticket;
    }
    public synchronized void unparkVehicle(String ticketId){
        Ticket ticket = activeTickets.remove(ticketId);
        ticket.setExitTime(LocalDateTime.now());
        ParkingSlot slot = ticket.getParkingSlot();
        slot.setVehicle(null);
        slot.setAvailable();
        System.out.println("vehicle unparked "+ticket.getVehicle().getVehicleNumber());
        monitoringService.notifyChange(activeTickets.size(),parkingLot.getCapacity());
        calculateFee(ticket);
    }
    private void calculateFee(Ticket ticket){
        double amount = this.feesStrategy.calculateFee(ticket);
        System.out.println("Please pay an amount of: "+amount);
    }
    public synchronized String reserveSlot(int id){
        try {
            ParkingSlot ps = parkingLot.getParkingSlotById(id).get();
            if (ps != null && ps.isAvailable()) {
                ps.setReserved();
                System.out.println("Slot with id: " + id + " is reserved");
                return "Slot with id: " + id + " is reserved";
            }
        }
        catch (Exception e) {
            System.out.println("Slot with id: " + id + " is not available for reservation");
        }
        System.out.println("Slot with id: "+id+" is not available for reservation");
        return "Slot with id: "+id+" is not available for reservation";
    }

    public synchronized String unreserveSlot(int id){
        try {
            ParkingSlot ps = parkingLot.getParkingSlotById(id).get();
            if (ps != null && ps.isReserved()) {
                ps.setAvailable();
                System.out.println("Slot with id: " + id + " is unreserved and available now");
                return "Slot with id: " + id + " is unreserved";
            }
        }
        catch (Exception e) {
            System.out.println("Slot with id: " + id + " is not available for availability");
        }
        return "Slot with id: "+id+" is not available for unreservation";
    }

    public synchronized int getOccupiedSpots() {
        return activeTickets.size();
    }

    public int getCapacity() {
        return this.parkingLot.getCapacity();
    }
}

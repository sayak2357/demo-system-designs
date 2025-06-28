package com.rideSharingV2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ride {
    private UUID id;
    private String sharedBy;
    private List<String> selectedBy;
    private String origin;
    private String destination;
    private int availableSeats;
    private RideStatus status;
    private String vehicleNumber;
    private String vehicleModel;

    public Ride(String sharedBy,  String origin, String destination, int availableSeats, String vehicleNumber, String vehicleModel) {
        this.id = UUID.randomUUID();
        this.sharedBy = sharedBy;
        this.selectedBy = new ArrayList<>();
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.vehicleNumber = vehicleNumber;
        this.vehicleModel = vehicleModel;
    }

    public UUID getId() {
        return id;
    }

    public String getSharedBy() {
        return sharedBy;
    }

    public List<String> getSelectedBy() {
        return selectedBy;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public RideStatus getStatus() {
        return status;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void endRide(){
        this.status=RideStatus.END;
    }

    public void addPassenger(String passenger, int seats){
        this.selectedBy.add(passenger);
        this.availableSeats -= seats;
    }

    @Override
    public String toString(){
        return "Ride details: ride created by: "+sharedBy;
    }
}

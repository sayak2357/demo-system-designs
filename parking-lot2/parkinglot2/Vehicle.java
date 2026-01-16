package com.gfg.parkinglot;

public class Vehicle {
    private String licensePlateNumber;
    private VehicleType vehicleType;

    public Vehicle(String licensePlateNumber, VehicleType vehicleType) {
        this.licensePlateNumber = licensePlateNumber;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}

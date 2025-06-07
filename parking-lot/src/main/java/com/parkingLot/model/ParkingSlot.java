package com.parkingLot.model;

public class ParkingSlot {
    int id;
    boolean isOccupied;
    VehicleType allowedType;
    Vehicle vehicle;
    public ParkingSlot(int id, VehicleType allowedType){
        this.id = id;
        this.allowedType = allowedType;
        this.isOccupied = false;
    }
    public boolean isAvailable(){
        return !this.isOccupied;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public VehicleType getAllowedType() {
        return allowedType;
    }

    public void setAllowedType(VehicleType allowedType) {
        this.allowedType = allowedType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getId() {
        return id;
    }
}

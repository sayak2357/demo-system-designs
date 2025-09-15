package com.parkingLot.model;

public class ParkingSlot {
    int id;
    SlotStatus slotStatus;
    VehicleType allowedType;
    Vehicle vehicle;
    public ParkingSlot(int id, VehicleType allowedType){
        this.id = id;
        this.allowedType = allowedType;
        this.slotStatus = SlotStatus.AVAILABLE;
    }
    public boolean isAvailable(){
        return this.slotStatus.equals(SlotStatus.AVAILABLE);
    }

    public boolean isOccupied() {
        return this.slotStatus.equals(SlotStatus.OCCUPIED);
    }

    public boolean isReserved() {
        return this.slotStatus.equals(SlotStatus.RESERVED);
    }

    public void setOccupied() {
        this.slotStatus = SlotStatus.OCCUPIED;
    }

    public void setAvailable(){ this.slotStatus = SlotStatus.AVAILABLE; }

    public void setReserved(){ this.slotStatus = SlotStatus.RESERVED; }

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

package com.parkingLot.model;

import java.util.List;
import java.util.Optional;

public class ParkingFloor {
    int floorNumber;
    List<ParkingSlot> slots;

    public ParkingFloor(int floorNumber, List<ParkingSlot> slots) {
        this.floorNumber = floorNumber;
        this.slots = slots;
    }
    public Optional<ParkingSlot> getAvailableSlot(VehicleType vehicleType){
        return slots.stream().filter(s->s.allowedType.equals(vehicleType) && s.isAvailable()).findFirst();
    }
}

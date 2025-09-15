package com.parkingLot.model;

import java.util.List;
import java.util.Optional;

public class ParkingLot {
    List<ParkingFloor> floors;

    public ParkingLot(List<ParkingFloor> floors) {
        this.floors = floors;
    }
    public Optional<ParkingSlot> getNearestParkingSlot(VehicleType vehicleType){
        for(ParkingFloor parkingFloor:floors){
            Optional<ParkingSlot> slot = parkingFloor.getAvailableSlot(vehicleType);
            if(!slot.isEmpty())
                return slot;
        }
        return Optional.empty();
    }
}

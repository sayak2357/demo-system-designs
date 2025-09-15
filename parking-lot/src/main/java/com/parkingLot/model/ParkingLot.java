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
    public Optional<ParkingSlot> getParkingSlotById(int id){
        Optional<ParkingSlot> target = null;
        for(ParkingFloor floor:floors){
            target = floor.getSlotById(id);
            if(!target.isEmpty())
                return target;
        }
        return null;
    }
}

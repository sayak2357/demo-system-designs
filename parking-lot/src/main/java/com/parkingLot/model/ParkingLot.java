package com.parkingLot.model;

import java.util.List;
import java.util.Optional;

public class ParkingLot {
    List<ParkingFloor> floors;
    private int capacity;
    public ParkingLot(List<ParkingFloor> floors) {
        this.floors = floors;
        this.capacity = floors.stream().mapToInt(ParkingFloor::getCapacity).sum();
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

    public int getCapacity() {
        return capacity;
    }
}

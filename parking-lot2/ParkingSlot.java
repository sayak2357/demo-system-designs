package com.gfg.parkinglot;

import com.gfg.parkinglot.exceptions.ParkingErrorCode;
import com.gfg.parkinglot.exceptions.ParkingException;

import java.util.UUID;

public class ParkingSlot {

    private final String parkingLotId;
    private Vehicle vehicle;
    private final SlotType slotType;

    public ParkingSlot(SlotType slotType) {
        this.slotType = slotType;
        this.parkingLotId = UUID.randomUUID().toString();
    }

    public void assignVehicle(Vehicle vehicle){
        if(this.vehicle == null){
            this.vehicle = vehicle;
        }
        else{
            throw new ParkingException(ParkingErrorCode.SLOT_FULL);
        }
    }
    public void removeVehicle(){
        this.vehicle = null;
    }



    public SlotType getSlotType() {
        return slotType;
    }
}

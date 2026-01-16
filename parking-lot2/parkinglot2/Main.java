package com.gfg.parkinglot;

import com.gfg.parkinglot.exceptions.ParkingException;

public class Main {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader();
        ParkingLot parkingLot = new ParkingLot(inputReader);
        for(int i=0;i<4;i++){
            parkingLot.addEmptySlot(new ParkingSlot(SlotType.SMALL));
        }

        for(int i=0;i<2;i++){
            parkingLot.addEmptySlot(new ParkingSlot(SlotType.STANDARD));
        }

//        for(int i=0;i<1;i++){
//            parkingLot.addEmptySlot(new ParkingSlot(SlotType.LARGE));
//        }

        parkingLot.printStatus();

        OperationType operationType = OperationType.START;
        while(operationType != OperationType.EXIT){
            try{
                operationType = inputReader.getOperation();
                if(operationType == OperationType.VEHICLE_ENTRY){
                    parkingLot.vehicleEntry();
                    parkingLot.printStatus();
                }
                else if(operationType == OperationType.VEHICLE_EXIT){
                    parkingLot.vehicleExit();
                    parkingLot.printStatus();
                }
            }
            catch (ParkingException e){
                System.out.println("error code: "+e.getParkingErrorCode()+"\nerror mesg: "+e.getMessage());
            }
        }

    }
}

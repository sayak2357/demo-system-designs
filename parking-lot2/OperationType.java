package com.gfg.parkinglot;

public enum OperationType {
    START(0),
    VEHICLE_ENTRY(1),
    VEHICLE_EXIT(2),
    EXIT(3);
    private final int type;

    OperationType(int type){
        this.type = type;
    }
    public static OperationType fromValue(int type){
        if(type==1){
            return OperationType.VEHICLE_ENTRY;
        }
        else if(type==2){
            return OperationType.VEHICLE_EXIT;
        }
        else
            return OperationType.EXIT;
    }
}

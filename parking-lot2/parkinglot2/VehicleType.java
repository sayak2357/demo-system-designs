package com.gfg.parkinglot;

public enum VehicleType {

    BIKE(2,3),
    HATCHBACK(3,4),
    SUV(3,5),
    PICKUP_TRUCK(4,12);

    private int length;
    private int width;


    VehicleType(int length,int width) {
        this.length = length;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
}

package com.gfg.parkinglot;

public enum SlotType {
    SMALL(2,4),
    STANDARD(3,5),
    LARGE(5,7);

    private int length;
    private int width;

    SlotType(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}

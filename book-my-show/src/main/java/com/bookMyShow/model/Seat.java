package com.bookMyShow.model;

public class Seat {
    private String seatId,row;
    private int number;

    public Seat(String seatId, String row, int number) {
        this.seatId = seatId;
        this.row = row;
        this.number = number;
    }

    public String getSeatId() {
        return seatId;
    }

    public String getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString(){
        return "seatId: "+seatId+"\n row:"+row;
    }
}

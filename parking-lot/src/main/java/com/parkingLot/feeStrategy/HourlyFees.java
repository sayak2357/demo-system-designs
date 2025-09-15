package com.parkingLot.feeStrategy;

public class HourlyFees implements FeesStrategy{
    private double rate;
    public HourlyFees(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculateFee(double hour) {
        return hour*this.rate;
    }
}

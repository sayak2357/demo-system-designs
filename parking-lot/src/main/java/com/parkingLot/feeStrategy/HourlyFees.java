package com.parkingLot.feeStrategy;

public class HourlyFees implements FeesStrategy{
    private double rate;
    public HourlyFees(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculateFee(double hour) {
        hour = Math.max(hour,1d);
        return hour*this.rate;
    }
}

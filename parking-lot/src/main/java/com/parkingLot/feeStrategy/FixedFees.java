package com.parkingLot.feeStrategy;

public class FixedFees implements FeesStrategy{
    private double rate;

    public FixedFees(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculateFee(double hour) {
        return rate;
    }
}

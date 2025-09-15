package com.parkingLot.feeStrategy;

import com.parkingLot.model.Ticket;

public class FixedFees implements FeesStrategy{
    private double rate;

    public FixedFees(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculateFee(Ticket ticket) {
        return rate;
    }
}

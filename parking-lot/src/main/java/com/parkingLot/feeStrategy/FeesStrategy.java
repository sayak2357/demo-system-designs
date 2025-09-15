package com.parkingLot.feeStrategy;

import com.parkingLot.model.Ticket;

public interface FeesStrategy {
    public double calculateFee(Ticket ticket);
}

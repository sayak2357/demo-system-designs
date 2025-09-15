package com.parkingLot.feeStrategy;

import com.parkingLot.model.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;

public class HourlyFees implements FeesStrategy{
    private double rate;
    public HourlyFees(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculateFee(Ticket ticket) {
        LocalDateTime start = ticket.getEntryTime();
        LocalDateTime end = ticket.getExitTime();
        long hours = Math.max(1,Duration.between(start, end).toHours());
        return hours*this.rate;
    }
}

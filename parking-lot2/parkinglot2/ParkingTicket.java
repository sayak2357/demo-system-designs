package com.gfg.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParkingTicket {
    private String ticketId;
    private Vehicle vehicle;
    private ParkingSlot parkingSlot;
    private TicketStatus ticketStatus;

    private double amount;
    private long entryTime;
    private long exitTime;

    private List<PaymentAttempt> paymentAttemps;

    private ParkingTicket(ParkingTicketBuilder parkingTicketBuilder){
        this.vehicle = parkingTicketBuilder.vehicle;
        this.parkingSlot = parkingTicketBuilder.parkingSlot;
        this.ticketId = parkingTicketBuilder.ticketId;
        this.ticketStatus = parkingTicketBuilder.ticketStatus;
        this.entryTime = parkingTicketBuilder.entryTime;
        this.exitTime = parkingTicketBuilder.exitTime;
        this.paymentAttemps = new ArrayList<>();
        this.amount = 0;
    }
    public static ParkingTicketBuilder builder(){ return new ParkingTicketBuilder();}

    public TicketStatus attemptPayment(Payment payment){
        if(this.ticketStatus == TicketStatus.IN_PROGRESS){
            this.exitTime = System.currentTimeMillis();
            this.amount = Calculator.calculateParkingCharges(this);
            this.ticketStatus = TicketStatus.UNPAID;
        }
        if(this.ticketStatus == TicketStatus.UNPAID){
            PaymentAttempt paymentAttempt = payment.doPayment(this.amount);
            if(paymentAttempt.getPaymentStatus()==PaymentStatus.SUCCESSFUL){
                this.ticketStatus = TicketStatus.PAID;
            }
            else if(paymentAttempt.getPaymentStatus() == PaymentStatus.FAILED){
                this.ticketStatus = TicketStatus.UNPAID;
            }
        }
        return this.ticketStatus;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public String getTicketId() {
        return ticketId;
    }

    public static class ParkingTicketBuilder{
        private Vehicle vehicle;
        private ParkingSlot parkingSlot;
        private String ticketId;
        private TicketStatus ticketStatus;
        private long entryTime;
        private long exitTime;

        public ParkingTicketBuilder withVehicle(Vehicle vehicle){
            this.vehicle = vehicle;
            return this;
        }

        public ParkingTicketBuilder withParkingSlot(ParkingSlot parkingSlot){
            this.parkingSlot = parkingSlot;
            return this;
        }
        public ParkingTicketBuilder withEntryTime(long entryTime){
            this.entryTime = entryTime;
            return this;
        }

        public ParkingTicketBuilder withExitTime(long exitTime){
            this.exitTime = exitTime;
            return this;
        }

        public ParkingTicket build(){
            this.ticketId = UUID.randomUUID().toString();
            this.ticketStatus = TicketStatus.IN_PROGRESS;
            return new ParkingTicket(this);
        }

    }
}

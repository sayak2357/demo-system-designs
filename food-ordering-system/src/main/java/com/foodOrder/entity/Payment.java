package com.foodOrder.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {
    private String id;
    private String orderId;
    private double amount;
    private PaymentStatus status;
    private LocalDateTime time;

    public Payment(String orderId, double amount) {
        this.id = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
        this.time = LocalDateTime.now();
    }

    public void updateStatus(PaymentStatus status){
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getTime() {
        return time;
    }
}

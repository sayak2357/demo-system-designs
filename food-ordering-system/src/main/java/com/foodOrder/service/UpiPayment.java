package com.foodOrder.service;

import com.foodOrder.entity.Payment;
import com.foodOrder.entity.PaymentStatus;

public class UpiPayment implements PaymentService{
    @Override
    public Payment processPayment(String orderId, double amount) {
        Payment payment = new Payment(orderId, amount);
        // simulate UPI gateway call
        payment.updateStatus(PaymentStatus.SUCCESS);
        return payment;
    }
}

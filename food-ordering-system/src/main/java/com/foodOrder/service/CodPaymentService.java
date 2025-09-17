package com.foodOrder.service;

import com.foodOrder.entity.Payment;
import com.foodOrder.entity.PaymentStatus;

public class CodPaymentService implements PaymentService{
    @Override
    public Payment processPayment(String orderId, double amount) {
        Payment payment = new Payment(orderId, amount);
        // COD is not paid upfront
        payment.updateStatus(PaymentStatus.PENDING);
        return payment;
    }
}

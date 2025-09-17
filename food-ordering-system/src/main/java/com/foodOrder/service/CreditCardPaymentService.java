package com.foodOrder.service;

import com.foodOrder.entity.Payment;
import com.foodOrder.entity.PaymentStatus;

public class CreditCardPaymentService implements PaymentService{

    @Override
    public Payment processPayment(String orderId, double amount) {
        Payment payment = new Payment(orderId,amount);
        // simulate API call to credit card gateway
        payment.updateStatus(PaymentStatus.SUCCESS);
        return payment;
    }
}

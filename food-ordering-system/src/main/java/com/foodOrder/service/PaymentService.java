package com.foodOrder.service;

import com.foodOrder.entity.Payment;

public interface PaymentService {
    Payment processPayment(String orderId, double amount);
}

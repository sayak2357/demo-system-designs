package com.gfg.parkinglot;

import com.gfg.parkinglot.PaymentStatus;

public class PaymentAttempt {
    private Payment payment;
    private PaymentStatus paymentStatus;
    private long attemptTime;

    public PaymentAttempt(Payment payment, PaymentStatus paymentStatus) {
        this.payment = payment;
        this.paymentStatus = paymentStatus;
        this.attemptTime = System.currentTimeMillis();
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}

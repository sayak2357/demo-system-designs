package com.gfg.parkinglot;

public abstract class Payment {

    protected PaymentDetails paymentDetails;

    protected PaymentAttempt createPaymentAttempt(Payment payment, PaymentStatus paymentStatus){
        return new PaymentAttempt(payment, paymentStatus );
    }

    public abstract PaymentAttempt doPayment(double amount);
}

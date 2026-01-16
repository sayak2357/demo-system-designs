package com.gfg.parkinglot;

public class CreditCardPayment extends Payment{
    public CreditCardPayment(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    @Override
    public PaymentAttempt doPayment(double amount) {
        // payment logic here
        System.out.println("Completed payment of "+amount);
        return createPaymentAttempt(this,PaymentStatus.SUCCESSFUL);
    }
}

package com.bookMyShowV2.model;

import com.bookMyShowV2.constants.PaymentMethod;

public class PaymentInfo {
    private final String paymentId;
    private final Double amount;
    private final PaymentMethod paymentMethod;

    public PaymentInfo(String paymentId, Double amount, PaymentMethod paymentMethod) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}

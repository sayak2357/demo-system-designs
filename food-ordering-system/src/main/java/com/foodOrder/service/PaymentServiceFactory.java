package com.foodOrder.service;
import com.foodOrder.entity.Payment;
import com.foodOrder.entity.PaymentMode;
import com.foodOrder.entity.PaymentStatus;
public class PaymentServiceFactory {
    public static PaymentService getPaymentService(PaymentMode mode) {
        switch (mode) {
            case CREDIT_CARD:
                return new CreditCardPaymentService();
            case UPI:
                return new UpiPaymentService();
            case CASH_ON_DELIVERY:
                return new CodPaymentService();
            default:
                throw new IllegalArgumentException("Unsupported payment mode: " + mode);
        }
    }
}

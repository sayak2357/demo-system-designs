package com.gfg.parkinglot;

public class CardDetails extends PaymentDetails{

    private String cardNo;
    private String cvv;

    public CardDetails(String cardNo, String cvv) {
        this.cardNo = cardNo;
        this.cvv = cvv;
    }
}

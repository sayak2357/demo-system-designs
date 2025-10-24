package com.zerodha.model;

public class Order {
    private TXN_TYPE txnType;
    private ORDER_TYPE orderType;
    private double price;
    private int quantity;
    private Stock stock;
    private EXCH exch;
    // transaction
    // ORDER_TYPE status
    // timestamp


    public Order(TXN_TYPE txnType, ORDER_TYPE orderType, double price, int quantity, Stock stock,EXCH exch) {
        this.txnType = txnType;
        this.orderType = orderType;
        this.price = price;
        this.quantity = quantity;
        this.stock = stock;
        this.exch = exch;
    }

    public TXN_TYPE getTxnType() {
        return txnType;
    }

    public ORDER_TYPE getOrderType() {
        return orderType;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public EXCH getExch() {
        return exch;
    }

    @Override
    public String toString() {
        return "Order{" +
                "txnType=" + txnType +
                ", orderType=" + orderType +
                ", price=" + price +
                ", quantity=" + quantity +
                ", stock=" + stock +
                ", exch=" + exch +
                '}';
    }
}

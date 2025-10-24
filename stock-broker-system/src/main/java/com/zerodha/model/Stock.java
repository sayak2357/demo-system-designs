package com.zerodha.model;

public class Stock {
    private String name;
    private double price;
    private EXCH exch;

    public Stock(String name, double price, EXCH exch) {
        this.name = name;
        this.price = price;
        this.exch = exch;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public EXCH getExch() {
        return exch;
    }
}

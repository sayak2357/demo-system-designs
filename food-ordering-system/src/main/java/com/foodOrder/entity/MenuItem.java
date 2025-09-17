package com.foodOrder.entity;

public class MenuItem {
    private String id;
    private String name;
    private double price;
    private double averageRating;
    private int ratingCount;

    public MenuItem(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.averageRating = 0d;
        this.ratingCount = 0;
    }
    public synchronized void addRating(int rating){
        double total = this.averageRating*this.ratingCount;
        this.ratingCount++;
        this.averageRating = (total+rating)/this.ratingCount;
    }
    public String getId() {
        return id;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getRatingCount() {
        return this.ratingCount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}

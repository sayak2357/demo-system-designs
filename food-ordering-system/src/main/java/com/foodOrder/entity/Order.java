package com.foodOrder.entity;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public class Order {
    private String id;
    private User user;
    private List<MenuItem> items;
    LocalDateTime time;
    OrderStatus status;

    public Order(User user, List<MenuItem> items) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.items = items;
        this.time = LocalDateTime.now();
        this.status = OrderStatus.NEW;
    }

    public void updateStatus(OrderStatus status){
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public OrderStatus getStatus() {
        return status;
    }
}

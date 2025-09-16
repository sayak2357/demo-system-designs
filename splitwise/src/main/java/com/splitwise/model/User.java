package com.splitwise.model;

import com.splitwise.notification.Observer;

public class User implements Observer {
    static int userId = 1;
    private int id;
    private String name;

    public User(String name) {
        this.name = name;
        this.id = userId++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(String message) {
        System.out.println("message for user: "+name+" "+message);
    }
}

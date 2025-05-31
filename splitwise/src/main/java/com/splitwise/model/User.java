package com.splitwise.model;

public class User {
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
}

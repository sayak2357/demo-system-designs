package com.uber.model;

import com.uber.observer.Observer;

public class User implements Observer {
    private String id, name;
    private Location location;

    public User(String id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public void update(String message) {
        System.out.println("Notification for User " + name + ": " + message);
    }


}

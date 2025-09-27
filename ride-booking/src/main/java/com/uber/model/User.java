package com.uber.model;

public class User {
    private final String id;
    private final String name;
    private volatile Location location;

    public User(String id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Location getLocation() { return location; }

    public void updateLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "User{" + id + "," + name + '}';
    }
}

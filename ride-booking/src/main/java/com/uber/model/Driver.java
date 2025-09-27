package com.uber.model;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Thread-safe driver model:
 * - availability is AtomicBoolean
 * - location is volatile and updated via updateLocation()
 */
public class Driver {
    private final String id;
    private final String name;
    private volatile Location location;
    private final AtomicBoolean isAvailable;

    public Driver(String id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.isAvailable = new AtomicBoolean(true);
    }

    public String getId() { return id; }
    public String getName() { return name; }

    public Location getLocation() { return location; }

    public void updateLocation(Location newLocation) {
        this.location = newLocation;
    }

    public boolean isAvailable() {
        return isAvailable.get();
    }

    /**
     * Atomically set availability. Useful to claim a driver.
     */
    public boolean compareAndSetAvailable(boolean expect, boolean update) {
        return isAvailable.compareAndSet(expect, update);
    }

    public void setAvailable(boolean available) {
        isAvailable.set(available);
    }

    @Override
    public String toString() {
        return "Driver{" + id + "," + name + ", available=" + isAvailable.get() + '}';
    }
}

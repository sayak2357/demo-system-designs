package com.uber.model;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Ride holds immutable ids and atomic status for thread-safety.
 */
public class Ride {
    private final String id;
    private final User user;
    private final Driver driver;
    private final Location pickup;
    private final Location destination;
    private final AtomicReference<RideStatus> status;

    public Ride(User user, Driver driver, Location pickup, Location destination) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.driver = driver;
        this.pickup = pickup;
        this.destination = destination;
        this.status = new AtomicReference<>(RideStatus.REQUESTED);
    }

    public String getId() { return id; }
    public User getUser() { return user; }
    public Driver getDriver() { return driver; }
    public Location getPickup() { return pickup; }
    public Location getDestination() { return destination; }

    public RideStatus getStatus() { return status.get(); }

    /**
     * Validates and updates status atomically.
     * Returns true if update succeeded.
     */
    public boolean updateStatus(RideStatus newStatus) {
        RideStatus cur = status.get();

        // basic allowed transitions
        boolean allowed = switch (cur) {
            case REQUESTED -> newStatus == RideStatus.ACCEPTED || newStatus == RideStatus.CANCELLED;
            case ACCEPTED -> newStatus == RideStatus.IN_PROGRESS || newStatus == RideStatus.CANCELLED;
            case IN_PROGRESS -> newStatus == RideStatus.COMPLETED || newStatus == RideStatus.CANCELLED;
            default -> false;
        };

        if (!allowed) return false;
        return status.compareAndSet(cur, newStatus);
    }

    @Override
    public String toString() {
        return "Ride{" + id + ", user=" + user + ", driver=" + driver + ", status=" + status.get() + '}';
    }
}

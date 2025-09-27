package com.uber.service;

import com.uber.model.Driver;
import com.uber.model.Location;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Simple matching service using a thread-safe driver pool.
 * For large scale, replace with spatial index (quadtree / k-d tree) or geo-hash buckets.
 */
public class RideMatchingService {
    private final HelperServices helper;
    private final CopyOnWriteArrayList<Driver> drivers; // thread-safe snapshot semantics

    public RideMatchingService(List<Driver> initialDrivers) {
        this.helper = new HelperServices();
        this.drivers = new CopyOnWriteArrayList<>(initialDrivers);
    }

    public void addDriver(Driver d) { drivers.addIfAbsent(d); }
    public void removeDriver(Driver d) { drivers.remove(d); }

    /**
     * Finds nearest driver and attempts to atomically claim them by CAS on availability.
     * Returns the claimed driver or null if none can be claimed.
     */
    public Driver findAndClaimNearestDriver(Location pickup, double maxDistanceAllowed) {
        Driver best = null;
        double bestDist = Double.MAX_VALUE;

        for (Driver d : drivers) {
            if (!d.isAvailable()) continue;
            double dist = helper.distanceTo(pickup, d.getLocation());
            if (dist < bestDist && dist <= maxDistanceAllowed) {
                bestDist = dist;
                best = d;
            }
        }

        if (best == null) return null;

        // attempt to claim
        boolean claimed = best.compareAndSetAvailable(true, false);
        if (claimed) return best;
        // if failed (race), you could retry; keep it simple for LLD
        return null;
    }
}

package com.bookMyShow.service;

import java.util.HashMap;
import java.util.Map;

public class SeatLockManager {
    private final Map<String, Map<String, SeatLock>> seatLocks; // showId -> (seatId -> SeatLock)
    public SeatLockManager(){
        this.seatLocks = new HashMap<>();
    }

    public synchronized boolean lockSeat(String showId, String seatId, String userId, long timeoutMillis) {
        seatLocks.putIfAbsent(showId, new HashMap<>());
        Map<String, SeatLock> lockedSeats = seatLocks.get(showId);

        if (lockedSeats.containsKey(seatId) && lockedSeats.get(seatId).isActive()) {
            return false; // already locked
        }

        long expiryTime = System.currentTimeMillis() + timeoutMillis;
        lockedSeats.put(seatId, new SeatLock(userId, expiryTime));
        return true;
    }

    public synchronized boolean isSeatLocked(String showId, String seatId, String userId) {
        if (!seatLocks.containsKey(showId)) return false;
        SeatLock lock = seatLocks.get(showId).get(seatId);
        return lock != null && lock.isActive() && lock.getUserId().equals(userId);
    }

    public synchronized void unlockSeat(String showId, String seatId, String userId) {
        if (!seatLocks.containsKey(showId)) return;
        Map<String, SeatLock> lockedSeats = seatLocks.get(showId);

        SeatLock lock = lockedSeats.get(seatId);
        if (lock != null && lock.getUserId().equals(userId)) {
            lockedSeats.remove(seatId);
        }
    }

    // Helper class
    private static class SeatLock {
        private final String userId;
        private final long expiryTime;

        public SeatLock(String userId, long expiryTime) {
            this.userId = userId;
            this.expiryTime = expiryTime;
        }

        public String getUserId() {
            return userId;
        }

        public boolean isActive() {
            return System.currentTimeMillis() < expiryTime;
        }
    }
}

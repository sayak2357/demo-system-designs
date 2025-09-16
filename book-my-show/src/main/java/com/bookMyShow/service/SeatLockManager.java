package com.bookMyShow.service;

import java.util.HashMap;
import java.util.Map;

public class SeatLockManager {
    private final Map<String, Map<String, SeatLock>> seatLocks; // showId -> (seatId -> SeatLock)

    public SeatLockManager() {
        this.seatLocks = new HashMap<>();
    }

    /**
     * Try to lock a seat for a given user with timeout.
     * If the seat is already locked and active, return false.
     */
    public synchronized boolean lockSeat(String showId, String seatId, String userId, long timeoutMillis) {
        seatLocks.putIfAbsent(showId, new HashMap<>());
        removeExpiredLocks(showId);  // cleanup expired locks

        Map<String, SeatLock> lockedSeats = seatLocks.get(showId);
        if (lockedSeats.containsKey(seatId) && lockedSeats.get(seatId).isActive()) {
            System.out.println("[LOCK-FAILED] Seat " + seatId + " already locked for show " + showId);
            return false; // already locked
        }

        long expiryTime = System.currentTimeMillis() + timeoutMillis;
        lockedSeats.put(seatId, new SeatLock(userId, expiryTime));

        System.out.println("[LOCKED] Seat " + seatId + " locked by user " + userId +
                " for show " + showId + " until " + expiryTime);

        return true;
    }

    /**
     * Check if a seat is locked by ANY user.
     */
    public synchronized boolean isSeatLockedByAnyone(String showId, String seatId) {
        removeExpiredLocks(showId);
        if (!seatLocks.containsKey(showId)) return false;

        SeatLock lock = seatLocks.get(showId).get(seatId);
        return lock != null && lock.isActive();
    }

    /**
     * Check if a seat is locked by a specific user.
     */
    public synchronized boolean isSeatLockedByUser(String showId, String seatId, String userId) {
        removeExpiredLocks(showId);
        if (!seatLocks.containsKey(showId)) return false;

        SeatLock lock = seatLocks.get(showId).get(seatId);
        return lock != null && lock.isActive() && lock.getUserId().equals(userId);
    }

    /**
     * Unlock seat explicitly if it belongs to the same user.
     */
    public synchronized void unlockSeat(String showId, String seatId, String userId) {
        removeExpiredLocks(showId);
        if (!seatLocks.containsKey(showId)) return;

        Map<String, SeatLock> lockedSeats = seatLocks.get(showId);
        SeatLock lock = lockedSeats.get(seatId);

        if (lock != null && lock.getUserId().equals(userId)) {
            lockedSeats.remove(seatId);
            System.out.println("[UNLOCKED] Seat " + seatId + " unlocked by user " + userId +
                    " for show " + showId);
        }
    }

    /**
     * Remove all expired locks for a given show.
     */
    private void removeExpiredLocks(String showId) {
        if (!seatLocks.containsKey(showId)) return;
        Map<String, SeatLock> lockedSeats = seatLocks.get(showId);

        lockedSeats.entrySet().removeIf(entry -> {
            boolean expired = !entry.getValue().isActive();
            if (expired) {
                System.out.println("[EXPIRED-LOCK] Seat " + entry.getKey() +
                        " lock expired for show " + showId +
                        " (was locked by user " + entry.getValue().getUserId() + ")");
            }
            return expired;
        });
    }

    /**
     * Inner class representing a seat lock.
     */
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

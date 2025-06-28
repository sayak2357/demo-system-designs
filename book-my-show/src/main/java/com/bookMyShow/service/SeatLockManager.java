package com.bookMyShow.service;

import java.util.HashMap;
import java.util.Map;

public class SeatLockManager {
    private Map<String, Map<String,Long>> seatLocks;
    public SeatLockManager(){
        this.seatLocks = new HashMap<>();
    }

    public synchronized boolean lockSeat(String showId,String seatId, long timeoutMillis){
        this.seatLocks.putIfAbsent(showId,new HashMap<>());
        Map<String,Long> lockedSeats = this.seatLocks.get(showId);

        if(lockedSeats.containsKey(seatId) && lockedSeats.get(seatId)>System.currentTimeMillis()){
            return false;  // already locked
        }
        lockedSeats.put(seatId,System.currentTimeMillis()+timeoutMillis);
        return true;
    }

    public boolean isSeatLocked(String showId, String seatId){
        return this.seatLocks.containsKey(showId) && this.seatLocks.get(showId).getOrDefault(seatId,0L)>System.currentTimeMillis();
    }
}

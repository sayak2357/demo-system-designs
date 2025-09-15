package com.parkingLot.service;

public class MonitoringService {
    public void notifyChange(int occupied, int capacity){
        System.out.println("Change in occupancy: "+occupied+" /"+capacity);
    }
}

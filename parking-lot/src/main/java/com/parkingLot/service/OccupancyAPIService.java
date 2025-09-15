package com.parkingLot.service;

import com.parkingLot.model.ParkingSlot;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class OccupancyAPIService {
    private ParkingService parkingService;

    public OccupancyAPIService(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public Map<String, Object> getOccupancyStatus() {
        int capacity = parkingService.getCapacity();
        int occupied = parkingService.getOccupiedSpots();
        int available = capacity - occupied;

        Map<String, Object> response = new HashMap<>();
        response.put("capacity", capacity);
        response.put("occupied", occupied);
        response.put("available", available);
        response.put("timestamp", Instant.now().toString());

        return response;
    }
}

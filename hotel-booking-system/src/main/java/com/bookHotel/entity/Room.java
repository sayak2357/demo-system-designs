package com.bookHotel.entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private String roomId;
    private RoomType roomType;
    Map<LocalDate,Boolean> availablityMap;

    public Room(String roomId, RoomType roomType) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.availablityMap = new HashMap<>();
    }

    public boolean isAvailable(LocalDate checkIn, LocalDate checkOut){
        for(LocalDate date = checkIn;!date.isAfter(checkOut);date = date.plusDays(1)){
            if(availablityMap.getOrDefault(date,true)==false)
                return false;
        }
        return true;
    }

    public void reserve(LocalDate checkIn,LocalDate checkOut){
        for(LocalDate date=checkIn;!date.isAfter(checkOut);date=date.plusDays(1)){
            availablityMap.put(date,false);
        }
    }

    public void cancel(LocalDate in, LocalDate out){
        for(LocalDate date=in;!date.isAfter(out);date=date.plusDays(1)){
            availablityMap.put(date,true);
        }
    }

}

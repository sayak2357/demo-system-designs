package com.bookHotel.entity;

import java.util.List;

public class Hotel {
    private String id;
    private String name;
    private String location;
    private List<Room> rooms;

    public Hotel(String id, String name, String location, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rooms = rooms;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}

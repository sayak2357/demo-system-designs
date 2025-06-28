package com.bookMyShowV2.model;

import java.util.ArrayList;
import java.util.UUID;

public class Cinema {
    private final String cinemaId,name,address;
    private final List<Hall> hallList;

    public Cinema(String name, String address) {
        this.cinemaId = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.hallList = new ArrayList<>();
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public List<Hall> getHallList() {
        return hallList;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public void addHall(Hall hall){
        this.hallList.add(hall);
    }
}

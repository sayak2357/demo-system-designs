package com.bookMyShowV2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class City {
    private final String cityId,name;
    private final Double lat,lng;
    private List<Cinema> cinemas;

    public City(String name, Double lat, Double lng) {
        this.cityId = UUID.randomUUID().toString();
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.cinemas = new ArrayList<>();
    }

    public String getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void addCinema(Cinema cinema){
        this.cinemas.add(cinema);
    }

    @Override
    public String toString(){
        return "cityId="+cityId+",name="+name+",latitude="+lat+",longitude="+lng;
    }
}

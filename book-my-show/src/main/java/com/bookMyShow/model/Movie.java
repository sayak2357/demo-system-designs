package com.bookMyShow.model;

import java.time.Duration;


public class Movie {
    private String id,name;
    private Duration duration;

    public Movie(String id, Duration duration, String name) {
        this.id = id;
        this.duration = duration;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }
}

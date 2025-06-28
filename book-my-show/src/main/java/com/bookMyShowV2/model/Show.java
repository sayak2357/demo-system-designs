package com.bookMyShowV2.model;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class Show {
    private final String showId;
    private final Movie movie;
    private final long startTime;
    private final HashSet<Integer> bookedSeatNumber;

    public Show(Movie movie, long startTime) {
        this.showId = UUID.randomUUID().toString();
        this.bookedSeatNumber = new HashSet<>();
        this.movie = movie;
        this.startTime = startTime;
    }

    public String getShowId() {
        return showId;
    }

    public HashSet<Integer> getBookedSeatNumber() {
        return bookedSeatNumber;
    }

    public long getStartTime() {
        return startTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void addSeatNumberAsBooked(List<Integer> booked){
        this.bookedSeatNumber.addAll(booked);
    }
}

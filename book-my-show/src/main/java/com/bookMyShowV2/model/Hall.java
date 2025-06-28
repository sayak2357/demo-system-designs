package com.bookMyShowV2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Hall {
    private final String hallId;
    private final List<Seat> seatMap;
    private final List<Show> showList;

    public Hall() {
        this.hallId = UUID.randomUUID().toString();
        this.seatMap = new ArrayList<>();
        this.showList = new ArrayList<>();
    }

    public List<Show> getShowList() {
        return showList;
    }

    public String getHallId() {
        return hallId;
    }
    public void addShow(Show show){
        this.showList.add(show);
    }

    public void addSeat(Seat seat){
        this.seatMap.add(seat);
    }

    public List<Seat> getSeatMap() {
        return seatMap;
    }
}

package com.foodOrder.service;

import com.foodOrder.entity.MenuItem;

public class RatingService {
    public void rateMenuItem(MenuItem item,int rating){
        if(rating<1 || rating>5){
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        item.addRating(rating);
    }

    public double getAverageRating(MenuItem item) {
        return item.getAverageRating();
    }

    public int getRatingCount(MenuItem item) {
        return item.getRatingCount();
    }
}

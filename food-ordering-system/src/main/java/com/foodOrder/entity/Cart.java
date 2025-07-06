package com.foodOrder.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item){
        this.items.add(item);
    }

    public void removeItem(MenuItem item){
        this.items.remove(item);
    }

    public void clearCart(){
        this.items.clear();
    }

    public List<MenuItem> getItems(){
        return this.items;
    }
}

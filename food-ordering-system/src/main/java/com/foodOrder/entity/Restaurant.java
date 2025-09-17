package com.foodOrder.entity;

import java.util.List;

public class Restaurant {
    private String id;
    private String name;
    private String location;
    private List<MenuItem> menu;

    public Restaurant(String id, String name, String location, List<MenuItem> menu) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.menu = menu;
    }
}

package com.splitwise.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<User> users;
    private String name;

    public Group(String name,List<User> users){
        this.name = name;
        this.users = users;
    }
    public void addUser(User user){
        if(!this.users.contains(user))
            this.users.add(user);
    }
    public List<User> getUsers(){
        return this.users;
    }

    public String getName() {
        return name;
    }
}

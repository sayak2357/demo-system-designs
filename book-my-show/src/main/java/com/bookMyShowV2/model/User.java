package com.bookMyShowV2.model;

import java.util.UUID;

public class User {
    private final String userId,name,email;

    public User(String name, String email) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString(){
        return "User{"+"userId="+userId+",name="+name+",email="+email;
    }
}

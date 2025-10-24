package com.zerodha.model;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static Map<Integer,User> userMap = new HashMap<>();

    public UserManager() {

    }

    public static void addUser(int id,User user){
        userMap.put(id,user);
    }

    public static User getUser(int id){
        return userMap.getOrDefault(id,null);
    }
}

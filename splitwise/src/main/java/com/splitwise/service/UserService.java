package com.splitwise.service;

import com.splitwise.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserService {
    private Map<Integer, User> userMap;

    public UserService() {
        this.userMap = new HashMap<>();
    }
    public void addUser(User user){
        this.userMap.put(user.getId(),user);
    }
    public User getUser(int userId){
        return this.userMap.getOrDefault(userId,null);
    }
    public Set<Integer> getUserIds(){
        return this.userMap.keySet();
    }
}

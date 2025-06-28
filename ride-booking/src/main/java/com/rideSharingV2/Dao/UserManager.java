package com.rideSharingV2.Dao;

import com.rideSharingV2.Exceptions.InvalidUser;
import com.rideSharingV2.Exceptions.UserAlreadyExists;
import com.rideSharingV2.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> users;

    public UserManager() {
        this.users = new HashMap<>();
    }

    public void addUser(User user){
        if(users.containsKey(user.getName()))
            throw new UserAlreadyExists();
        users.put(user.getName(),user);
    }

    public User getUser(String userName){
        if(!users.containsKey(userName))
            throw new InvalidUser();
        return users.get(userName);
    }

    public Collection<User> getUsers(){
        return users.values();
    }
}

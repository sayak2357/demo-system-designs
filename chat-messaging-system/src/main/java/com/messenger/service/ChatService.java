package com.messenger.service;

import com.messenger.entity.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatService {

    Map<String, Chat> chats = new HashMap<>();

    public String createOneToOneChat(User u1, User u2){
        Chat chat = new OneToOneChat(u1,u2);
        chats.put(chat.getId(),chat);
        return chat.getId();
    }

    public String createGroupChat(List<User> users){
        Chat chat = new GroupChat(users);
        chats.put(chat.getId(),chat);
        return chat.getId();
    }

    public void sendMessage(String chatId, Message message){
        Chat chat = chats.get(chatId);
        chat.sendMessage(message);
    }

    public void markMessageAsRead(String chatId, String messageId, User user){
        Chat chat = chats.get(chatId);
        chat.markAsRead(messageId,user);
    }
}

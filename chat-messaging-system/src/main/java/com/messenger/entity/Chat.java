package com.messenger.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Chat {
    public String id;
    public List<User> participants;
    List<Message> messages = new ArrayList<>();

    public Chat(List<User> participants) {
        this.id = UUID.randomUUID().toString();
        this.participants = participants;
    }

    public abstract void sendMessage(Message message);

    public abstract void markAsRead(String messageId, User user);

    public String getId() {
        return id;
    }
}

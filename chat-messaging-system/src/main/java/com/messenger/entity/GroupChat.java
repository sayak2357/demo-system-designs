package com.messenger.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupChat extends Chat{

    Map<String, DeliveryStatus> deliveryStatusMap = new HashMap<>();

    public GroupChat(List<User> participants) {
        super(participants);
    }

    @Override
    void sendMessage(Message message) {
        messages.add(message);
        for(User u:participants){
            if(!u.getId().equals(message.getSender().getId())){
                deliveryStatusMap.put(message.getId()+"_"+u.getId(),DeliveryStatus.SENT);
            }
        }
    }

    @Override
    void markAsRead(String messageId, User user) {
        String key = messageId+"_"+user.getId();
        if(deliveryStatusMap.containsKey(key)){
            deliveryStatusMap.put(key,DeliveryStatus.READ);
        }
    }
}

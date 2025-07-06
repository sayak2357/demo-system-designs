package com.messenger.entity;

import java.util.List;

public class OneToOneChat extends Chat{

    public OneToOneChat(User u1, User u2){
        super(List.of(u1,u2));
    }


    @Override
    void sendMessage(Message message) {
        messages.add(message);
        System.out.println("message sent from: "+message.getSender().getName());
    }

    @Override
    void markAsRead(String messageId, User user) {
            messages.stream().filter(m -> m.getId().equals(messageId)).findFirst().ifPresent(m -> m.getDeliveryStatus() = DeliveryStatus.READ);
    }

}

package com.messenger.entity;

import java.util.UUID;

public abstract class Message {
     String id;
     User sender;
     long timeStamp;
     DeliveryStatus deliveryStatus;

    public Message(User sender) {
        this.id = UUID.randomUUID().toString();
        this.sender = sender;
        this.timeStamp = System.currentTimeMillis();
        this.deliveryStatus = DeliveryStatus.SENT;
    }

    public String getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }
}

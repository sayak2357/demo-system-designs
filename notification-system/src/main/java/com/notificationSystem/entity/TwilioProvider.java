package com.notificationSystem.entity;

public class TwilioProvider implements NotificationProvider{

    @Override
    public boolean send(Notification notification) {
        System.out.println("Sending sms via Twilio: "+notification.getMessage());
        return false;
    }
}

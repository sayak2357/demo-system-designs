package com.notificationSystem.entity;

public class SendGridProvider implements NotificationProvider{

    @Override
    public boolean send(Notification notification) {
        System.out.println("Sending email via SendGrip: "+notification.getMessage());
        return false;
    }
}

package com.notificationSystem.entity;

public class SMSChannel implements NotificationChannel{

    private NotificationProvider provider;

    public SMSChannel(NotificationProvider provider) {
        this.provider = provider;
    }

    @Override
    public boolean send(Notification notification) {
        return provider.send(notification);
    }
}

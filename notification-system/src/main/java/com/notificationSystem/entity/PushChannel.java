package com.notificationSystem.entity;

public class PushChannel implements NotificationChannel{

    private NotificationProvider provider;

    public PushChannel(NotificationProvider provider) {
        this.provider = provider;
    }

    @Override
    public boolean send(Notification notification) {
        return provider.send(notification);
    }
}

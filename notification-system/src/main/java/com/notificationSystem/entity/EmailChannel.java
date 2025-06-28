package com.notificationSystem.entity;
import com.notificationSystem.entity.NotificationProvider;
public class EmailChannel implements NotificationChannel{

    private NotificationProvider provider;

    public EmailChannel(NotificationProvider provider) {
        this.provider = provider;
    }

    @Override
    public boolean send(Notification notification) {
        return provider.send(notification);
    }
}

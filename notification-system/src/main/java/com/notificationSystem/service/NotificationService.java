package com.notificationSystem.service;

import com.notificationSystem.entity.*;

import java.util.HashMap;
import java.util.Map;

public class NotificationService {

    private Map<NotificationType, NotificationChannel> channels = new HashMap<>();

    public NotificationService() {
        channels.put(NotificationType.EMAIL,new EmailChannel(new SendGridProvider()));
        channels.put(NotificationType.SMS,new SMSChannel(new TwilioProvider()));
    }

    public void sendNotification(Notification notification){
        NotificationChannel channel = channels.get(notification.getNotificationType());
        boolean success = channel.send(notification);

        if(!success){
            System.out.println("Retrying...");
            channel.send(notification);  // naive retry
        }
    }
}

package com.notificationSystem.entity;

public class Notification {
    private String to;
    private String message;
    private NotificationType notificationType;

    public Notification(String to, String message, NotificationType notificationType) {
        this.to = to;
        this.message = message;
        this.notificationType = notificationType;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }
}

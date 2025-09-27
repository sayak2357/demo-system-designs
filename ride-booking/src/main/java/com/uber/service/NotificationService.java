package com.uber.service;

import com.uber.model.Driver;
import com.uber.model.User;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Very small notification service. In a real system you'd integrate push notifications / SMS / websockets.
 * This class allows registering callbacks for drivers and users.
 */
public class NotificationService {
    private final List<Consumer<String>> listeners = new CopyOnWriteArrayList<>();

    public void registerListener(Consumer<String> listener) {
        listeners.add(listener);
    }

    public void removeListener(Consumer<String> listener) {
        listeners.remove(listener);
    }

    public void notifyAll(String message) {
        for (Consumer<String> l : listeners) {
            try { l.accept(message); } catch (Exception ignored) {}
        }
    }

    public void notifyUser(User user, String message) {
        notifyAll("User[" + user.getId() + "] " + message);
    }

    public void notifyDriver(Driver driver, String message) {
        notifyAll("Driver[" + driver.getId() + "] " + message);
    }
}

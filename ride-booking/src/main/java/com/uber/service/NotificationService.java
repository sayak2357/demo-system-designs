package com.uber.service;

import com.uber.observer.Notifier;
import com.uber.observer.Observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotificationService implements Notifier {
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }
}

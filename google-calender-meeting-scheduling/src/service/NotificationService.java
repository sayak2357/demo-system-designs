package service;

import entity.Notifier;
import entity.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotificationService implements Notifier {

    private List<Observer> observers;
    public NotificationService(){
        this.observers = new CopyOnWriteArrayList<>(); // thread safe
    }

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

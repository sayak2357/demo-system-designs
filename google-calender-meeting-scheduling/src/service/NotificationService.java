package service;

import entity.Notifier;
import entity.Observer;

import java.util.ArrayList;
import java.util.List;

public class NotificationService implements Notifier {

    private List<Observer> observers;
    public NotificationService(){
        this.observers = new ArrayList<>();
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

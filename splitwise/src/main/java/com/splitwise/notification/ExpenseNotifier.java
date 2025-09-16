package com.splitwise.notification;

import com.splitwise.service.ExpenseService;

import java.util.ArrayList;
import java.util.List;

public class ExpenseNotifier implements Notifier{
    private List<Observer> observers;
    public ExpenseNotifier(){
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

package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class User implements Observer{
    private int id;
    private String name;
    private List<Event> events;
    private LocalDateTime workingHourStart;
    private LocalDateTime workingHourEnd;

    public LocalDateTime getWorkingHourStart() {
        return workingHourStart;
    }

    public void setWorkingHourStart(LocalDateTime workingHourStart) {
        this.workingHourStart = workingHourStart;
    }

    public LocalDateTime getWorkingHourEnd() {
        return workingHourEnd;
    }

    public void setWorkingHourEnd(LocalDateTime workingHourEnd) {
        this.workingHourEnd = workingHourEnd;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        events = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event){
        this.events.add(event);
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(String message) {
        System.out.println("message for user: "+name+" "+message);
    }
}

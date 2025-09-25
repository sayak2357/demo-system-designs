package entity;

import java.util.ArrayList;
import java.util.List;
public class User {
    private int id;
    private String name;
    private List<Event> events;

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


}

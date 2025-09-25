package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private String id;
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private User host;
    private List<MeetingInvite> invites;

    public Event(String id, String title, LocalDateTime start, LocalDateTime end, User host) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.host = host;
        invites = new ArrayList<>();
    }

    public void addInvite(MeetingInvite invite){
        this.invites.add(invite);
    }

    public boolean isOverlap(LocalDateTime s, LocalDateTime e){
        return !(s.isAfter(end) || e.isBefore(s));
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public User getHost() {
        return host;
    }

    public List<MeetingInvite> getInvites() {
        return invites;
    }
}

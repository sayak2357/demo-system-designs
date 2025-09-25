package service;

import entity.Event;
import entity.MeetingInvite;
import entity.RSVPStatus;
import entity.User;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CalenderService {
    public boolean hasConflict(User user, LocalDateTime start, LocalDateTime end){
            return user.getEvents().stream().anyMatch(e-> e.isOverlap(start,end));
    }

    public Event createEvent(String title,LocalDateTime start,LocalDateTime end, User host, List<User> participants){
        if(hasConflict(host,start,end)){
            throw new RuntimeException("Host is not available at this time");
        }

        Event event = new Event(UUID.randomUUID().toString(),title,start,end,host);
        host.addEvent(event);
        for(User p:participants){
            if(hasConflict(p,start,end)){
                System.out.println("Warning: "+p.getName()+" has conflict.");
            }
            MeetingInvite meetingInvite = new MeetingInvite(p);
            event.addInvite(meetingInvite);
            p.addEvent(event);        // add event to calender reagrdless of RSVP
        }
        return event;
    }

    public List<Event> getUserSchedule(User user, LocalDateTime from, LocalDateTime to){
        return user.getEvents().stream().filter(e->e.getStart().isAfter(from.minusSeconds(1)) && e.getEnd().isBefore(to.plusSeconds(1)))
        .sorted(Comparator.comparing(e -> e.getStart())).collect(Collectors.toList());
    }

    public void respondToInvite(User user, Event event, RSVPStatus status){
        for(MeetingInvite invite:event.getInvites()){
            if(invite.getParticipant().getId()==user.getId()){
                invite.updateStatus(status);
                break;
            }
        }
    }
}

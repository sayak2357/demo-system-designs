package service;

import entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CalendarService {
    private NotificationService notificationService;

    public CalendarService(){
        this.notificationService = new NotificationService();
    }
    public boolean hasConflict(User user, LocalDateTime start, LocalDateTime end){
            return user.getEvents().stream().anyMatch(e-> e.isOverlap(start,end));
    }

    public Event createEvent(String title,LocalDateTime start,LocalDateTime end, User host, List<User> participants){
        if(hasConflict(host,start,end)){
            throw new RuntimeException("Host is not available at this time");
        }
        List<Observer> observers = new ArrayList<>();
        Event event = new Event(UUID.randomUUID().toString(),title,start,end,host);
        host.addEvent(event);
        for(User p:participants){
            if(hasConflict(p,start,end)){
                System.out.println("Warning: "+p.getName()+" has conflict.");
            }
            if(isOutsideWorkingHours(start,end,p)){
                System.out.println(p.getName()+" is not working at the hour");
                continue;
            }
            MeetingInvite meetingInvite = new MeetingInvite(p);
            event.addInvite(meetingInvite);
            p.addEvent(event);        // add event to calender reagrdless of RSVP
            observers.add(p);
            notificationService.registerObserver(p);
        }
        notificationService.notifyObservers("invited to event: "+title);

        for(Observer ob:observers){
            notificationService.removeObserver(ob);
        }
        return event;
    }
    public RecurringEvent createRecurringEvent(String title, LocalDateTime start, LocalDateTime end,
                                               User host, List<User> participants,
                                               RecurrencePattern pattern, LocalDateTime recurrenceEnd) {
        RecurringEvent recurringEvent = new RecurringEvent(
                UUID.randomUUID().toString(), title, start, end, host, pattern, recurrenceEnd
        );
        host.addEvent(recurringEvent);

        for (User p : participants) {
            MeetingInvite invite = new MeetingInvite(p);
            recurringEvent.addInvite(invite);
            p.addEvent(recurringEvent);
            notificationService.registerObserver(p);
        }

        notificationService.notifyObservers("Invited to recurring event: " + title);
        return recurringEvent;
    }

    public List<Event> expandRecurringEvent(RecurringEvent recurringEvent) {
        List<Event> occurrences = new ArrayList<>();
        LocalDateTime currentStart = recurringEvent.getStart();
        LocalDateTime currentEnd = recurringEvent.getEnd();

        while (!currentStart.isAfter(recurringEvent.getRecurrenceEnd())) {
            Event occurrence = new Event(
                    UUID.randomUUID().toString(),
                    recurringEvent.getTitle(),
                    currentStart,
                    currentEnd,
                    recurringEvent.getHost()
            );

            for (MeetingInvite invite : recurringEvent.getInvites()) {
                occurrence.addInvite(new MeetingInvite(invite.getParticipant()));
            }

            occurrences.add(occurrence);

            currentStart = getNextOccurrence(currentStart, recurringEvent.getRecurrencePattern());
            currentEnd = getNextOccurrence(currentEnd, recurringEvent.getRecurrencePattern());
        }
        return occurrences;
    }

    private LocalDateTime getNextOccurrence(LocalDateTime time, RecurrencePattern pattern) {
        return switch (pattern) {
            case DAILY -> time.plusDays(1);
            case WEEKLY -> time.plusWeeks(1);
            case MONTHLY -> time.plusMonths(1);
        };
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
    public boolean isOutsideWorkingHours(LocalDateTime start, LocalDateTime end, User u){
        return start.isAfter(u.getWorkingHourStart().minusSeconds(1)) && end.isBefore(u.getWorkingHourEnd().plusSeconds(1));
    }
}

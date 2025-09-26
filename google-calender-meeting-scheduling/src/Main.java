import entity.RecurrencePattern;
import entity.RecurringEvent;
import entity.User;
import service.CalendarService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to Google Calender");
        CalendarService calendarService = new CalendarService();

        User host = new User(1, "Alice");
        User bob = new User(2, "Bob");
        User charlie = new User(3, "Charlie");

        // One-time event
        calendarService.createEvent(
                "Team Sync",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                host,
                Arrays.asList(bob, charlie)
        );

        // Recurring event
        RecurringEvent recurringEvent = calendarService.createRecurringEvent(
                "Daily Standup",
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusMinutes(15),
                host,
                List.of(bob),
                RecurrencePattern.DAILY,
                LocalDateTime.now().plusDays(5)
        );

        System.out.println("\nExpanded occurrences of recurring event:");
        calendarService.expandRecurringEvent(recurringEvent).forEach(e ->
                System.out.println(e.getTitle() + " at " + e.getStart())
        );
    }
}
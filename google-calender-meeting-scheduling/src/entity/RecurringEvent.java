package entity;

import java.time.LocalDateTime;

public class RecurringEvent extends Event {
    private RecurrencePattern recurrencePattern;
    private LocalDateTime recurrenceEnd;

    public RecurringEvent(String id, String title, LocalDateTime start, LocalDateTime end,
                          User host, RecurrencePattern pattern, LocalDateTime recurrenceEnd) {
        super(id, title, start, end, host);
        this.recurrencePattern = pattern;
        this.recurrenceEnd = recurrenceEnd;
    }

    public RecurrencePattern getRecurrencePattern() {
        return recurrencePattern;
    }

    public LocalDateTime getRecurrenceEnd() {
        return recurrenceEnd;
    }
}

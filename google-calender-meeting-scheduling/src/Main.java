import entity.Event;
import entity.RSVPStatus;
import entity.User;
import service.CalenderService;

import java.time.LocalDateTime;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to Google Calender");
        User sam = new User(1,"Sam");
        User anil = new User(2,"Anil");

        CalenderService calenderService = new CalenderService();

        LocalDateTime start = LocalDateTime.of(2025,9,26,10,0);
        LocalDateTime end = LocalDateTime.of(2025,9,26,11,0);

        Event standup = calenderService.createEvent("DSM",start,end, sam,List.of(anil));
        calenderService.respondToInvite(anil,standup, RSVPStatus.ACCEPTED);

        calenderService.getUserSchedule(sam,start.minusHours(1),end.plusHours(2))
                .forEach(e -> System.out.println(e.getTitle()+ " at "+e.getStart()));
    }
}
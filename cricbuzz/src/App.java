import models.*;
import repositories.MatchRepository;
import services.*;
import web.WebSocketNotifier;
import controllers.MatchController;

import java.util.Arrays;
import java.util.UUID;
import java.util.function.Consumer;

public class App {
    public static void main(String[] args) throws InterruptedException {
        // setup teams
        Player p1 = new Player("p1", "Rohit Sharma", PlayerRole.BATSMAN);
        Player p2 = new Player("p2", "Virat Kohli", PlayerRole.BATSMAN);
        Team india = new Team("t1", "India", Arrays.asList(p1, p2));

        Player p3 = new Player("p3", "Joe Root", PlayerRole.BATSMAN);
        Team england = new Team("t2", "England", Arrays.asList(p3));

        // infra
        MatchRepository repo = new MatchRepository();
        EventBus eventBus = new EventBus(4);
        NotificationService<String> notifier = new NotificationService<>();

        // wire websocket notifier to notification service
        WebSocketNotifier ws = new WebSocketNotifier();
        notifier.subscribe(msg -> ws.pushToClients("match-1", msg));

        MatchService matchService = new MatchService(repo, eventBus, notifier);
        MatchController controller = new MatchController(matchService);

        Match match = new Match(UUID.randomUUID().toString(), india, england, 20);
        matchService.startMatch(match);

        // simulate balls
        BallEvent ball1 = new BallEvent(0, 1, "p3", "p1", BallEvent.Type.RUNS, 4, "Boundary! Four to the rope");
        controller.postBallEvent(match.getId(), ball1);

        System.out.println("Score: " + controller.getScore(match.getId()));

        BallEvent ball2 = new BallEvent(0, 2, "p3", "p1", BallEvent.Type.WICKET, 0, "Caught at slip - WICKET");
        controller.postBallEvent(match.getId(), ball2);

        System.out.println("Score: " + controller.getScore(match.getId()));

        // give event bus some time to finish async handlers
        Thread.sleep(500);
        eventBus.shutdown();
    }
}
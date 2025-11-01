package services;

import models.BallEvent;
import models.Innings;
import models.Match;
import repositories.MatchRepository;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * High level orchestration of matches. Receives ball events, updates score, publishes
 * live commentary/notifications.
 */
public class MatchService {
    private final MatchRepository matchRepo;
    private final EventBus eventBus;
    private final NotificationService<String> notificationService;

    public MatchService(MatchRepository matchRepo, EventBus eventBus, NotificationService<String> notificationService) {
        this.matchRepo = matchRepo;
        this.eventBus = eventBus;
        this.notificationService = notificationService;
    }

    public void startMatch(Match match) {
        match.setStatus(Match.Status.LIVE);
        match.setStartTime(Instant.now());
        matchRepo.save(match);
        notificationService.publish("Match " + match.getId() + " is LIVE");
    }

    public void submitBall(String matchId, BallEvent event) {
        Match match = matchRepo.findById(matchId);
        if (match == null) throw new IllegalArgumentException("Match not found");

        // determine which innings to update: simplistic logic - if innings1 not complete -> update
        Innings target = !match.getInnings1().isComplete() ? match.getInnings1() : match.getInnings2();
        ScoreService scoreService = new ScoreService(target);

        // apply update synchronously to keep ordering for a match - can be improved using per-match queue
        scoreService.applyBallEvent(event);

        // publish event to event bus for analytics, persistence, and live commentary
        eventBus.publish(event, e -> {
            // nl: generate commentary and send notifications
            String commentary = String.format("Over %d.%d: %s", e.getOverNumber(), e.getBallInOver(), e.getDescription());
            notificationService.publish(commentary);
            // TODO: persist to DB or write-ahead-log
        });
    }

    public String getScore(String matchId) {
        Match match = matchRepo.findById(matchId);
        Innings target = !match.getInnings1().isComplete() ? match.getInnings1() : match.getInnings2();
        return new ScoreService(target).getScore();
    }
}

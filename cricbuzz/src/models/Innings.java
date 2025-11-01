package models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Innings {
    private final Team battingTeam;
    private final Team bowlingTeam;
    private final List<BallEvent> events = new ArrayList<>();
    private final AtomicInteger runs = new AtomicInteger(0);
    private final AtomicInteger wickets = new AtomicInteger(0);
    private final AtomicInteger balls = new AtomicInteger(0); // total legal balls
    private final int oversLimit; // -1 for unlimited

    public Innings(Team battingTeam, Team bowlingTeam, int oversLimit) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.oversLimit = oversLimit;
    }

    public synchronized void addBallEvent(BallEvent event) {
        events.add(event);
        // update balls only for legal deliveries
        if (event.getType() != BallEvent.Type.WIDE && event.getType() != BallEvent.Type.NO_BALL) {
            balls.incrementAndGet();
        }
        // update runs and wickets
        runs.addAndGet(event.getRuns());
        if (event.getType() == BallEvent.Type.WICKET) wickets.incrementAndGet();
    }

    public int getRuns() { return runs.get(); }
    public int getWickets() { return wickets.get(); }
    public int getBalls() { return balls.get(); }
    public List<BallEvent> getEvents() { return events; }
    public Team getBattingTeam() { return battingTeam; }
    public Team getBowlingTeam() { return bowlingTeam; }
    public boolean isComplete() {
        if (oversLimit > 0) {
            return balls.get() >= oversLimit * 6;
        }
        return false;
    }
}
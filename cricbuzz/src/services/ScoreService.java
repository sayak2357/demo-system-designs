package services;

import models.BallEvent;
import models.Innings;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Responsible for updating scoreboard in a thread-safe way and exposing read APIs.
 */
public class ScoreService {
    private final Innings innings;
    private final ReentrantReadWriteLock rw = new ReentrantReadWriteLock();

    public ScoreService(Innings innings) {
        this.innings = innings;
    }

    public void applyBallEvent(BallEvent event) {
        rw.writeLock().lock();
        try {
            innings.addBallEvent(event);
        } finally {
            rw.writeLock().unlock();
        }
    }

    public String getScore() {
        rw.readLock().lock();
        try {
            int runs = innings.getRuns();
            int wickets = innings.getWickets();
            int balls = innings.getBalls();
            int overs = balls / 6;
            int ballInOver = balls % 6;
            return String.format("%d/%d (%d.%d overs)", runs, wickets, overs, ballInOver);
        } finally {
            rw.readLock().unlock();
        }
    }
}
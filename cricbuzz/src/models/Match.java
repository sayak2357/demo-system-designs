package models;

import java.time.Instant;

public class Match {
    public enum Status { SCHEDULED, LIVE, PAUSED, COMPLETED }

    private final String id;
    private final Team teamA;
    private final Team teamB;
    private Innings innings1;
    private Innings innings2;
    private volatile Status status = Status.SCHEDULED;
    private Instant startTime;

    public Match(String id, Team teamA, Team teamB, int oversPerInnings) {
        this.id = id;
        this.teamA = teamA;
        this.teamB = teamB;
        this.innings1 = new Innings(teamA, teamB, oversPerInnings);
        this.innings2 = new Innings(teamB, teamA, oversPerInnings);
    }

    public String getId() { return id; }
    public Team getTeamA() { return teamA; }
    public Team getTeamB() { return teamB; }
    public Innings getInnings1() { return innings1; }
    public Innings getInnings2() { return innings2; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public void setStartTime(Instant t) { this.startTime = t; }
    public Instant getStartTime() { return startTime; }
}
package models;

public class BallEvent {
    public enum Type { DOT, RUNS, WIDE, NO_BALL, WICKET }

    private final int overNumber;
    private final int ballInOver; // 1..6
    private final String bowlerId;
    private final String batsmanId;
    private final Type type;
    private final int runs; // excluding extras (or including depending on design)
    private final String description;

    public BallEvent(int overNumber, int ballInOver, String bowlerId, String batsmanId, Type type, int runs, String description) {
        this.overNumber = overNumber;
        this.ballInOver = ballInOver;
        this.bowlerId = bowlerId;
        this.batsmanId = batsmanId;
        this.type = type;
        this.runs = runs;
        this.description = description;
    }

    // getters
    public int getOverNumber() { return overNumber; }
    public int getBallInOver() { return ballInOver; }
    public String getBowlerId() { return bowlerId; }
    public String getBatsmanId() { return batsmanId; }
    public Type getType() { return type; }
    public int getRuns() { return runs; }
    public String getDescription() { return description; }
}

package models;

public class Player {
    private final String id;
    private final String name;
    private final PlayerRole role; // BATSMAN, BOWLER, ALLROUNDER, WICKETKEEPER

    public Player(String id, String name, PlayerRole role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public PlayerRole getRole() { return role; }
}

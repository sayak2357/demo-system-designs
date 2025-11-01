package repositories;

import models.Match;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MatchRepository {
    private final Map<String, Match> store = new ConcurrentHashMap<>();

    public void save(Match match) { store.put(match.getId(), match); }
    public Match findById(String id) { return store.get(id); }
}

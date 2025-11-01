package controllers;

import models.BallEvent;
import services.MatchService;

/**
 * Thin controller that accepts HTTP/WebSocket commands in a web app. Here just a POJO to show contract.
 */
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) { this.matchService = matchService; }

    // example method invoked by REST endpoint
    public void postBallEvent(String matchId, BallEvent event) {
        matchService.submitBall(matchId, event);
    }

    public String getScore(String matchId) {
        return matchService.getScore(matchId);
    }
}
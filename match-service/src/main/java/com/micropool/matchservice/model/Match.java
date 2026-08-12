package com.micropool.matchservice.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Match {

    // Ball groups pre-assigned at creation - no open-table phase
    private static final List<String> SOLIDS =
            List.of("SOLID_1", "SOLID_2", "SOLID_3", "SOLID_4", "SOLID_5", "SOLID_6", "SOLID_7");
    private static final List<String> STRIPES =
            List.of("STRIPE_9", "STRIPE_10", "STRIPE_11", "STRIPE_12", "STRIPE_13", "STRIPE_14", "STRIPE_15");

    private final String matchId;
    private final String player1;
    private final String player2;
    private MatchStatus status;
    private String currentTurn;
    private final Map<String, List<String>> ballsRemaining;
    private boolean eightBallPotted;
    private String winner;

    public Match(String matchId, String player1, String player2) {
        this.matchId = matchId;
        this.player1 = player1;
        this.player2 = player2;
        this.status = MatchStatus.ACTIVE;
        this.currentTurn = player1;                 // player1 breaks
        this.ballsRemaining = new LinkedHashMap<>();
        this.ballsRemaining.put(player1, new ArrayList<>(SOLIDS));
        this.ballsRemaining.put(player2, new ArrayList<>(STRIPES));
        this.eightBallPotted = false;
        this.winner = null;
    }

    // used when potting balls in the DefaultMatchRulesEngine
    public void removeBalls(String player, int count) {
        // fetch player's remaining balls
        List<String> balls = ballsRemaining.get(player);
        // prevents more balls being removed than balls remaining (i.e player tries to pot 2 balls but they only have black remaining)
        int toRemove = Math.min(count, balls.size());
        for (int i = 0; i < toRemove; i++) {
            balls.remove(balls.size() - 1);
        }
    }

    public void switchTurn() {
        this.currentTurn = opponentOf(currentTurn);
    }

    public void setEightBallPotted() {
        this.eightBallPotted = true;
    }

    public void completeWith(String winner) {
        this.status = MatchStatus.COMPLETED;
        this.winner = winner;
    }

    // used when switching the turn to the opposite player (either player1 or player2)
    public String opponentOf(String player) {
        return player.equals(player1) ? player2 : player1;
    }

    public boolean isOnEightBall(String player) {
        List<String> balls = ballsRemaining.get(player);
        return balls != null && balls.isEmpty();
    }

    public int remainingCount(String player) {
        List<String> balls = ballsRemaining.get(player);
        return balls == null ? 0 : balls.size();
    }


    // GETTERS


    public String getMatchId() {
        return matchId;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    public Map<String, List<String>> getBallsRemaining() {
        return ballsRemaining;
    }

    public boolean isEightBallPotted() {
        return eightBallPotted;
    }

    public String getWinner() {
        return winner;
    }
}

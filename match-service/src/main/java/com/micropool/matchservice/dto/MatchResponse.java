package com.micropool.matchservice.dto;

import com.micropool.matchservice.model.Match;
import com.micropool.matchservice.model.MatchStatus;

import java.util.List;
import java.util.Map;

public class MatchResponse {
    public String matchId;
    public String player1;
    public String player2;
    public MatchStatus status;
    public String currentTurn;
    public Map<String, List<String>> ballsRemaining;
    public boolean eightBallPotted;
    public String winner;

    public static MatchResponse from(Match m) {
        MatchResponse r = new MatchResponse();
        r.matchId = m.getMatchId();
        r.player1 = m.getPlayer1();
        r.player2 = m.getPlayer2();
        r.status = m.getStatus();
        r.currentTurn = m.getCurrentTurn();
        r.ballsRemaining = m.getBallsRemaining();
        r.eightBallPotted = m.isEightBallPotted();
        r.winner = m.getWinner();
        return r;
    }
}

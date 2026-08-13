package com.micropool.leagueservice.dto;

import jakarta.validation.constraints.NotBlank;

public class RecordResultRequest {

    @NotBlank
    private String matchId;

    @NotBlank
    private String winner;

    @NotBlank
    private String loser;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getLoser() {
        return loser;
    }

    public void setLoser(String loser) {
        this.loser = loser;
    }
}

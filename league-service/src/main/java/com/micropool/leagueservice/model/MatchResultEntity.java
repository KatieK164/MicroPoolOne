package com.micropool.leagueservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class MatchResultEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String matchId;

    private String winner;

    private String loser;

    private Instant completedAt;

    public MatchResultEntity() {
    }

    public MatchResultEntity(String matchId, String winner, String loser, Instant completedAt) {
        this.matchId = matchId;
        this.winner = winner;
        this.loser = loser;
        this.completedAt = completedAt;
    }

    public Long getId() {
        return id;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getWinner() {
        return winner;
    }

    public String getLoser() {
        return loser;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }
}

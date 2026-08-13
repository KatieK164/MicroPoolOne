package com.micropool.leagueservice.dto;

public class LeaderboardEntry {

    private String playerName;
    private int wins;
    private int losses;

    public LeaderboardEntry() {
    }

    public LeaderboardEntry(String playerName, int wins, int losses) {
        this.playerName = playerName;
        this.wins = wins;
        this.losses = losses;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
}

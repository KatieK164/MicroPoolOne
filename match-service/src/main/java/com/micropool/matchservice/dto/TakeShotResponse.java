package com.micropool.matchservice.dto;

public class TakeShotResponse {
    public String shotResult;
    public int ballsPotted;
    public boolean foul;
    public boolean turnChanged;
    public MatchResponse match;

    public TakeShotResponse(String shotResult, int ballsPotted, boolean foul,
                             boolean turnChanged, MatchResponse match) {
        this.shotResult = shotResult;
        this.ballsPotted = ballsPotted;
        this.foul = foul;
        this.turnChanged = turnChanged;
        this.match = match;
    }
}

package com.micropool.matchservice.model;


public record ShotOutcome(ShotResult result, int resultCode) {

    public int ballsPotted() {
        return switch (result) {
            case POT_ONE -> 1;
            case POT_TWO -> 2;
            case FOUL, MISS -> 0;
        };
    }

    public boolean isPot() {
        return result == ShotResult.POT_ONE || result == ShotResult.POT_TWO;
    }
}

package com.micropool.matchservice.model;


public enum ShotResult {
    FOUL,
    MISS,
    // design simplification -- it's very rare that you will pot more than 2 balls on the one shot
    POT_ONE,
    POT_TWO
}

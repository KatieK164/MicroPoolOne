package com.micropool.matchservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TakeShotRequest {
    @NotBlank
    private String player;
    @Min(0) @Max(359)
    private int angle;
    @Min(0) @Max(100)
    private int power;
    private int spin = 0;

    public String getPlayer() { return player; }
    public void setPlayer(String player) { this.player = player; }
    public int getAngle() { return angle; }
    public void setAngle(int angle) { this.angle = angle; }
    public int getPower() { return power; }
    public void setPower(int power) { this.power = power; }
    public int getSpin() { return spin; }
    public void setSpin(int spin) { this.spin = spin; }
}

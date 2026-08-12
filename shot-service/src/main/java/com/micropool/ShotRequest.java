package com.micropool;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ShotRequest {

    @NotNull(message = "angle is required")
    @Min(value = 0, message = "angle must be at least 0")
    @Max(value = 360, message = "angle must be at most 360")
    private Integer angle;

    @NotNull(message = "power is required")
    @Min(value = 0, message = "power must be at least 0")
    @Max(value = 100, message = "power must be at most 100")
    private Integer power;

    public ShotRequest() {
    }

    public ShotRequest(Integer angle, Integer power) {
        this.angle = angle;
        this.power = power;
    }

    public Integer getAngle() {
        return angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}

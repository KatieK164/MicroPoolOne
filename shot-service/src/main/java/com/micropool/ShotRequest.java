package com.micropool;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ShotRequest {

    private Integer angle;
    private Integer power;
    private Integer spin;

    public ShotRequest() {
    }

    public ShotRequest(Integer angle, Integer power, Integer spin) {
        this.angle = angle;
        this.power = power;
        this.spin = spin;
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

    public Integer getSpin() {
        return spin;
    }

    public void setSpin(Integer spin) {
        this.spin = spin;
    }
}

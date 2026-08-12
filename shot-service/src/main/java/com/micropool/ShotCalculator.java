package com.micropool;

import org.springframework.stereotype.Service;

@Service
public class ShotCalculator {

    public ShotResult calculate(int angle, int power, int spin) {
        int resultCode = (angle + power + spin) % 10;

        if (resultCode == 0) {
            return ShotResult.FOUL;
        } else if (resultCode <= 4) {
            return ShotResult.MISS;
        } else if (resultCode <= 8) {
            return ShotResult.POT_ONE;
        } else {
            return ShotResult.POT_TWO;
        }
    }

    public int getResultCode(int angle, int power, int spin) {
        return (angle + power + spin) % 10;
    }
}

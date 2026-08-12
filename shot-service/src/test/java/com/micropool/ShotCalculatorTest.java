package com.micropool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShotCalculatorTest {

    private final ShotCalculator calculator = new ShotCalculator();


    @Test
    void resultCode0IsFoul() {
        assertEquals(ShotResult.FOUL, calculator.calculate(10, 0));
    }


    @Test
    void resultCode1IsMiss() {
        assertEquals(ShotResult.MISS, calculator.calculate(1, 0));
    }


    @Test
    void resultCode2IsMiss() {
        assertEquals(ShotResult.MISS, calculator.calculate(2, 0));
    }

    @Test
    void resultCode3IsMiss() {
        assertEquals(ShotResult.MISS, calculator.calculate(3, 0));
    }


    @Test
    void resultCode4IsMiss() {
        assertEquals(ShotResult.MISS, calculator.calculate(4, 0));
    }


    @Test
    void resultCode5IsPotOne() {
        assertEquals(ShotResult.POT_ONE, calculator.calculate(5, 0));
    }


    @Test
    void resultCode6IsPotOne() {
        assertEquals(ShotResult.POT_ONE, calculator.calculate(6, 0));
    }

    @Test
    void resultCode7IsPotOne() {
        assertEquals(ShotResult.POT_ONE, calculator.calculate(7, 0));
    }


    @Test
    void resultCode8IsPotOne() {
        assertEquals(ShotResult.POT_ONE, calculator.calculate(8, 0));
    }


    @Test
    void resultCode9IsPotTwo() {
        assertEquals(ShotResult.POT_TWO, calculator.calculate(9, 0));
    }
}

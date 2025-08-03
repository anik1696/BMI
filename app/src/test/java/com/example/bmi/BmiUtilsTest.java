package com.example.bmi;

import org.junit.Test;

import static org.junit.Assert.*;

public class BmiUtilsTest {

    @Test
    public void testBmiCalculation() {
        float bmi = BmiUtils.calculateBmi(70, 5, 9); // 5 feet 9 inches, 70kg
        assertEquals(22.99f, bmi, 0.1f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroHeightThrowsException() {
        BmiUtils.calculateBmi(70, 0, 0);
    }

    @Test
    public void testBmiIsCorrectForAnotherValue() {
        float bmi = BmiUtils.calculateBmi(90, 6, 2); // 6ft 2in, 90kg
        assertEquals(25.89f, bmi, 0.1f);
    }
}

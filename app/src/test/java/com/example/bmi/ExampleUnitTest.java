package com.example.bmi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit tests, which will execute on the development machine (host).
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void subtraction_isCorrect() {
        assertEquals(0, 2 - 2);
    }

    @Test
    public void multiplication_isCorrect() {
        assertEquals(9, 3 * 3);
    }

    @Test
    public void division_isCorrect() {
        assertEquals(2, 10 / 5);
    }

    @Test
    public void floatingPointComparison() {
        assertEquals(3.14f, 3.14f, 0.0001);
    }

    // BMI calculation test - corrected expected value
    @Test
    public void bmiCalculationSample() {
        float bmi = BmiUtils.calculateBmi(70, 5, 9);  // weight=70kg, height=5ft 9in
        assertEquals(22.79f, bmi, 0.1f);  // Acceptable margin of error 0.1
    }

    // Test for zero height input should throw IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void bmiWithZeroHeightThrowsException() {
        BmiUtils.calculateBmi(65, 0, 0);
    }

    // Lower BMI boundary test - changed weight for correct underweight BMI
    @Test
    public void bmiLowerBoundary() {
        float bmi = BmiUtils.calculateBmi(40, 5, 0);  // 40kg, 5ft 0in
        assertTrue("BMI should be under 18.5 for underweight", bmi < 18.5f);
    }

    // Upper BMI boundary test - Obese range
    @Test
    public void bmiUpperBoundary() {
        float bmi = BmiUtils.calculateBmi(95, 5, 6);  // 95kg, 5ft 6in
        assertTrue("BMI should be over 30 for obese", bmi > 30f);
    }
}

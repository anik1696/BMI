package com.example.bmi;

import org.junit.Test;

import static org.junit.Assert.*;

public class OverWeightUnitTest {

    @Test
    public void dummyTest_shouldPass() {
        // Dummy test to check test environment
        assertTrue(true);
    }

    @Test
    public void activityClassExists() {
        try {
            Class<?> clazz = Class.forName("com.example.bmi.OverWeight");
            assertNotNull("OverWeight activity should exist", clazz);
        } catch (ClassNotFoundException e) {
            fail("OverWeight activity class not found");
        }
    }
}

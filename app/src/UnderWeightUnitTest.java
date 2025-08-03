package com.example.bmi;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnderWeightUnitTest {

    @Test
    public void dummyTest_passes() {
        // Just a dummy test to validate test setup
        assertEquals(4, 2 + 2);
    }

    @Test
    public void activityClassExists() {
        try {
            Class<?> clazz = Class.forName("com.example.bmi.UnderWeight");
            assertNotNull(clazz);
        } catch (ClassNotFoundException e) {
            fail("UnderWeight activity class not found");
        }
    }
}

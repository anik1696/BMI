package com.example.bmi;

public class BmiUtils {

    public static float calculateBmi(float weightKg, float feet, float inches) {
        float heightInMeters = (feet * 0.3048f) + (inches * 0.0254f);
        if (heightInMeters <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
        return weightKg / (heightInMeters * heightInMeters);
    }
}

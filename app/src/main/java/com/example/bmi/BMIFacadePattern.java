package com.example.bmi;

import android.content.Context;
import android.content.Intent;

public class BMIFacadePattern {
    public static void main(String[] args) {
        
        BMIFacade bmiFacade = new BMIFacade();
        bmiFacade.calculateAndDisplayBMI(170, 65);
    }
}

class BMIFacade {
    private BMIValidator validator;
    private BMICalculator calculator;
    private BMICategoryDeterminer determiner;
    
    public BMIFacade() {
        validator = new BMIValidator();
        calculator = new BMICalculator();
        determiner = new BMICategoryDeterminer();
    }
    
    public void calculateAndDisplayBMI(double height, double weight) {
        if (validator.validate(height, weight)) {
            double bmi = calculator.calculate(height, weight);
            String category = determiner.determineCategory(bmi);
            System.out.println("BMI: " + bmi + ", Category: " + category);
        } else {
            System.out.println("Invalid input");
        }
    }
}

class BMIValidator {
    public boolean validate(double height, double weight) {
        return height > 0 && weight > 0 && height < 300 && weight < 300;
    }
}

class BMICalculator {
    public double calculate(double height, double weight) {
        double heightInMeters = height / 100;
        return weight / (heightInMeters * heightInMeters);
    }
}

class BMICategoryDeterminer {
    public String determineCategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25) return "Normal";
        if (bmi < 30) return "Overweight";
        if (bmi < 35) return "Obese";
        return "Extremely Obese";
    }
}

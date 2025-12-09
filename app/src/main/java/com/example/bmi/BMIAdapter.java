package com.example.bmi;

import android.content.ContentValues;

public class BMIAdapter {
    
    public ContentValues adaptBMIForDatabase(float bmi, String timestamp, String category) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_BMI, String.format("%.2f", bmi));
        values.put(DBHelper.COL_TIMESTAMP, timestamp);
        // You could add category to database if needed
        return values;
    }
    
    public String adaptBMIToString(float bmi) {
        if (bmi < 18.5) {
            return String.format("Your BMI is %.2f\nYou are underweight", bmi);
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return String.format("Your BMI is %.2f\nYou are normal", bmi);
        } else if (bmi >= 25 && bmi <= 29.9) {
            return String.format("Your BMI is %.2f\nYou are overweight", bmi);
        } else if (bmi >= 30 && bmi <= 34.9) {
            return String.format("Your BMI is %.2f\nYou are obese", bmi);
        } else {
            return String.format("Your BMI is %.2f\nYou are extremely obese", bmi);
        }
    }
}

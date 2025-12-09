package com.example.bmi;

public class BMIMemento {
    private final float bmi;
    private final String timestamp;
    private final String category;
    
    public BMIMemento(float bmi, String timestamp, String category) {
        this.bmi = bmi;
        this.timestamp = timestamp;
        this.category = category;
    }
    
    public float getBMI() {
        return bmi;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public String getCategory() {
        return category;
    }
}

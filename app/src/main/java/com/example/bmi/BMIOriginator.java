package com.example.bmi;

public class BMIOriginator {
    private float bmi;
    private String timestamp;
    private String category;
    
    public void setState(float bmi, String timestamp, String category) {
        this.bmi = bmi;
        this.timestamp = timestamp;
        this.category = category;
    }
    
    public BMIMemento saveToMemento() {
        return new BMIMemento(bmi, timestamp, category);
    }
    
    public void restoreFromMemento(BMIMemento memento) {
        this.bmi = memento.getBMI();
        this.timestamp = memento.getTimestamp();
        this.category = memento.getCategory();
    }
    
    public float getBmi() {
        return bmi;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public String getCategory() {
        return category;
    }
}

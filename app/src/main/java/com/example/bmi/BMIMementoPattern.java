package com.example.bmi;

import java.util.ArrayList;
import java.util.List;

public class BMIMementoPattern {
    public static void main(String[] args) {
        BMIHistory history = new BMIHistory();
        BMIRecord record = new BMIRecord(25.6, "Overweight");
        history.addMemento(record.saveToMemento());
      
        record.setBmi(24.8);
        record.setCategory("Normal");
        history.addMemento(record.saveToMemento());
        record.restoreFromMemento(history.getMemento(0));
        System.out.println("Restored: " + record.getBmi() + " - " + record.getCategory());
    }
}

class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>();
    
    public void add(Memento state) {
        mementoList.add(state);
    }
    
    public Memento get(int index) {
        return mementoList.get(index);
    }
}

class BMIRecord {
    private double bmi;
    private String category;
    
    public BMIRecord(double bmi, String category) {
        this.bmi = bmi;
        this.category = category;
    }
    
    public double getBmi() {
        return bmi;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setBmi(double bmi) {
        this.bmi = bmi;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public Memento saveToMemento() {
        return new Memento(bmi, category);
    }
    
    public void restoreFromMemento(Memento memento) {
        this.bmi = memento.getBmi();
        this.category = memento.getCategory();
    }
}

class Memento {
    private double bmi;
    private String category;
    
    public Memento(double bmi, String category) {
        this.bmi = bmi;
        this.category = category;
    }
    
    public double getBmi() {
        return bmi;
    }
    
    public String getCategory() {
        return category;
    }
}

class BMIHistory {
    private List<Memento> history = new ArrayList<>();
    
    public void addMemento(Memento memento) {
        history.add(memento);
    }
    
    public Memento getMemento(int index) {
        return history.get(index);
    }
}

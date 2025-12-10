package com.example.bmi;

import android.database.Cursor;

public class BMIIteratorPattern {
    public static void main(String[] args) {
        BMIHistoryCollection collection = new BMIHistoryCollection();
        
        for (Iterator iter = collection.getIterator(); iter.hasNext();) {
            BMIRecordItem record = (BMIRecordItem) iter.next();
            System.out.println("BMI: " + record.getBmi() + ", Category: " + record.getCategory());
        }
    }
}

interface Container {
    Iterator getIterator();
}

interface Iterator {
    boolean hasNext();
    Object next();
}

class BMIRecordItem {
    private double bmi;
    private String category;
    
    public BMIRecordItem(double bmi, String category) {
        this.bmi = bmi;
        this.category = category;
    }
    
    public double getBmi() { return bmi; }
    public String getCategory() { return category; }
}

class BMIHistoryCollection implements Container {
    private BMIRecordItem[] records = {
        new BMIRecordItem(25.6, "Overweight"),
        new BMIRecordItem(22.3, "Normal"),
        new BMIRecordItem(19.8, "Normal"),
        new BMIRecordItem(31.2, "Obese")
    };
    
    @Override
    public Iterator getIterator() {
        return new BMIHistoryIterator();
    }
    
    private class BMIHistoryIterator implements Iterator {
        int index = 0;
        
        @Override
        public boolean hasNext() {
            return index < records.length;
        }
        
        @Override
        public Object next() {
            if (this.hasNext()) {
                return records[index++];
            }
            return null;
        }
    }
}

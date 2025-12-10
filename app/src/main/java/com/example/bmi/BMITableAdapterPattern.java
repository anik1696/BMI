package com.example.bmi;

import android.database.Cursor;

public class BMITableAdapterPattern {
    public static void main(String[] args) {
      
        BMIData data = new BMIData("BMI: 25.6, Category: Overweight");
        DataAdapter adapter = new TableDataAdapter(data);
        adapter.display();
    }
}

interface DataAdapter {
    void display();
}

class BMIData {
    private String data;
    
    public BMIData(String data) {
        this.data = data;
    }
    
    public String getData() {
        return data;
    }
}

class TableDataAdapter implements DataAdapter {
    private BMIData bmiData;
    
    public TableDataAdapter(BMIData bmiData) {
        this.bmiData = bmiData;
    }
    
    @Override
    public void display() {
        String data = bmiData.getData();
        System.out.println("Displaying in table format: " + data);
    }
}

class ChartDataAdapter implements DataAdapter {
    private BMIData bmiData;
    
    public ChartDataAdapter(BMIData bmiData) {
        this.bmiData = bmiData;
    }
    
    @Override
    public void display() {
        String data = bmiData.getData();
        System.out.println("Displaying in chart format: " + data);
    }
}

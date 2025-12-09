package com.example.bmi;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BMIFacade {
    private Context context;
    private DBSingleton dbHelper;
    private BMIAdapter adapter;
    private BMISubject subject;
    private BMIOriginator originator;
    private BMICaretaker caretaker;
    
    public BMIFacade(Context context) {
        this.context = context;
        this.dbHelper = DBSingleton.getInstance(context);
        this.adapter = new BMIAdapter();
        this.subject = new BMISubject();
        this.originator = new BMIOriginator();
        this.caretaker = new BMICaretaker();
    }
    
    
    public String calculateBMI(float weight, float feet, float inches) {
        try {
            float bmi = BmiUtils.calculateBmi(weight, feet, inches);
            String category = getBMICategory(bmi);
            
            
            subject.setBMIState(bmi, category);
            
            
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            originator.setState(bmi, timestamp, category);
            caretaker.addMemento(originator.saveToMemento());
            
            return adapter.adaptBMIToString(bmi);
            
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }
    
    
    public boolean saveBMIResult(float bmi) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        ContentValues values = adapter.adaptBMIForDatabase(bmi, timestamp, getBMICategory(bmi));
        
        DatabaseCommand command = new InsertBMICommand(dbHelper.getInstance(context), values);
        return command.execute();
    }
    
    
    public Intent getAdviceIntent(float bmi) {
        return BMIAdviceFactory.createAdviceIntent(context, bmi);
    }
    
  
    public BMIResultCollection getAllBMIData() {
        Cursor cursor = dbHelper.getInstance(context).getAllData();
        return new BMIResultCollection(cursor);
    }
    
    
    public boolean deleteAllBMIData() {
        DatabaseCommand command = new DeleteAllCommand(dbHelper.getInstance(context));
        return command.execute();
    }
    
    
    private String getBMICategory(float bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal";
        else if (bmi < 30) return "Overweight";
        else if (bmi < 35) return "Obese";
        else return "Extremely Obese";
    }
    
    
    public void attachObserver(BMIObserver observer) {
        subject.attach(observer);
    }
    
    public void detachObserver(BMIObserver observer) {
        subject.detach(observer);
    }
    
    
    public void undo() {
        BMIMemento memento = caretaker.undo();
        if (memento != null) {
            originator.restoreFromMemento(memento);
        }
    }
    
    public void redo() {
        BMIMemento memento = caretaker.redo();
        if (memento != null) {
            originator.restoreFromMemento(memento);
        }
    }
    
    public boolean canUndo() {
        return caretaker.canUndo();
    }
    
    public boolean canRedo() {
        return caretaker.canRedo();
    }
}

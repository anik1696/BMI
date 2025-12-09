package com.example.bmi;

import android.database.Cursor;

public class BMIResultCollection {
    private Cursor cursor;
    
    public BMIResultCollection(Cursor cursor) {
        this.cursor = cursor;
    }
    
    public BMIResultIterator createIterator() {
        return new BMIResultIterator(cursor);
    }
}

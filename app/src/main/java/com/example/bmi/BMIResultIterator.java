package com.example.bmi;

import android.database.Cursor;

import java.util.Iterator;

public class BMIResultIterator implements Iterator<String[]> {
    private Cursor cursor;
    private int position = -1;
    
    public BMIResultIterator(Cursor cursor) {
        this.cursor = cursor;
        if (cursor != null) {
            cursor.moveToFirst();
        }
    }
    
    @Override
    public boolean hasNext() {
        return cursor != null && !cursor.isAfterLast();
    }
    
    @Override
    public String[] next() {
        if (cursor == null || cursor.isAfterLast()) {
            return null;
        }
        
        String[] result = new String[3];
        result[0] = cursor.getString(0); // id
        result[1] = cursor.getString(1); // bmi
        result[2] = cursor.getString(2); // timestamp
        
        cursor.moveToNext();
        position++;
        
        return result;
    }
    
    public void reset() {
        if (cursor != null) {
            cursor.moveToFirst();
            position = -1;
        }
    }
    
    public void close() {
        if (cursor != null) {
            cursor.close();
        }
    }
}package

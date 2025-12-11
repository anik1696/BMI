package com.example.bmi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class SingletonDBHelper extends SQLiteOpenHelper {
    private static SingletonDBHelper instance;
    private static final String DB_NAME = "bmi_data.db";
    private static final String TABLE = "bmi_records";
    
    private SingletonDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }
    
    public static synchronized SingletonDBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonDBHelper(context);
        }
        return instance;
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " (id INTEGER PRIMARY KEY, bmi REAL, timestamp TEXT)");
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
    
    public boolean insertBMI(float bmi, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("bmi", bmi);
        values.put("timestamp", time);
        return db.insert(TABLE, null, values) != -1;
    }
    
    public List<String> getAllRecords() {
        List<String> records = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " ORDER BY id DESC", null);
        
        while (cursor.moveToNext()) {
            records.add(String.format("ID:%d BMI:%.2f Time:%s",
                cursor.getInt(0), cursor.getFloat(1), cursor.getString(2)));
        }
        cursor.close();
        return records;
    }
    
    public void clearAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, null, null);
    }
}

package com.example.bmi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BMIDatabaseSingleton extends SQLiteOpenHelper {
    private static BMIDatabaseSingleton instance;
    
    private static final String DATABASE_NAME = "BMI_DB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "bmi_records";
    
    private BMIDatabaseSingleton(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public static synchronized BMIDatabaseSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new BMIDatabaseSingleton(context.getApplicationContext());
        }
        return instance;
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "bmi REAL NOT NULL, " +
                     "category TEXT NOT NULL, " +
                     "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(sql);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    
    public long insertBMI(double bmi, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("bmi", bmi);
        values.put("category", category);
        return db.insert(TABLE_NAME, null, values);
    }
    
    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + 
                          " ORDER BY timestamp DESC", null);
    }
    
    public int deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, null, null);
    }
    
    public void showMessage() {
        System.out.println("BMI Database Connected!");
    }
}

// DBHelper.java
package com.example.bmi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bmi_data.db";
    public static final String TABLE_NAME = "bmi_records";
    public static final String COL_ID = "id";
    public static final String COL_BMI = "bmi";
    public static final String COL_TIMESTAMP = "timestamp";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1); // version = 1
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_BMI + " TEXT, " +
                        COL_TIMESTAMP + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertBMI(String bmi, String timestamp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_BMI, bmi);
        contentValues.put(COL_TIMESTAMP, timestamp);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY id DESC", null);
    }

    public boolean deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(TABLE_NAME, null, null);
        return deletedRows > 0;
    }

}

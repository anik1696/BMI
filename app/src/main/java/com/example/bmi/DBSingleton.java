package com.example.bmi;

import android.content.Context;

public class DBSingleton {
    private static DBHelper instance;
    
    private DBSingleton() {}
    
    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }
}

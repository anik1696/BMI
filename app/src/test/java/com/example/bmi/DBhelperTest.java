package com.example.bmi;

import android.content.Context;
import android.database.Cursor;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBHelperTest {

    private DBHelper dbHelper;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        dbHelper = new DBHelper(context);
        dbHelper.deleteAllData();
    }

    @After
    public void tearDown() {
        dbHelper.close();
    }

    @Test
    public void testInsertAndRetrieve() {
        boolean success = dbHelper.insertBMI("23.1", "2025-08-03 15:30");
        assertTrue(success);

        Cursor cursor = dbHelper.getAllData();
        assertNotNull(cursor);
        assertTrue(cursor.moveToFirst());

        String bmi = cursor.getString(cursor.getColumnIndexOrThrow("bmi"));
        String timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"));

        assertEquals("23.1", bmi);
        assertEquals("2025-08-03 15:30", timestamp);

        cursor.close();
    }

    @Test
    public void testDeleteAll() {
        dbHelper.insertBMI("25.5", "2025-08-03 16:00");
        assertTrue(dbHelper.deleteAllData());

        Cursor cursor = dbHelper.getAllData();
        assertNotNull(cursor);
        assertEquals(0, cursor.getCount());
        cursor.close();
    }
}

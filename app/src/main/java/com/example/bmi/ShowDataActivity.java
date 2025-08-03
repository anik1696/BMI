package com.example.bmi;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ShowDataActivity extends AppCompatActivity {

    TableLayout tableLayout;
    Button btnDeleteAll;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data); // Your layout file name

        tableLayout = findViewById(R.id.tableLayout); // Ensure this matches the XML id
        btnDeleteAll = findViewById(R.id.btnDeleteAll);
        dbHelper = new DBHelper(this);

        loadBMIData();

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean deleted = dbHelper.deleteAllData();
                if (deleted) {
                    Toast.makeText(ShowDataActivity.this, "All BMI records deleted.", Toast.LENGTH_SHORT).show();
                    tableLayout.removeAllViews();  // Clear table data
                    TextView noData = new TextView(ShowDataActivity.this);
                    noData.setText("No data available.");
                    tableLayout.addView(noData);
                } else {
                    Toast.makeText(ShowDataActivity.this, "No data to delete.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadBMIData() {
        Cursor cursor = dbHelper.getAllData();

        // Clear the table in case of old data
        tableLayout.removeAllViews();

        if (cursor.getCount() == 0) {
            TextView noData = new TextView(this);
            noData.setText("No data available.");
            tableLayout.addView(noData);
            return;
        }

        // Create and add the header row
        TableRow headerRow = new TableRow(this);
        headerRow.setBackgroundColor(Color.LTGRAY);

        String[] headers = {"ID", "BMI", "Timestamp"};
        for (String header : headers) {
            TextView tv = new TextView(this);
            tv.setText(header);
            tv.setPadding(16, 16, 16, 16);
            tv.setTextSize(16);
            tv.setTextColor(Color.BLACK);
            headerRow.addView(tv);
        }
        tableLayout.addView(headerRow);

        // Add data rows
        while (cursor.moveToNext()) {
            TableRow row = new TableRow(this);

            for (int i = 0; i < 3; i++) {
                TextView tv = new TextView(this);
                tv.setText(cursor.getString(i));
                tv.setPadding(16, 16, 16, 16);
                tv.setTextSize(14);
                tv.setTextColor(Color.DKGRAY);
                row.addView(tv);
            }
            tableLayout.addView(row);
        }
    }
}

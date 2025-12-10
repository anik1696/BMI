package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.database.Cursor;

public class ShowDataActivity extends AppCompatActivity {
    
    private TableLayout tableLayout;
    private Button btnDeleteAll;
    private BMIDatabaseSingleton db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        
        tableLayout = findViewById(R.id.tableLayout);
        btnDeleteAll = findViewById(R.id.btnDeleteAll);
        db = BMIDatabaseSingleton.getInstance(this);

        loadBMIData();

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    
                BMIDatabase database = new BMIDatabase();
                Command deleteCommand = new DeleteAllCommand(database);
                BMIOperationInvoker invoker = new BMIOperationInvoker();
                invoker.takeCommand(deleteCommand);
                invoker.executeCommands();
                
                // Clear table
                tableLayout.removeAllViews();
                TextView noData = new TextView(ShowDataActivity.this);
                noData.setText("No data available.");
                tableLayout.addView(noData);
                
                Toast.makeText(ShowDataActivity.this, 
                    "All records deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private void loadBMIData() {
        tableLayout.removeAllViews();
        
        Cursor cursor = db.getAllData();
        
        if (cursor.getCount() == 0) {
            TextView noData = new TextView(this);
            noData.setText("No data available.");
            tableLayout.addView(noData);
            return;
        }
        
        TableRow headerRow = new TableRow(this);
        headerRow.setBackgroundColor(Color.parseColor("#6200EE"));
        
        String[] headers = {"ID", "BMI", "Category", "Timestamp"};
        for (String header : headers) {
            TextView tv = new TextView(this);
            tv.setText(header);
            tv.setPadding(10, 10, 10, 10);
            tv.setTextColor(Color.WHITE);
            headerRow.addView(tv);
        }
        tableLayout.addView(headerRow);
        int index = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TableRow row = new TableRow(this);
            row.setBackgroundColor(index % 2 == 0 ? Color.WHITE : Color.parseColor("#F5F5F5"));
            
            // Get data from cursor
            String id = cursor.getString(0);
            String bmi = String.format("%.2f", cursor.getDouble(1));
            String category = cursor.getString(2);
            String timestamp = cursor.getString(3);
            
            String[] values = {id, bmi, category, timestamp};
            
            for (String value : values) {
                TextView tv = new TextView(this);
                tv.setText(value);
                tv.setPadding(10, 10, 10, 10);
                row.addView(tv);
            }
            
            tableLayout.addView(row);
            cursor.moveToNext();
            index++;
        }
        cursor.close();
    }
}

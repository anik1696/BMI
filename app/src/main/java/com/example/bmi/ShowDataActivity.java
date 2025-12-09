package com.example.bmi;

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
    BMIFacade bmiFacade;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        
        tableLayout = findViewById(R.id.tableLayout);
        btnDeleteAll = findViewById(R.id.btnDeleteAll);
        bmiFacade = new BMIFacade(this);
        
        loadBMIData();
        
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean deleted = bmiFacade.deleteAllBMIData();
                if (deleted) {
                    Toast.makeText(ShowDataActivity.this, "All BMI records deleted.", Toast.LENGTH_SHORT).show();
                    tableLayout.removeAllViews();
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
    
        BMIResultCollection collection = bmiFacade.getAllBMIData();
        BMIResultIterator iterator = collection.createIterator();
        
        
        tableLayout.removeAllViews();
        
        if (!iterator.hasNext()) {
            TextView noData = new TextView(this);
            noData.setText("No data available.");
            tableLayout.addView(noData);
            iterator.close();
            return;
        }
        
        
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
        
        
        iterator.reset();
        while (iterator.hasNext()) {
            String[] rowData = iterator.next();
            if (rowData != null) {
                TableRow row = new TableRow(this);
                
                for (int i = 0; i < 3; i++) {
                    TextView tv = new TextView(this);
                    tv.setText(rowData[i]);
                    tv.setPadding(16, 16, 16, 16);
                    tv.setTextSize(14);
                    tv.setTextColor(Color.DKGRAY);
                    row.addView(tv);
                }
                tableLayout.addView(row);
            }
        }
        
        iterator.close();
    }
}

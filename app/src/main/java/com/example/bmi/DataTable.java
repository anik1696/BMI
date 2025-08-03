package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTable extends AppCompatActivity {

    TextView tvResult, underWeight, normalWeight, overWeight, obese, morbidlyObese;
    ImageView clrimg, showData, saveData;
    DBHelper dbHelper;
    String bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_table);

        // Initialize views
        tvResult = findViewById(R.id.tvResult);
        clrimg = findViewById(R.id.clrimg);
        underWeight = findViewById(R.id.underWeight);
        normalWeight = findViewById(R.id.normalWeight);
        overWeight = findViewById(R.id.overWeight);
        obese = findViewById(R.id.obese);
        morbidlyObese = findViewById(R.id.morbidlyObese);
        showData = findViewById(R.id.showData);
        saveData = findViewById(R.id.saveData);

        // Initialize database helper
        dbHelper = new DBHelper(this);

        // Get BMI from intent
        bmi = getIntent().getStringExtra("bmi");

        if (bmi != null) {
            float bmiValue = Float.parseFloat(bmi);

            if (bmiValue < 18.5) {
                tvResult.setText("Your BMI is " + bmi + "\nYou are underweight");
            } else if (bmiValue >= 18.5 && bmiValue <= 24.9) {
                tvResult.setText("Your BMI is " + bmi + "\nYou are normal");
            } else if (bmiValue >= 25 && bmiValue <= 29.9) {
                tvResult.setText("Your BMI is " + bmi + "\nYou are overweight");
            } else if (bmiValue >= 30 && bmiValue <= 34.9) {
                tvResult.setText("Your BMI is " + bmi + "\nYou are obese");
            } else {
                tvResult.setText("Your BMI is " + bmi + "\nYou are extremely obese");
            }
        }

        // Clear BMI result
        clrimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("");
            }
        });

        // Save BMI to SQLite on click
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bmi != null) {
                    String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    boolean inserted = dbHelper.insertBMI(bmi, timestamp);
                    if (inserted) {
                        // Optional: Toast message for user feedback
                        Toast.makeText(DataTable.this, "BMI saved successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DataTable.this, "Failed to save BMI.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        // Show saved BMI data
        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataTable.this, ShowDataActivity.class));
            }
        });

        // Open advice activities
        underWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataTable.this, UnderWeight.class));
            }
        });
        normalWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataTable.this, NormalWright.class));
            }
        });
        overWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataTable.this, OverWeight.class));
            }
        });
        obese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataTable.this, Obese.class));
            }
        });
        morbidlyObese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataTable.this, ExtremelyObese.class));
            }
        });
    }
}

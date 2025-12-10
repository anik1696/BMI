package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private EditText etHeight, etWeight;
    private TextView tvResult;
    private Button btnCalculate, btnViewData;
    private BMIDatabaseSingleton db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize UI
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        tvResult = findViewById(R.id.tvResult);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnViewData = findViewById(R.id.btnViewData);
        
        // Singleton database instance
        db = BMIDatabaseSingleton.getInstance(this);
        
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
        
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowDataActivity.class));
            }
        });
    }
    
    private void calculateBMI() {
        try {
            double height = Double.parseDouble(etHeight.getText().toString());
            double weight = Double.parseDouble(etWeight.getText().toString());
            
            if (height <= 0 || weight <= 0) {
                Toast.makeText(this, "Enter valid values", Toast.LENGTH_SHORT).show();
                return;
            }
            BMIContext context = new BMIContext(new StandardBMICalculator());
            double bmi = context.calculateBMI(height, weight);
            BMICategory category = BMICategoryFactory.createBMICategory(bmi);
            String result = String.format("BMI: %.2f\nCategory: %s\nAdvice: %s", 
                bmi, category.getCategoryName(), category.getAdvice());
            tvResult.setText(result);
            db.insertBMI(bmi, category.getCategoryName());
            Intent intent = BMICategoryFactory.createCategoryActivity(this, bmi);
            startActivity(intent);
            
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}

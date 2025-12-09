package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements BMIObserver {
    
    private EditText edWeight, edFeet, edInch;
    private Button btnEnter;
    private BMIFacade bmiFacade;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edWeight = findViewById(R.id.edWeight);
        edFeet = findViewById(R.id.edFeet);
        edInch = findViewById(R.id.edInch);
        btnEnter = findViewById(R.id.btnEnter);
        
        // Initialize Facade
        bmiFacade = new BMIFacade(this);
        bmiFacade.attachObserver(this);
        
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightStr = edWeight.getText().toString().trim();
                String feetStr = edFeet.getText().toString().trim();
                String inchStr = edInch.getText().toString().trim();
                
                if (weightStr.isEmpty() || feetStr.isEmpty() || inchStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                try {
                    float weight = Float.parseFloat(weightStr);
                    float feet = Float.parseFloat(feetStr);
                    float inch = Float.parseFloat(inchStr);
                    
                    
                    String result = bmiFacade.calculateBMI(weight, feet, inch);
                    
                
                    Intent intent = new Intent(MainActivity.this, DataTable.class);
                    intent.putExtra("bmi", String.format("%.2f", getCurrentBMIValue(result)));
                    intent.putExtra("result_text", result);
                    startActivity(intent);
                    
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    
    private float getCurrentBMIValue(String result) {
        
        try {
            String[] parts = result.split(" ");
            return Float.parseFloat(parts[3]);
        } catch (Exception e) {
            return 0;
        }
    }
    
    @Override
    public void onBMIStateChanged(float bmiValue, String category) {
          runOnUiThread(() -> {
            
        });
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bmiFacade.detachObserver(this);
    }
}

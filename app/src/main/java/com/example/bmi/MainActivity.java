package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edWeight, edFeet, edInch;
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edWeight = findViewById(R.id.edWeight);
        edFeet = findViewById(R.id.edFeet);
        edInch = findViewById(R.id.edInch);
        btnEnter = findViewById(R.id.btnEnter);

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

                float weight = Float.parseFloat(weightStr);
                float feet = Float.parseFloat(feetStr);
                float inch = Float.parseFloat(inchStr);

                float heightInMeters = (feet * 0.3048f) + (inch * 0.0254f);
                float bmi = weight / (heightInMeters * heightInMeters);
                String formattedBmi = String.format("%.2f", bmi);

                Intent intent = new Intent(MainActivity.this, DataTable.class);
                intent.putExtra("bmi", formattedBmi);
                startActivity(intent);
            }
        });
    }
}

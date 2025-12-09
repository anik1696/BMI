package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DataTable extends AppCompatActivity {
    
    TextView tvResult, underWeight, normalWeight, overWeight, obese, morbidlyObese;
    ImageView clrimg, showData, saveData, btnUndo, btnRedo;
    BMIFacade bmiFacade;
    String bmi;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_table);
        
    
        tvResult = findViewById(R.id.tvResult);
        clrimg = findViewById(R.id.clrimg);
        underWeight = findViewById(R.id.underWeight);
        normalWeight = findViewById(R.id.normalWeight);
        overWeight = findViewById(R.id.overWeight);
        obese = findViewById(R.id.obese);
        morbidlyObese = findViewById(R.id.morbidlyObese);
        showData = findViewById(R.id.showData);
        saveData = findViewById(R.id.saveData);
        btnUndo = findViewById(R.id.btnUndo);
        btnRedo = findViewById(R.id.btnRedo);
        
    
        bmiFacade = new BMIFacade(this);
        
        
        String resultText = getIntent().getStringExtra("result_text");
        bmi = getIntent().getStringExtra("bmi");
        
        if (resultText != null) {
            tvResult.setText(resultText);
        }
        
        
        updateUndoRedoButtons();
        
        
        clrimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("");
            }
        });
        
        
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bmi != null) {
                    boolean saved = bmiFacade.saveBMIResult(Float.parseFloat(bmi));
                    if (saved) {
                        Toast.makeText(DataTable.this, "BMI saved successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DataTable.this, "Failed to save BMI.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        
        
        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bmiFacade.undo();
                updateUndoRedoButtons();
                Toast.makeText(DataTable.this, "Undo performed", Toast.LENGTH_SHORT).show();
            }
        });
        
    
        btnRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bmiFacade.redo();
                updateUndoRedoButtons();
                Toast.makeText(DataTable.this, "Redo performed", Toast.LENGTH_SHORT).show();
            }
        });
        
        
        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataTable.this, ShowDataActivity.class));
            }
        });
        
        
        setupAdviceClickListeners();
    }
    
    private void setupAdviceClickListeners() {
        View.OnClickListener adviceClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bmi != null) {
                    float bmiValue = Float.parseFloat(bmi);
                    Intent adviceIntent = bmiFacade.getAdviceIntent(bmiValue);
                    startActivity(adviceIntent);
                }
            }
        };
        
        underWeight.setOnClickListener(adviceClickListener);
        normalWeight.setOnClickListener(adviceClickListener);
        overWeight.setOnClickListener(adviceClickListener);
        obese.setOnClickListener(adviceClickListener);
        morbidlyObese.setOnClickListener(adviceClickListener);
    }
    
    private void updateUndoRedoButtons() {
        btnUndo.setEnabled(bmiFacade.canUndo());
        btnRedo.setEnabled(bmiFacade.canRedo());
    }
}

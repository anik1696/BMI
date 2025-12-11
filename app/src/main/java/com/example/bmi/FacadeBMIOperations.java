package com.example.bmi;

import android.content.Context;
import android.content.Intent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FacadeBMIOperations {
    private Context context;
    
    public FacadeBMIOperations(Context c) { context = c; }
    
    public float calculateBMI(float weight, float feet, float inches) {
        HeightAdapter adapter = AdapterHeightUnits.createFeetAdapter(feet, inches);
        float heightM = adapter.convertToMeters();
        if (heightM <= 0) throw new IllegalArgumentException("Invalid height");
        return weight / (heightM * heightM);
    }
    
    public BMIAdvice getAdvice(float bmi) {
        return FactoryBMIAdvice.createAdvice(bmi);
    }
    
    public Intent getAdviceIntent(float bmi) {
        BMIAdvice advice = getAdvice(bmi);
        return new Intent(context, advice.getActivityClass());
    }
    
    public boolean saveToDatabase(float bmi) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        SingletonDBHelper db = SingletonDBHelper.getInstance(context);
        return db.insertBMI(bmi, timestamp);
    }
}

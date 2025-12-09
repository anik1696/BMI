package com.example.bmi;

import android.content.Context;
import android.content.Intent;

public class BMIAdviceFactory {
    
    public static Intent createAdviceIntent(Context context, float bmi) {
        if (bmi < 18.5) {
            return new Intent(context, UnderWeight.class);
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return new Intent(context, NormalWright.class);
        } else if (bmi >= 25 && bmi <= 29.9) {
            return new Intent(context, OverWeight.class);
        } else if (bmi >= 30 && bmi <= 34.9) {
            return new Intent(context, Obese.class);
        } else {
            return new Intent(context, ExtremelyObese.class);
        }
    }
}

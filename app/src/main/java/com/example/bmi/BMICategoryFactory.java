package com.example.bmi;

import android.content.Intent;

public class BMICategoryFactory {
    public static Intent createCategoryActivity(Context context, double bmi) {
        BMICategory category = createBMICategory(bmi);
        return new Intent(context, category.getActivityClass());
    }
    
    public static BMICategory createBMICategory(double bmi) {
        if (bmi < 18.5) {
            return new UnderweightCategory(bmi);
        } else if (bmi < 25) {
            return new NormalCategory(bmi);
        } else if (bmi < 30) {
            return new OverweightCategory(bmi);
        } else if (bmi < 35) {
            return new ObeseCategory(bmi);
        } else {
            return new ExtremelyObeseCategory(bmi);
        }
    }
}

interface BMICategory {
    String getCategoryName();
    String getAdvice();
    Class<?> getActivityClass();
}

class UnderweightCategory implements BMICategory {
    private double bmi;
    
    public UnderweightCategory(double bmi) {
        this.bmi = bmi;
    }
    
    @Override
    public String getCategoryName() {
        return "Underweight";
    }
    
    @Override
    public String getAdvice() {
        return "Consider gaining healthy weight";
    }
    
    @Override
    public Class<?> getActivityClass() {
        return UnderWeight.class;
    }
}

class NormalCategory implements BMICategory {
    private double bmi;
    
    public NormalCategory(double bmi) {
        this.bmi = bmi;
    }
    
    @Override
    public String getCategoryName() {
        return "Normal Weight";
    }
    
    @Override
    public String getAdvice() {
        return "Maintain your healthy lifestyle";
    }
    
    @Override
    public Class<?> getActivityClass() {
        return NormalWright.class;
    }
}

class OverweightCategory implements BMICategory {
    private double bmi;
    
    public OverweightCategory(double bmi) {
        this.bmi = bmi;
    }
    
    @Override
    public String getCategoryName() {
        return "Overweight";
    }
    
    @Override
    public String getAdvice() {
        return "Consider diet and exercise";
    }
    
    @Override
    public Class<?> getActivityClass() {
        return OverWeight.class;
    }
}

class ObeseCategory implements BMICategory {
    private double bmi;
    
    public ObeseCategory(double bmi) {
        this.bmi = bmi;
    }
    
    @Override
    public String getCategoryName() {
        return "Obese";
    }
    
    @Override
    public String getAdvice() {
        return "Consult with healthcare provider";
    }
    
    @Override
    public Class<?> getActivityClass() {
        return Obese.class;
    }
}

class ExtremelyObeseCategory implements BMICategory {
    private double bmi;
    
    public ExtremelyObeseCategory(double bmi) {
        this.bmi = bmi;
    }
    
    @Override
    public String getCategoryName() {
        return "Extremely Obese";
    }
    
    @Override
    public String getAdvice() {
        return "Immediate medical consultation needed";
    }
    
    @Override
    public Class<?> getActivityClass() {
        return ExtremelyObese.class;
    }
}

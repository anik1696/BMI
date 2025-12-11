package com.example.bmi;

public interface BMIAdvice {
    String getTitle();
    String getDescription();
    Class<?> getActivityClass();
}

class UnderweightAdvice implements BMIAdvice {
    public String getTitle() { return "Underweight"; }
    public String getDescription() { return "Gain weight with healthy foods"; }
    public Class<?> getActivityClass() { return UnderWeight.class; }
}

class NormalWeightAdvice implements BMIAdvice {
    public String getTitle() { return "Normal"; }
    public String getDescription() { return "Maintain healthy lifestyle"; }
    public Class<?> getActivityClass() { return NormalWright.class; }
}

class OverweightAdvice implements BMIAdvice {
    public String getTitle() { return "Overweight"; }
    public String getDescription() { return "Reduce calorie intake"; }
    public Class<?> getActivityClass() { return OverWeight.class; }
}

class ObeseAdvice implements BMIAdvice {
    public String getTitle() { return "Obese"; }
    public String getDescription() { return "Consult healthcare provider"; }
    public Class<?> getActivityClass() { return Obese.class; }
}

class ExtremelyObeseAdvice implements BMIAdvice {
    public String getTitle() { return "Extremely Obese"; }
    public String getDescription() { return "Urgent medical consultation"; }
    public Class<?> getActivityClass() { return ExtremelyObese.class; }
}

public class FactoryBMIAdvice {
    public static BMIAdvice createAdvice(float bmi) {
        if (bmi < 18.5) return new UnderweightAdvice();
        if (bmi <= 24.9) return new NormalWeightAdvice();
        if (bmi <= 29.9) return new OverweightAdvice();
        if (bmi <= 34.9) return new ObeseAdvice();
        return new ExtremelyObeseAdvice();
    }
}

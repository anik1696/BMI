public class BmiEvaluator {

public static String evaluate(float bmi) {
    if (bmi < 18.5) {
        return "You are underweight";
    } else if (bmi >= 18.5 && bmi <= 24.9) {
        return "You are normal";
    } else if (bmi >= 25 && bmi <= 29.9) {
        return "You are overweight";
    } else if (bmi >= 30 && bmi <= 34.9) {
        return "You are obese";
    } else {
        return "You are extremely obese";
    }
}

}

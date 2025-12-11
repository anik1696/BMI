package com.example.bmi;

public interface HeightAdapter {
    float convertToMeters();
}

public class AdapterHeightUnits {
    public static class FeetAdapter implements HeightAdapter {
        private float feet, inches;
        public FeetAdapter(float f, float i) { feet = f; inches = i; }
        public float convertToMeters() {
            return (feet * 0.3048f) + (inches * 0.0254f);
        }
    }
    
    public static class CentimeterAdapter implements HeightAdapter {
        private float centimeters;
        public CentimeterAdapter(float cm) { this.centimeters = cm; }
        public float convertToMeters() { return centimeters / 100f; }
    }
    
    public static HeightAdapter createFeetAdapter(float f, float i) {
        return new FeetAdapter(f, i);
    }
    
    public static HeightAdapter createCmAdapter(float cm) {
        return new CentimeterAdapter(cm);
    }
}

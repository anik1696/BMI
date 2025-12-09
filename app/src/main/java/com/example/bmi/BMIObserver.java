package com.example.bmi;

public interface BMIObserver {
    void onBMIStateChanged(float bmiValue, String category);
}

// BMISubject.java
package com.example.bmi;

import java.util.ArrayList;
import java.util.List;

public class BMISubject {
    private List<BMIObserver> observers = new ArrayList<>();
    private float currentBMI;
    private String currentCategory;
    
    public void attach(BMIObserver observer) {
        observers.add(observer);
    }
    
    public void detach(BMIObserver observer) {
        observers.remove(observer);
    }
    
    public void setBMIState(float bmi, String category) {
        this.currentBMI = bmi;
        this.currentCategory = category;
        notifyObservers();
    }
    
    private void notifyObservers() {
        for (BMIObserver observer : observers) {
            observer.onBMIStateChanged(currentBMI, currentCategory);
        }
    }
    
    public float getCurrentBMI() {
        return currentBMI;
    }
    
    public String getCurrentCategory() {
        return currentCategory;
    }
}

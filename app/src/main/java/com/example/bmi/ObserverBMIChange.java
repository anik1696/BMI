package com.example.bmi;

import java.util.ArrayList;
import java.util.List;

public class ObserverBMIChange {
    private List<DataChangeListener> listeners = new ArrayList<>();
    
    public void addListener(DataChangeListener listener) {
        listeners.add(listener);
    }
    
    public void removeListener(DataChangeListener listener) {
        listeners.remove(listener);
    }
    
    public void notifyDataChanged() {
        for (DataChangeListener listener : listeners) {
            listener.onDataChanged();
        }
    }
    
    public interface DataChangeListener {
        void onDataChanged();
    }
}

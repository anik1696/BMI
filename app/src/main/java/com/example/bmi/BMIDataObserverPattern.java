package com.example.bmi;

import java.util.ArrayList;
import java.util.List;

interface BMISubject {
    void registerObserver(BMIObserver observer);
    void removeObserver(BMIObserver observer);
    void notifyObservers(String operation);
}

class BMIDataManager implements BMISubject {
    private List<BMIObserver> observers = new ArrayList<>();
    
    public void addBMIRecord(double bmi, String category) {
        BMIDatabaseSingleton db = BMIDatabaseSingleton.getInstance(context);
        db.insertBMI(bmi, category);
        notifyObservers("ADD");
    }
    
    public void deleteAllRecords() {
        BMIDatabaseSingleton db = BMIDatabaseSingleton.getInstance(context);
        db.deleteAllData();
        notifyObservers("DELETE_ALL");
    }
    
    @Override
    public void registerObserver(BMIObserver observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(BMIObserver observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers(String operation) {
        for (BMIObserver observer : observers) {
            observer.update(operation);
        }
    }
}


interface BMIObserver {
    void update(String operation);
}

// Concrete Observer for Table Update
class BMITableObserver implements BMIObserver {
    private TableLayout tableLayout;
    private Context context;
    
    public BMITableObserver(Context context, TableLayout tableLayout) {
        this.context = context;
        this.tableLayout = tableLayout;
    }
    
    @Override
    public void update(String operation) {
        if (operation.equals("ADD") || operation.equals("DELETE_ALL")) {
            refreshTableData();
        }
    }
    
    private void refreshTableData() {
        
    }
}


class BMIStatsObserver implements BMIObserver {
    private TextView statsTextView;
    
    public BMIStatsObserver(TextView statsTextView) {
        this.statsTextView = statsTextView;
    }
    
    @Override
    public void update(String operation) {
        if (operation.equals("ADD") || operation.equals("DELETE_ALL")) {
            updateStatistics();
        }
    }
    
    private void updateStatistics() {
    }
}

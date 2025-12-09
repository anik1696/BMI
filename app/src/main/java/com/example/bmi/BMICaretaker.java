package com.example.bmi;

import java.util.ArrayList;
import java.util.List;

public class BMICaretaker {
    private List<BMIMemento> mementoList = new ArrayList<>();
    private int currentIndex = -1;
    
    public void addMemento(BMIMemento memento) {
        // Remove all mementos after current index
        if (currentIndex < mementoList.size() - 1) {
            mementoList = mementoList.subList(0, currentIndex + 1);
        }
        mementoList.add(memento);
        currentIndex = mementoList.size() - 1;
    }
    
    public BMIMemento getCurrentMemento() {
        if (currentIndex >= 0 && currentIndex < mementoList.size()) {
            return mementoList.get(currentIndex);
        }
        return null;
    }
    
    public BMIMemento undo() {
        if (currentIndex > 0) {
            currentIndex--;
            return mementoList.get(currentIndex);
        }
        return null;
    }
    
    public BMIMemento redo() {
        if (currentIndex < mementoList.size() - 1) {
            currentIndex++;
            return mementoList.get(currentIndex);
        }
        return null;
    }
    
    public boolean canUndo() {
        return currentIndex > 0;
    }
    
    public boolean canRedo() {
        return currentIndex < mementoList.size() - 1;
    }
}

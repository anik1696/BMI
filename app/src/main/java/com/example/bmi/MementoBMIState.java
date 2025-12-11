package com.example.bmi;

public class MementoBMIState {
    public static class BmiState {
        public final float weight, feet, inches;
        public BmiState(float w, float f, float i) {
            weight = w; feet = f; inches = i;
        }
    }
    
    public static class Originator {
        private BmiState state;
        public void setState(float w, float f, float i) {
            state = new BmiState(w, f, i);
        }
        public Memento saveToMemento() {
            return new Memento(state);
        }
        public void restoreFromMemento(Memento m) {
            state = m.getSavedState();
        }
        public BmiState getState() { return state; }
    }
    
    public static class Memento {
        private final BmiState state;
        public Memento(BmiState s) { state = s; }
        public BmiState getSavedState() { return state; }
    }
    
    public static class Caretaker {
        private Memento memento;
        public void save(Memento m) { memento = m; }
        public Memento getMemento() { return memento; }
    }
}

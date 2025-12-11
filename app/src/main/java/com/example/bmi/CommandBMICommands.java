package com.example.bmi;

public interface BMICommand {
    void execute();
    float getResult();
}

public class CommandBMICommands {
    public static class CalculateCommand implements BMICommand {
        private FacadeBMIOperations facade;
        private float weight, feet, inches, result;
        
        public CalculateCommand(FacadeBMIOperations f, float w, float ft, float in) {
            facade = f; weight = w; feet = ft; inches = in;
        }
        
        public void execute() {
            result = facade.calculateBMI(weight, feet, inches);
        }
        
        public float getResult() { return result; }
    }
    
    public static class SaveCommand implements BMICommand {
        private FacadeBMIOperations facade;
        private float bmi;
        private boolean saved;
        
        public SaveCommand(FacadeBMIOperations f, float b) { facade = f; bmi = b; }
        
        public void execute() {
            saved = facade.saveToDatabase(bmi);
        }
        
        public float getResult() { return saved ? 1 : 0; }
    }
    
    public static class CommandInvoker {
        private BMICommand command;
        public void setCommand(BMICommand cmd) { command = cmd; }
        public void execute() { if (command != null) command.execute(); }
        public float getResult() { return command != null ? command.getResult() : 0; }
    }
}

package com.example.bmi;

import android.content.Context;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class BMICommandPattern {
    public static void main(String[] args) {
      
        BMIDatabase db = new BMIDatabase();
        Command saveCommand = new SaveBMICommand(db, 25.6, "Overweight");
        Command deleteCommand = new DeleteAllCommand(db);
        
        BMIOperationInvoker invoker = new BMIOperationInvoker();
        invoker.takeCommand(saveCommand);
        invoker.takeCommand(deleteCommand);
        invoker.executeCommands();
    }
}

interface Command {
    void execute();
}

class BMIDatabase {
    private List<String> records = new ArrayList<>();
    
    public void saveRecord(double bmi, String category) {
        records.add("BMI: " + bmi + ", Category: " + category);
        System.out.println("Saved record: BMI=" + bmi + ", Category=" + category);
    }
    
    public void deleteAll() {
        records.clear();
        System.out.println("All records deleted");
    }
}

class SaveBMICommand implements Command {
    private BMIDatabase database;
    private double bmi;
    private String category;
    
    public SaveBMICommand(BMIDatabase database, double bmi, String category) {
        this.database = database;
        this.bmi = bmi;
        this.category = category;
    }
    
    @Override
    public void execute() {
        database.saveRecord(bmi, category);
    }
}

class DeleteAllCommand implements Command {
    private BMIDatabase database;
    
    public DeleteAllCommand(BMIDatabase database) {
        this.database = database;
    }
    
    @Override
    public void execute() {
        database.deleteAll();
    }
}

class BMIOperationInvoker {
    private List<Command> commands = new ArrayList<>();
    
    public void takeCommand(Command command) {
        commands.add(command);
    }
    
    public void executeCommands() {
        for (Command command : commands) {
            command.execute();
        }
        commands.clear();
    }
}

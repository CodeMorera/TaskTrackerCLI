package com.tasktracker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // String[] statuses = {"", "todo", "in-progress", "done"};
        int nextId =0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("*************************************");
        System.out.println("               Task List             ");
        System.out.println("*************************************");
    
        boolean choices = true;

        while(choices){
            System.out.println("Choose an option:");
            System.out.println("A. Add Task");
            System.out.println("B. Update");
            System.out.println("C. Delete");
            System.out.println("D. List Tasks");
            System.out.println("E. Exit");
            String option = scanner.next();
            Thread.sleep(500);

            switch(option.toUpperCase()){
                case "A":
                    System.out.println("You chose Add Task.");
                    newTask(scanner);
                    break;
                case "B":
                    System.out.println("You chose Update Task.");
                    break;
                case "C":
                    System.out.println("You chose Delete Task.");
                    break;
                case "D":
                    System.out.println("You chose List Task.");
                    break;
                case "E":
                    System.out.println("Good Bye.");
                    choices = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
        System.out.println("*************************************");
        scanner.close();
    }

    public static void newTask(Scanner scanner) throws InterruptedException {
        // consume leftover newline from previous next() call
        Map<String, Object> jsonMap = new HashMap<>();
        scanner.nextLine();
        System.out.println("Add description: ");
        String desc = scanner.nextLine();
        Thread.sleep(500);
        String description = String.format("Task %s added successfully!", desc);
        System.out.println(description);

        boolean progress = true;
        while (progress) {
            String[] statuses = {"", "To Do", "In Progress", "Done"};
            Thread.sleep(500);
            System.out.println("Add status: ");
            System.out.println("1 for 'To Do'");
            System.out.println("2 for 'In Progress'");
            System.out.println("3 for 'Done'");
            int stat = scanner.nextInt();
            if (stat == 1 || stat == 2 || stat == 3) {
                progress = false;
                String status = statuses[stat];
                Thread.sleep(500);
                String successStatus = String.format("Status is: %s!", status);
                System.out.println(successStatus);
                
            } else {
                System.out.println("That is not a valid input. Please press 1, 2, or 3");
            }
            
        }
    }
    public static void jsonWriter(int id, String description, String status) {
    
        Task task = new Task(1,description, status);
    }  
}
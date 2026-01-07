import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        String filename = "task.json";
        createJsonFileIfNotExists(filename);
        if(args.length > 0){
            switch (args[0]) {
                case "add":
                    System.out.println("Task added successfully ");
                    break;
                case "update":
                    System.out.println("Typed update");
                    break;
                case "delete":
                    System.out.println("Typed delete");
                    break;
                case "mark-in-progress":
                    System.out.println("Typed Add");
                    break;
                case "mark-done":
                    System.out.println("Typed Add");
                    break;
                case "list":
                    System.out.println("Typed Add");
                    break;
            
                default:
                    break;
            }
        }
    }
    public static void createJsonFileIfNotExists(String filePath) {
        try{
            File file = new File(filePath);

            if(file.createNewFile()) {
                System.out.println("File create: " + file.getName());

                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("{}");
                    writer.flush();
                }
            } else {
                System.out.println("File already exists.");
            }
        }catch (IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }
}
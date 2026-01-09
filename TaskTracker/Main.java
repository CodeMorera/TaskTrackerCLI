import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
                    String JString = jsonString(args[1]);
                    insertToJsonFile(filename, JString, null);      
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
                    writer.write("[]");
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
    public static String jsonString(String description){
        String jsonTemplate = "{\n" +
                                         "  \"id\": \"id_filler\",\n" +
                                         "  \"description\": \"%s\",\n" +
                                         "  \"status\": \"status_filler\",\n" +
                                         "  \"createdAt\": \"CreatedAt\",\n" +
                                         "  \"updatedAt\": \"updatedAt\",\n}";
        String jsonDescString = String.format(jsonTemplate, description);
        return jsonDescString;
        // try (FileWriter fileWriter = new FileWriter("task.json")) {
        //     fileWriter.write(jsonDescString);
        //     System.out.println("Successfully wrote JSON data to " + "task.json");
        // } catch (IOException e) {
        //     System.err.println("An error occurred while writing to the file: " + e.getMessage());
        //     e.printStackTrace();
        // }
    }
    public static void insertToJsonFile(String filePath, String jsonString, String arrayKey) {
        StringBuilder fileContent = new StringBuilder();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            while ((line = reader.readLine()) != null){
                fileContent.append(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int insertIndex = -1;
        if (arrayKey == null) {
            insertIndex = fileContent.lastIndexOf("]");
        }else{
            String searchString = "\"" + arrayKey + "\": [";
            int keyIndex = fileContent.indexOf(searchString);
            if(keyIndex != -1) {
                insertIndex = fileContent.indexOf("]", keyIndex);
            }
        }

        if (insertIndex == -1) {
            System.out.println("Could not find the json array");
            return;
        }

        if (fileContent.charAt(insertIndex - 1) != '[') {
            jsonString = "," + jsonString;
        }

        fileContent.insert(insertIndex, jsonString);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(fileContent.toString());
            System.out.println("New Json String Inserted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
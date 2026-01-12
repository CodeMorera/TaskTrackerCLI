import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        String filename = "task.json";
        createJsonFileIfNotExists(filename);
        if(args.length > 0){
            switch (args[0]) {
                case "add":
                    String[] subArray = Arrays.copyOfRange(args,1,args.length);
                    String JArgs = String.join(" ", subArray);

                    Calendar cal = Calendar.getInstance();
                    String calString = String.format("%1$tm/%1$td/%1$tY", cal);
                    // String calString = calFormat.toString();


                    String JString = jsonString(JArgs, calString);
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
    public static String jsonString(String description, String cal){
        String jsonTemplate = "{\n" +
                                         "  \"id\": \"id_filler\",\n" +
                                         "  \"description\": \"%s\",\n" +
                                         "  \"status\": \"status_filler\",\n" +
                                         "  \"createdAt\": \"%s\",\n" +
                                         "  \"updatedAt\": \"%s\"\n},";
        String jsonDescString = String.format(jsonTemplate, description, cal, cal);
        return jsonDescString;
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
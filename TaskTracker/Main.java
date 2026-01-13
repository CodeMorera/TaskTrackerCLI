import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String filename = "task.json";
        createJsonFileIfNotExists(filename);
        Task.setNextId(getNextIdFromJson(filename));
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "add":
                    String[] subArray = Arrays.copyOfRange(args, 1, args.length);
                    String JArgs = String.join(" ", subArray);

                    // Calendar cal = Calendar.getInstance();
                    // String calString = String.format("%1$tm/%1$td/%1$tY", cal);
                    // String calString = calFormat.toString();

                    String JString = jsonString(JArgs);
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
        try {
            File file = new File(filePath);

            if (file.createNewFile()) {
                System.out.println("File create: " + file.getName());

                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[]");
                    writer.flush();
                }
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    public static String jsonString(String description) {
        Task task = new Task(description);
        int eyedee = task.getId();
        String desc = task.getDescription().toString();
        Date date = Date.from(task.getCreatedAt());
        String formattedDate = String.format("%tY-%tm-%td %tH:%tM:%tS", date, date, date, date, date, date);
        String jsonTemplate = "{\n" +
                "  \"id\": \"%d\",\n" +
                "  \"description\": \"%s\",\n" +
                "  \"status\": \"status_filler\",\n" +
                "  \"createdAt\": \"%s\",\n" +
                "  \"updatedAt\": \"%s\"\n}";
        String jsonDescString = String.format(jsonTemplate, eyedee, desc, formattedDate, formattedDate);
        return jsonDescString;
    }

    public static void insertToJsonFile(String filePath, String jsonString, String arrayKey) {
        StringBuilder fileContent = new StringBuilder();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String text = fileContent.toString().trim();
        if (text.isEmpty()) {
            text = "[]";
            fileContent = new StringBuilder(text);
        }
        ;

        int insertIndex = -1;
        if (arrayKey == null) {
            insertIndex = fileContent.lastIndexOf("]");
        } else {
            String searchString = "\"" + arrayKey + "\": [";
            int keyIndex = fileContent.indexOf(searchString);
            if (keyIndex != -1) {
                insertIndex = fileContent.indexOf("]", keyIndex);
            }
        }

        if (insertIndex == -1) {
            System.out.println("Could not find the json array");
            return;
        }

        boolean isEmptyArray = fileContent.indexOf("[") != -1
                && fileContent.indexOf("]") == fileContent.indexOf("[") + 1;

        String obj = jsonString;

        String insertion;
        if (isEmptyArray) {
            insertion = System.lineSeparator() + obj + System.lineSeparator();
        } else {
            insertion = "," + System.lineSeparator() + obj + System.lineSeparator();
        }

        fileContent.insert(insertIndex, insertion);

        if (fileContent.charAt(0) == '[' && fileContent.charAt(1) != '\n') {
            fileContent.insert(1, "\n");
        }

        int lastBracket = fileContent.lastIndexOf("]");
        if (lastBracket > 0 && fileContent.charAt(lastBracket - 1) != '\n') {
            fileContent.insert(lastBracket, "\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(fileContent.toString());
            System.out.println("New Json String Inserted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getNextIdFromJson(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }

        Pattern pattern = Pattern.compile("\"id\"\\s*:\\s*\"(\\d+)\"");
        Matcher matcher = pattern.matcher(content.toString());

        int maxId = 0;

        while (matcher.find()) {
            int id = Integer.parseInt(matcher.group(1));
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId + 1;
    }
}
package fluffycontrol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * date: 2026-02-25
 * @author Sovren
 */
public class Tasks {
    
    private static final String FILE_NAME = "TaskData.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Random rand = new Random();
    private final Path savePath;

    public Tasks() {
        savePath = Paths.get(System.getProperty("user.dir"), FILE_NAME);
    }
    
     // <editor-fold desc="Save And Load">
    public UserData loadData() {
        if (!Files.exists(savePath)) {
            System.out.println("Save file missing. Creating new one...");
            UserData base = createBaseData();
            saveData(base);
            return base;
        }

        try (FileReader reader = new FileReader(savePath.toFile())) {
            UserData data = gson.fromJson(reader, UserData.class);

            if (data.getTasks() == null || data.getTasks().isEmpty()) {
                data.setTasks(createBaseData().getTasks());
                saveData(data);
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return createBaseData();
    }

    public void saveData(UserData data) {
        try (FileWriter writer = new FileWriter(savePath.toFile())) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     // </editor-fold>
    
    public String randomTask(UserData data, boolean isCLI) {
        if (data.getTasks().isEmpty()) {
            System.out.println("No tasks available.");
            return null;
        }        
        String task = data.getTasks().get(rand.nextInt(data.getTasks().size()));
        
        if(isCLI){System.out.printf("%nDo this Task: " + task); return null;
        }else{return task;}
    }

    private UserData createBaseData() {
        UserData data = new UserData();
        
        data.getTasks().addAll(Arrays.asList(
                "Daily Walk (15+ min)",
                "Morning Routine",
                "Clean Small Area",
                "Language Study Session (15 min)",
                "Stretching Session (10 min)",
                "Read a few pages in a book.",
                "Weekly Reflection (short)",
                "Budget Review",
                "Plan Tomorrow",
                "Test New Clothing Style",
                "Gym Session",
                "VR Social Anxiety Training",
                "Cook a Proper Meal",
                "Math 2b Study Session",
                "Portfolio Improvement Step",
                "Java Project Progress",
                "Programming Practice (30 min)",
                "Laundry & Clothing Care",
                "Painting / Drawing Session",
                "Online Presence Update",
                "Fix 3 Bugs",
                "Implement Small Feature",
                "Sewing / Cosplay Practice",
                "Long Dog Walk (45+ min)",
                "Deep Room Cleaning",
                "Full Room Redesign Step",
                "2+ Hour Deep Work Session",
                "Advanced Software Dev Study (1+ hr)",
                "Major Game Feature Implementation",
                "Build Game System From Scratch",
                "Nature / Outdoor Day Activity",
                "Release Playable Build",
                "Resist Impulse (log instead of acting)",
                "Social Event Initiated & Attended"
        ));
        return data;
    }

    public static void taskComplete(String difficulty, boolean milestone, UserData data){        
        switch(difficulty){
            case "light" -> {
                data.setCredits(data.getCredits() + 10);
                if (milestone){
                    data.setCredits(data.getCredits() + 490);
                    data.setTokens(data.getTokens() + 1);
                }
            }
            case "normal" -> {
                data.setCredits(data.getCredits() + 30);
                if (milestone){
                    data.setCredits(data.getCredits() + 470);
                    data.setTokens(data.getTokens() + 2);
                }
            }
            case "heavy" -> {
                data.setCredits(data.getCredits() + 70);
                if (milestone){
                    data.setCredits(data.getCredits() + 430);
                    data.setTokens(data.getTokens() + 3);
                }
            }
            default -> {System.out.printf("%nInvalid difficulty for task");}
        }
        
    }
    // <editor-fold desc="Deprecated CLI function">
    /* 
    // CLI Reward handler
    public void chooseRewardCLI(Scanner input, UserData data, int num){
        boolean loop = true;
        while(loop){
            System.out.printf("%nWhich reward would you like to redeem?%nInput: ");
            while(!input.hasNextInt()){
                System.out.printf("Your input was %s. Please enter a number: ",input.next());
            }
            num = input.nextInt();
            
            //String choice = input.nextLine();
            switch(num){
                // Tier 1    /// You could add these into the Json and then load them here
                case 1 -> {confirmCLI(input, "New Clothing Item", 600, 1, data); loop = false;}
                case 2 -> {confirmCLI(input, "Small Hobby Purchase", 300, 1, data); loop = false;}
                case 3 -> {confirmCLI(input, "New Game", 400, 1, data); loop = false;}
                case 4 -> {confirmCLI(input, "Tech Accessory", 600, 1, data); loop = false;}
                // Tier 2
                case 5 -> {confirmCLI(input, "Day Trip", 900, 2, data); loop = false;}
                case 6 -> {confirmCLI(input, "Setup Upgrade", 1200, 2, data); loop = false;}              
                // Tier 3
                case 7 -> {confirmCLI(input, "Major Purchase", 4000, 5, data); loop = false;}
                case 8 -> {confirmCLI(input, "Weekend Trip", 2500, 3, data); loop = false;}
                case 9 -> {confirmCLI(input, "Large Hobby Investment", 3000, 4, data); loop = false;}
                default -> {System.out.printf("Invalid input please enter the number for the task");}
            }
        }        
        
    }
    
    // Confirms reward purchase
    public void confirmCLI(Scanner input, String text, int amount, int token, UserData data){
        String answer, choice;
        do {
            System.out.printf("%nAre you sure you want to purchase %s for %d credits or %d tokens? (Y/N): ", text, amount, token);
            answer = input.next().trim().toUpperCase();
        } while (!answer.matches("[YN]"));
        boolean yesAnswer = answer.equalsIgnoreCase("Y");        
        if(yesAnswer){
        do{
            System.out.printf("%nWhich which currecny? (C/T): ");
            choice = input.next().trim().toUpperCase();
        }while(!choice.matches("[CT]"));
        if(choice.equalsIgnoreCase("C")){data.setCredits(data.getCredits() - amount);} else {
            data.setTokens(data.getTokens() - token);
            }
        }
        saveData(data);
    }
    */
    // </editor-fold>
}

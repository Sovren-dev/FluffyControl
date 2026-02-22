package fluffycontrol;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * date: 2026-01-17
 * @author Sovren
 */
public class FluffyControl {
    public static String username = System. getProperty("user.name");   
    public static String version = "0.1.0";
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MainFrame mainFrame = new MainFrame();
        boolean running = true;
        String timeUntilShutdown = "30";
        
        onLaunch(timeUntilShutdown);
        /* // Unused
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {                        
            try {
                System.out.println("No bypassing by exiting the program!");       
                new ProcessBuilder("rundll32", "user32.dll,LockWorkStation").start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        */
        
        // Fallback CLI Mode  // Maybe move this feature to a diffrent class to declutter main?        
        while(running){
            System.out.printf("%nWhat would you like to access?");
            System.out.printf("%nInput: ");
            String profile = input.nextLine().toLowerCase();
            switch(profile){
                case "ins" -> {openWebBrowserTab("https://www.youtube.com/watch?v=nBdcQGPLejk");}                
                case "test" -> {profiles(profile);}
                case "programming" -> {profiles(profile);}
                case "shutdown" -> {timeUntilShutdown = "30"; shutdown(timeUntilShutdown);}                
                case "quit" -> {running = false;}
                case "help" -> {System.out.printf("Commands: shutdown, math, programming, quit, ins");}
                default -> {System.out.println("Invalid input, type help for commands");}
            }
        }
        
        openHtmlFromJar("/html/ExitReason.html","ExitReason");
        try {
        Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.exit(0);
    }
    
    
    private static void onLaunch(String timeUntilShutdown) {        
        System.out.printf("Welcome to Fluffy Control version %s, %s!%n",version, username);        
        timerForShutdown(true, timeUntilShutdown);
    }
    
    // <editor-fold desc="Shutdown Feature">
    
    // Timer currently only used for shutdown
    private static void timerForShutdown(boolean sh, String timeUntilShutdown){
        warnForShutdown();
        LocalTime endTime = LocalTime.of(23,50); // Create a date object (23,59)
        Timer timer = new Timer(10000, e -> {
            LocalTime now = LocalTime.now();
            System.out.printf("%nDebug now: %tT", now);
             if(!now.isBefore(endTime)) {
                System.out.printf("%nDebug: Time reached stopping timer.");
                if(sh)shutdown(timeUntilShutdown);
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }
    
    // Warns user before shutdown
    private static void warnForShutdown(){
        //  Create the date objects
        LocalTime oneHour = LocalTime.of(22,50); // Default: (22,59)
        LocalTime thirtyMin = LocalTime.of(23,20); // Default: (23,29)
        LocalTime fiveMin = LocalTime.of(23,45); // Default: (23,54)
        
        boolean[] fired = {false, false, false}; // Checks if event has been fired
        
        Timer timer = new Timer(5000, e -> {
            LocalTime now = LocalTime.now();
            
            if(!fired[0] && !now.isBefore(oneHour)) {
                fired[0] = true;
                System.out.printf("%n1 Hour left");                
                //JOptionPane.showMessageDialog(null, "1 Hour Left until automatic shutdown");    
                //openWebBrowserTab("file:///C:/html/FluffyProject/OneHour.html");
                openHtmlFromJar("/html/OneHour.html", "OneHour");
            }
            
            if(!fired[1] && !now.isBefore(thirtyMin)) {
                fired[1] = true;
                System.out.printf("%n30 minutes left");
                //JOptionPane.showMessageDialog(null, "30 minutes Left until automatic shutdown");
                //openWebBrowserTab("file:///C:/html/FluffyProject/ThirtyMin.html");
                openHtmlFromJar("/html/ThirtyMin.html", "ThirtyMin");                
            }
            
            if(!fired[2] && !now.isBefore(fiveMin)) {
                fired[2] = true;
                System.out.printf("%n5 minutes left");                
                //JOptionPane.showMessageDialog(null, "5 minutes Left until automatic shutdown");
                //openWebBrowserTab("file:///C:/html/FluffyProject/FiveMin.html");
                openHtmlFromJar("/html/FiveMin.html", "FiveMin");
                openWebBrowserTab("https://www.youtube.com/watch?v=E5DFG2xwT00");                
            }
        });
        
        timer.start();
    }  
    
    //shutdown computer
    public static void shutdown(String timeUntilShutdown){
        try {
            new ProcessBuilder("shutdown", "/s", "/t", timeUntilShutdown).start();   
            int shutdownTimer;
            try {
               shutdownTimer = Integer.parseInt(timeUntilShutdown);
            }
            catch (NumberFormatException e) {
               shutdownTimer = 0;
            }
            for(int i=shutdownTimer-5; i>0; i--){
                try {                                    
                    System.out.printf("%nShutdown in %d%n",i+5);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    System.getLogger(FluffyControl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // </editor-fold>
    
    // <editor-fold desc="Utility Launchers">
    
    // Launches an exe file within their local directory
    public static void launchEXE(String exeUrl, String localFilesUrl){
        try{
            // Define path of the .exe
            ProcessBuilder pb = new ProcessBuilder(exeUrl);            
            // Setting working directory
            pb.directory(new File(localFilesUrl));            
            // Start
            Process process = pb.start();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }    
    
    // Launches the web browser with the specified URL.
    private static void openWebBrowserTab(String url){
        try{
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException ex) {
            System.getLogger(FluffyControl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (URISyntaxException ex) {
            System.getLogger(FluffyControl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } 
    }
    
    // Launches html file with the specified resource path
    private static void openHtmlFromJar(String resourcePath, String fileName) {
        try (InputStream in = FluffyControl.class.getResourceAsStream(resourcePath)){
            if(in == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }
            File tempFile = File.createTempFile(fileName,".html");
            tempFile.deleteOnExit();
            
            Files.copy(in, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            
            Desktop.getDesktop().browse(tempFile.toURI());
        } catch (IOException ex) {
            System.getLogger(FluffyControl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }  
    }    
    // </editor-fold>
    
    // <editor-fold desc="Profiles Feature">
    // Holds all urls for each profile and launches them
    public static void profiles(String profile ){        
        switch(profile){
            case "test" -> {
            openHtmlFromJar("/html/FiveMin.html", "FiveMin");
            }
            case "ironlung" -> {openWebBrowserTab("https://www.youtube.com/watch?v=tnQsohEFgjQ&list=PLxA687tYuMWgiK_LKbEKna0q0U7a_d_W9&index=1");}
            case "vyletpony" -> {
            openWebBrowserTab("https://www.youtube.com/@VyletPony");
            openWebBrowserTab("https://www.youtube.com/watch?v=wpXwEcHhlpQ&list=PLXO_EoOinL6HVHnGwiQHbhOGV9lQ3aiMm");
            }
            case "japanese" -> {
                openWebBrowserTab("https://gohoneko.neocities.org/grammar/taekim#0%20Before%20you%20begin");                      
                openWebBrowserTab("https://gohoneko.neocities.org/learn/kana");
                openWebBrowserTab("https://jisho.org/");
                openWebBrowserTab("https://guidetojapanese.org/learn/");
                openWebBrowserTab("https://learnjapanese.moe/");
                openWebBrowserTab("https://www.youtube.com/watch?v=LbXNpmNvU0U");
                openWebBrowserTab("https://www.youtube.com/watch?v=nuI4OgsJv_Q");                
            }
            case "programming" -> {
                try{
                     // Define path of the .exe
                    String exeUrl = "C:\\Program Files\\Apache NetBeans\\bin\\netbeans64.exe";
                    String localFilesUrl = "C:\\Program Files\\Apache NetBeans\\bin";
                    launchEXE(exeUrl, localFilesUrl);
                    TimeUnit.MILLISECONDS.sleep(500);
                    openWebBrowserTab("https://www.youtube.com/watch?v=ikz_YcsmuUE");
                    TimeUnit.MILLISECONDS.sleep(500);
                    openWebBrowserTab("https://docs.oracle.com/en/java/javase/25/index.html");
                    TimeUnit.MILLISECONDS.sleep(500);
                    openWebBrowserTab("https://www.reddit.com/r/learnprogramming/wiki/faq/#wiki_getting_started");
                    TimeUnit.MILLISECONDS.sleep(500);
                    openWebBrowserTab("https://github.com/codecrafters-io/build-your-own-x");
                    TimeUnit.MILLISECONDS.sleep(500);
                    openWebBrowserTab("https://github.com/practical-tutorials/project-based-learning");
                } catch (InterruptedException ex) {
                    System.getLogger(FluffyControl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
            case "mangamusic" -> {
                openWebBrowserTab("https://www.youtube.com/watch?v=kVRx9z9E8oo&list=PLSXCAhBaQ1kIvnQf6T2RxOhudtttHfY6E");
                openWebBrowserTab("https://www.youtube.com/watch?v=qzKg0FeNRZ8");
                openWebBrowserTab("https://www.youtube.com/watch?v=ViuCOfVy_4Y&t=81s");
                openWebBrowserTab("https://www.youtube.com/watch?v=sIquBR37fFk&t=2792s");              
                openWebBrowserTab("https://www.youtube.com/watch?v=hi87-ewkY-w");
                openWebBrowserTab("https://www.youtube.com/watch?v=IKcO1hkW93g");
                openWebBrowserTab("https://www.youtube.com/watch?v=yJmHjMbloCI");
                openWebBrowserTab("https://www.youtube.com/watch?v=WFkafjg15eg");
            }
            default -> {System.out.printf("%nError invalid profile.");}
        }
    }
}

package fluffycontrol;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.util.Scanner;
import javax.swing.*;

/**
 * date: 2026-01-17
 * @author Sovren
 */
public class FluffyControl {
    public static String username = System.getProperty("user.name");   
    public static String version = "0.9.0-Linux";
    public static boolean running = true;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //MainFrame mainFrame = new MainFrame();
        SwingUtilities.invokeLater(MainFrame::new);

        onLaunch();
        
        // Fallback CLI Mode
        while(running){
            System.out.printf("%nWhat would you like to access?");
            System.out.printf("%nInput: ");
            String profile = input.nextLine().toLowerCase();
            switch(profile){
                case "ins" -> openWebBrowserTab("https://www.youtube.com/watch?v=nBdcQGPLejk");
                case "quit" -> running = false;
                case "help" -> System.out.print("Commands: ins, quit");
                case "reset-to-day" -> {
                    RestrictionManager.fired[0] = false;
                    RestrictionManager.fired[1] = true;
                }
                default -> System.out.println("Invalid input, type help for commands");
            }
        }

        try {
        Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.exit(0);
    }

    private static void onLaunch() {        
        System.out.printf("Welcome to Fluffy Control version %s, %s!%n",version, username);        
        RestrictionManager.restrictionSystem();
    }

    // <editor-fold desc="Utility Launchers">
    
    // Launches the web browser with the specified URL.
    public static void openWebBrowserTab(String url){
        try{
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            System.getLogger(FluffyControl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }    
    // </editor-fold>
}

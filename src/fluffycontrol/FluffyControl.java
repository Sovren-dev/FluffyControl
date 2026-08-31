package fluffycontrol;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

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
        MainFrame mainFrame = new MainFrame();
        
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
        warnSystem();
    }
    
    // <editor-fold desc="Shutdown Feature">
    
    // Warns user before shutdown
    private static void warnSystem(){
        boolean[] fired = {false, false}; // Checks if event has been fired

        String networkInterFace = "enp14s0";

        Timer timer = new Timer(30000, e -> {
            LocalTime now = LocalTime.now();
            boolean isDayTime = isBetween(now, LocalTime.of(8, 0), LocalTime.of(23,30));
            // Nighttime. After 23:30 and before 8:00
            if (!isDayTime && !fired[0]) {
                System.out.println("Get to bed");
                warningWindow("Sleep time","You can do whatever you need to do tomorrow.","#D0342C");
                // Turn off Ethernet
                NetworkController.turnOffNetwork();
                fired[0] = true; // Lock night event
                fired[1] = false; // Unlock day event
            }

            else if (isDayTime && !fired[1]) {
                System.out.printf("%nGood morning, %s! ", username);
                // turn on Ethernet
                NetworkController.turnOnNetwork();
                fired[1] = true; // Lock day event
                fired[0] = false; // Unlock night event
            }
        });
        timer.start();
    }

    /**
     * Display a full-screen reminder window.
     * 
     * <p>The window is always on top, can be closed by standard window controls. 
     * Title, message, and background color are supplied by the caller</p>
     * @param title The heading displayed at the top of the reminder
     * @param body The main reminder text displayed bellow the title
     * @param color Background color in hex format
     */
    public static void warningWindow(String title, String body, String color){
        JFrame frame = new JFrame();
        
        //frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel label = new JLabel(
                "<html><center><h1>" + title + "</h1><br>" + body + "</center></html>",
                SwingConstants.CENTER
        );
        
        label.setFont(new Font("Verdana", Font.BOLD, 40));
        label.setBackground(Color.decode(color));
        
        label.setOpaque(true);
        
        frame.add(label);
        frame.setVisible(true);        
    }

    // TimerCheck Helper
    private static boolean isBetween(LocalTime now, LocalTime start, LocalTime end) {        
        return !now.isBefore(start) && !now.isAfter(end);
    }
    // </editor-fold>
    
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

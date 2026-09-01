package fluffycontrol;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

import static fluffycontrol.FluffyControl.username;

public class RestrictionManager {
    public static boolean[] fired = {false, false}; // Checks if event has been fired
    public static void restrictionSystem(){

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

    // TimerCheck Helper
    private static boolean isBetween(LocalTime now, LocalTime start, LocalTime end) {
        return !now.isBefore(start) && !now.isAfter(end);
    }

    public static void warningWindow(String title, String body, String color){
        JFrame frame = new JFrame();

        //frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setSize(850,600);
        frame.setLocationRelativeTo(null);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
}

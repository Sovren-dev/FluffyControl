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
                SwingUtilities.invokeLater(warnWindow::new);
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
}

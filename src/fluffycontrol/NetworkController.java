package fluffycontrol;

import java.io.IOException;
public class NetworkController {
    private static final String INTERFACE_NAME = "enp14s0"; // or "eth0", "wlan0", etc.

    public static void turnOffNetwork() {
        try {
            // Kopplar bort det specifika nätverkskortet
            ProcessBuilder pb = new ProcessBuilder("nmcli", "device", "disconnect", INTERFACE_NAME);
            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Network disabled");
            } else {
                System.err.println("Network could not be disabled. Exit kod: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void turnOnNetwork() {
        try {
            // Startar nätverkskortet igen
            ProcessBuilder pb = new ProcessBuilder("nmcli", "device", "connect", INTERFACE_NAME);
            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Network enabled.");
            } else {
                System.err.println("Network could not start. Exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
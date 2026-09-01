package fluffycontrol;

import java.io.IOException;
public class NetworkController {
    private static final String INTERFACE_NAME = "enp14s0"; // or "eth0", "wlan0", etc.

    // Fix this mess when you wake up :/

    /*
    public static boolean turnOffNetwork() {
        return runCommand("nmcli", "device", "disconnect", INTERFACE_NAME);
    }

    public static boolean turnOnNetwork() {
        return runCommand("nmcli", "device", "connect", INTERFACE_NAME);
    }


    public static boolean runCommand(String... command){
        try {
            Process process = new ProcessBuilder(command).redirectErrorStream(true).start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Command successful.");
                return true;
            }

            System.err.println("Command failed. Exit code: " + exitCode);

            return false;

        } catch (IOException e) {
            System.err.println("Could not execute command: " + e.getMessage());
            return false;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    */

    public static void turnOffNetwork() {
        runCommand("nmcli", "device", "disconnect", INTERFACE_NAME);
    }

    public static void turnOnNetwork() {
        runCommand("nmcli", "device", "connect", INTERFACE_NAME);
    }

    public static void runCommand(String... command){
        try {
            Process process = new ProcessBuilder(command).redirectErrorStream(true).start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Command successful.");
                return;
            }

            System.err.println("Command failed. Exit code: " + exitCode);

        } catch (IOException e) {
            System.err.println("Could not execute command: " + e.getMessage());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
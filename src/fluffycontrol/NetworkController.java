package fluffycontrol;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
public class NetworkController {
    public static String INTERFACE_NAME = "enp14s0"; // or "eth0", "wlan0", "wlp15s0", etc.

    public static void turnOffNetwork(Gson gson) {
        runCommand("nmcli", "device", "disconnect", getInterface(gson));
    }

    public static void turnOnNetwork(Gson gson) {
        runCommand("nmcli", "device", "connect", getInterface(gson));
    }

    public static void runCommand(String... command){
        try {
            Process process = new ProcessBuilder(command).redirectErrorStream(true).start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println(LogColors.BLUE + LogColors.BOLD + "Network Command successful." + LogColors.RESET);
                return;
            }

            System.err.println("Command failed. Exit code: " + exitCode);

        } catch (IOException e) {
            System.err.println("Could not execute command: " + e.getMessage());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static String getInterface(Gson gson) {
        Config loadedConfig = Config.loadFile(gson, new File(Config.configFile), false);
        return loadedConfig.getINTERFACE_NAME();
    }
}
package fluffycontrol;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

/**
 * date: 2026-09-26
 * @author Sovren
 */
public class Config {
    // Test config options may not be in final version

    private String theme;
    private boolean autoStart;
    private LocalTime bedtime;
    private LocalTime wakeup;
    private String INTERFACE_NAME; // or "eth0", "wlan0", etc. // Wi-Fi, Ethernet

    public Config(String theme, boolean autoStart, LocalTime bedtime, LocalTime wakeup, String INTERFACE_NAME) {
        this.theme = theme;
        this.autoStart = autoStart;
        this.bedtime = bedtime;
        this.wakeup = wakeup;
        this.INTERFACE_NAME = INTERFACE_NAME;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }

    public void setBedtime(LocalTime bedtime) {
        this.bedtime = bedtime;
    }

    public void setWakeup(LocalTime wakeup) {
        this.wakeup = wakeup;
    }

    public void setINTERFACE_NAME(String INTERFACE_NAME) {
        this.INTERFACE_NAME = INTERFACE_NAME;
    }

    public boolean isAutoStart() {
        return autoStart;
    }

    public LocalTime getBedtime() {
        return bedtime;
    }

    public LocalTime getWakeup() {
        return wakeup;
    }

    public String getINTERFACE_NAME() {
        return INTERFACE_NAME;
    }

    public static String getConfigFile() {
        return configFile;
    }

    @Override
    public String toString() {
        return "Theme: " + theme + " autoStart: " + autoStart + " bedtime: "
                + bedtime + " wakeup: " + wakeup + " INTERFACE_NAME: " + INTERFACE_NAME;
    }

    public static String configFile = "config.json";

    public static Config loadFile(Gson gson, File fileName) {
        Config config = null;
        try (FileReader reader = new FileReader(fileName)) {
            config = gson.fromJson(reader, Config.class);
            System.out.println(LogColors.GREEN + LogColors.BOLD + "Successfully read config file" + LogColors.RESET);
            System.out.println("DEBUG: " + config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static void writeToFile(File fileName, String jsonOutput) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonOutput);
            System.out.println(LogColors.GREEN + LogColors.BOLD + "Successfully written JSON object to " + configFile + LogColors.RESET);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config defaultConfig = new Config("default",
            false,
            LocalTime.of(23, 30),
            LocalTime.of(9, 0),
            "enp14s0"
    );

    // Check if config file exsits and load file
    public static void doesConfigExist(Gson gson, String jsonOutput) {
        File fileName = new File(Config.configFile);
        if (fileName.isFile()) {
            System.out.println(LogColors.BLUE + LogColors.BOLD + "Config file found. Loading configuration..." + LogColors.RESET);
            // Run code here:
            System.out.println("Default Config: " + Config.defaultConfig);
            //Config loadedConfig = Config.loadFile(gson, fileName); // Load file
            //jsonOutput = gson.toJson(loadedConfig);
            //Config.writeToFile(fileName, jsonOutput);

        } else {
            // Write defaults
            System.out.println(LogColors.YELLOW + LogColors.BOLD + "[WARN] Config file not found. Creating new one..." + LogColors.RESET);
            Config.writeToFile(fileName, jsonOutput);
        }
    }
}

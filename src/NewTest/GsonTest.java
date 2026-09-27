package NewTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GsonTest {
    private static String GUILinkerFile = "GUILinkerFile.json";

    static void main() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String[] words = {"Hello","Car", "Fox", "Wolf"};


        //fluffycontrol.Config.doesConfigExist(gson, jsonOutput);
        // Either make a switch case or rewrite doesConfigExist to allow for other files
        // Same with writeToFile.

        File f = new File(GUILinkerFile);
        if(f.isFile() && !f.isDirectory()) {
            // If file exists run this code:

        } else {
            try (FileWriter file = new FileWriter(GUILinkerFile)){
                words = new String[] {"Aegis Defenders",
                        "A.R.D. Alien Removal Division",
                        "ATLYSS",
                        "Hytale",
                        "Halls of Torment",
                        "Aviators",
                        "Backrooms: Escape Together",
                        "Bleed Runner",
                        "Call of Juarez Gunslinger",
                        "Celeste",
                        "Dark Sector",
                        "Darksiders",
                        "Darksiders Warmastered Edition",
                        "Deadlock",
                        "Deep Rock Galactic",
                        "FIGHT KNIGHT",
                        "Half-Life",
                        "ISLANDERS",
                        "Jagged Alliance Gold",
                        "Metro 2033",
                        "Metro: Last Light Complete Edition",
                        "Monster Hunter Stories 2: Wings of Ruin Trial Version",
                        "Neglected",
                        "Nightmare Kart",
                        "Ninja Kiwi Archive",
                        "PEAK",
                        "Plants vs. Zombies™ Garden Warfare",
                        "Spiritfarer®: Farewell Edition",
                        "Steel Assault",
                        "STEINS;GATE",
                        "Sumire",
                        "The Last Train",
                        "Titan Quest Anniversary Edition",
                        "Undertale",
                        "V Rising",
                        "Voice of Cards: The Isle Dragon Roars Demo",
                        "Warhammer 40,000: Dawn of War II",
                        "Warhammer 40,000: Gladius - Relics of War",
                        "Warhammer: Vermintide 2",
                        "でびるコネクション (Devil Connection)",
                        "Aethermancer Demo",
                        "Alien Swarm: Reactive Drop",
                        "Among Us",
                        "ASTRONEER",
                        "Avorion",
                        "Axis & Allies 1942 Online",
                        "Baldur's Gate 3",
                        "Barotrauma",
                        "Battlestar Galactica Deadlock",
                        "Blackwake",
                        "Company of Heroes 2",
                        "Conqueror's Blade",
                        "Content Warning",
                        "Counter-Strike 2",
                        "Creativerse",
                        "DEFCON",
                        "Diesel Knights Playtest",
                        "Divinity: Original Sin 2",
                        "Dune: Spice Wars",
                        "Enlisted",
                        "Garry's Mod",
                        "Generation Zero®",
                        "Golf With Your Friends",
                        "Grand Theft Auto V Enhanced",
                        "Human Fall Flat",
                        "Just Cause 3",
                        "Kingdoms and Castles",
                        "Left 4 Dead 2",
                        "Magellania",
                        "Marvel Rivals",
                        "Next Day: Survival",
                        "Nuclear Nightmare",
                        "Path of Exile",
                        "Plague Inc: Evolved",
                        "Plants vs. Zombies: Game of the Year",
                        "Portal",
                        "Portal 2",
                        "Purrgatory",
                        "R.E.P.O.",
                        "Robocraft",
                        "SAS: Zombie Assault 4",
                        "Satisfactory",
                        "SCP: Containment Breach Multiplayer",
                        "SCP: Secret Laboratory",
                        "Scrap Mechanic",
                        "Space Engineers",
                        "Spore",
                        "Squad",
                        "STAR WARS Jedi: Fallen Order™",
                        "Stick Fight: The Game",
                        "Stormworks: Build and Rescue",
                        "Subnautica",
                        "Sven Co-op",
                        "Terraria",
                        "THE FINALS",
                        "The Mean Greens - Plastic Warfare",
                        "Unturned",
                        "War Selection",
                        "World of Tanks",
                        "World of Warships",
                        "Minecraft - Survival",
                        "Minecraft - Hypixel",
                        "Legendary Tales",
                        "No Man's Sky",
                        "Diablo 2",
                        "Starcraft 2",
                        "Starcraft 1",
                        "Rain World",
                        "Outer Wilds",
                        "Iron Lung",
                        "Mohrta",
                        "Beat Saber",
                        "Underdogs",
                        "Nine Sols",
                        "Moss VR 2",
                        "Heroes of the Storm",
                        "World of Warcraft"};
                String jsonOutput = gson.toJson(words);
                System.out.println("" + jsonOutput);
                file.write(jsonOutput);

                System.out.println("Successfully written JSON object to file.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

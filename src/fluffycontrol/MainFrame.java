package fluffycontrol;

import java.awt.*;
import java.net.URL;
import java.util.Random;
import javax.swing.*;


/**
 * date: 2026-02-17
 * @author Sovren
 */
public class MainFrame extends JFrame {    
    private static final Random rand = new Random();

    public MainFrame(){
        setTitle("Fluffy Control Panel v" + FluffyControl.version);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800 , 500);
        setLocationRelativeTo(null);
        //setLayout(null);
        setResizable(false);        
        // Icon
        URL resource = MainFrame.class.getResource("/res/FluffyControlIcon.png");
        if (resource != null) {
            ImageIcon iconImage = new ImageIcon(resource);
            setIconImage(iconImage.getImage());
        } else {
            System.err.println("Could not find IconImage");
        }
        // Creates GUI
        menuJPanel panel = new menuJPanel();
        panel.setBackground(Color.decode("#1e2022"));
        add(panel);
        
        setVisible(true);
    }
    
    // Links functions to buttons
    public static String GUILinker(String type) {
        switch(type){
            case "rngGame" -> {
                // Load json String instead here
                String[] rngGame = {"Aegis Defenders",
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
                int r = rand.nextInt(rngGame.length);
                System.out.printf("%n%s | Number: %d%n", rngGame[r], r);
                return rngGame[r];
            }            
            default -> System.out.println("Invalid GUI option");
        }
        return null;
    }    
}

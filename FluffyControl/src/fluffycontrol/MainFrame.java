package fluffycontrol;

import java.awt.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;


/**
 * date: 2026-02-17
 * @author Sovren
 */
public class MainFrame extends JFrame {
    JLabel label = new JLabel();
    //ImageIcon image  = new ImageIcon(MainFrame.class.getResource("/res/imageTest.jpg")); // Unused
    Border border = BorderFactory.createLineBorder(Color.green,3);
    //ImageIcon scaledIcon =  scaleIcon(image, 400, 300); // Unused
    
    private JButton button;
    
    
    MainFrame(){
        setTitle("Fluffy Control Panel");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // HIDE_ON_CLOSE or EXIT_ON_CLOSE
        setSize(1280 , 720);
        setLocationRelativeTo(null);
        
        ImageIcon iconImage = new ImageIcon(MainFrame.class.getResource("/res/FluffyControlIcon.png"));
        setIconImage(iconImage.getImage());
                
        JPanel panel = new JPanel(new GridLayout(3,3,10,10));
        panel.setBackground(new Color(15,15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        add(panel);
        
                        
        panel.add(createButton("Japanese", () -> launchProfile("japanese")));
        panel.add(createButton("Programming", () -> launchProfile("programming")));
        panel.add(createButton("Manga Music", () -> launchProfile("mangamusic")));
        panel.add(createButton("Iron Lung Soundtrack",() -> launchProfile("ironlung")));
        panel.add(createButton("Vylet Pony's Music", () -> launchProfile("vyletpony")));
        panel.add(createButton("Random Task", () -> GUILinker("RNG")));
        panel.add(createButton("Completed Task", () -> GUILinker("TaskCompleted")));
        panel.add(createButton("Show Currency", () -> GUILinker("ShowCurrency")));
        // Rewards move else where? to a new tab or maybe change the default buttons.
        // Max 9 buttons and font 30
        panel.add(createButton("New Clothing Item", () -> GUILinker("RewardOne")));
        panel.add(createButton("Small Hobby Purchase", () -> GUILinker("RewardTwo")));
        panel.add(createButton("New Game", () -> GUILinker("RewardThree")));
        panel.add(createButton("Tech Accessory", () -> GUILinker("RewardFour")));
        panel.add(createButton("Day Trip", () -> GUILinker("RewardFive")));
        panel.add(createButton("Setup Upgrade", () -> GUILinker("RewardSix")));
        panel.add(createButton("Major Purchase", () -> GUILinker("RewardSeven")));
        panel.add(createButton("Weekend Trip", () -> GUILinker("RewardEight")));
        panel.add(createButton("Large Hobby Investment", () -> GUILinker("RewardNine")));
        
        
                
        /* // Unused
        // Label
        this.label.setText("DEBUG text");        
        this.label.setIcon(scaledIcon);
        label.setHorizontalTextPosition(JLabel.CENTER); // LEFT, CENTER, RIGHT
        label.setVerticalTextPosition(JLabel.TOP);  // TOP, CENTER, BOTTOM
        label.setForeground(new Color(0x00ff00)); // set font color of text
        label.setFont(new Font("Arial", Font.PLAIN,20)); // set font of text
        label.setIconTextGap(5); // set gap of text to image             
        label.setBackground(Color.red); // set label background color
        label.setOpaque(true); // display label background color
        label.setBorder(border);        
        label.setHorizontalAlignment(JLabel.CENTER); // set horizontal pos of icon+text within label
        label.setVerticalAlignment(JLabel.CENTER); // set vertical pos of icon+text within label
        label.setBounds(100, 100, 500, 350); // set x,y pos within frame as well as dimensions
                
        
        
        this.setLayout(null);
               
           
        button = createButton(); // buttons :3
        this.add(button);
        
        this.add(label);
        */
        setVisible(true);
    }
    
    
    /* // Unused
    public static ImageIcon scaleIcon(ImageIcon icon, int maxWidth, int maxHeight) {
        int originalWidth = icon.getIconWidth();
        int originalHeight = icon.getIconHeight();

        double scale = Math.min(
            (double) maxWidth / originalWidth,
            (double) maxHeight / originalHeight
        );

        int newWidth = (int) (originalWidth * scale);
        int newHeight = (int) (originalHeight * scale);

        Image scaled = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        return new ImageIcon(scaled);
    }
*/

    private JButton createButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(198,198,198));        
        button.setFont(new Font("Arial", Font.PLAIN,20)); // set font of text Default: "Arial", 30
        //button.setBounds(700,100,150,50); // x, y, width, height
        button.addActionListener(e -> action.run());
        return button;
    }    
    
    private void launchProfile(String command){
        FluffyControl.profiles(command);
    }
    
    private void shutdownRequest(){
        FluffyControl.shutdown("30");
    }
    
    
    // Links functions to buttons
    private void GUILinker(String type){
       Scanner input = new Scanner(System.in);
       Tasks tasks = new Tasks();
       UserData data = tasks.loadData();
       
       // Link the rewards to a json instead. { Text, credits, tokens} Use the text for the button aswell
       switch(type){
           case "RNG" -> {String text = tasks.randomTask(data, false);
           JOptionPane.showMessageDialog(null, text);}
           case "ShowCurrency" -> {
               String currencyText = "You have " + data.getCredits() + " credits and " + data.getTokens() + " tokens.";
                JOptionPane.showMessageDialog(null, currencyText, "Currency", 1);
           }
           case "TaskCompleted" -> {
            String[] options = {"Light", "Normal", "Heavy", "Milestone"};
            int reply = JOptionPane.showOptionDialog(null, "What type of task did you complete?", "Completed task", JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            switch(reply){
                case 0 -> {data.setCredits(data.getCredits() + 10); tasks.saveData(data);}
                case 1 -> {data.setCredits(data.getCredits() + 30); tasks.saveData(data);}
                case 2 -> {data.setCredits(data.getCredits() + 70); tasks.saveData(data);}
                case 3 -> {data.setCredits(data.getCredits() + 1000) ;
                    String[] options2 = {"1 token", "2 tokens", "3 tokens"};
                    int milestoneReply = JOptionPane.showOptionDialog(null, "How much tokens does this milestone award?", "Milestone completed", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options2, options[0]);
                    if (milestoneReply == 0){
                    data.setTokens(data.getTokens() + 1);
                    System.out.printf("%nDEBUG: Current Credits: %d  Current Tokens: %d",
                            data.getCredits(), data.getTokens()); tasks.saveData(data);
                    } else if (milestoneReply == 1){
                        data.setTokens(data.getTokens() + 2);                
                        System.out.printf("%nDEBUG: Current Credits: %d  Current Tokens: %d",
                                data.getCredits(), data.getTokens()); tasks.saveData(data);
                    } else if (milestoneReply == 2){
                        data.setTokens(data.getTokens() + 3);                
                        System.out.printf("%nDEBUG: Current Credits: %d  Current Tokens: %d",
                                data.getCredits(), data.getTokens()); tasks.saveData(data);
                    } else if (milestoneReply == JOptionPane.CLOSED_OPTION){
                        System.out.printf("%nCanceled completion");
                        System.out.printf("%nDEBUG: Current Credits: %d  Current Tokens: %d",
                                data.getCredits(), data.getTokens());
                    }
                }
                default -> {System.out.println("Task completion canceled");}
            }
           }
           // Rewards
           case "RewardOne" -> {String reward = "New Clothing Item";
           purchaseHandler(reward, 600, 1, data, tasks);}
           case "RewardTwo" -> {String reward = "Small Hobby Purchase";
           purchaseHandler(reward, 300, 1, data, tasks);}
           case "RewardThree" -> {String reward = "New Game";
           purchaseHandler(reward, 400, 1, data, tasks);}
           case "RewardFour" -> {String reward = "Tech Accessory";
           purchaseHandler(reward, 600, 1, data, tasks);}
           case "RewardFive" -> {String reward = "Day Trip";
           purchaseHandler(reward, 900, 1, data, tasks);}
           case "RewardSix" -> {String reward = "Setup Upgrade";
           purchaseHandler(reward, 1200, 1, data, tasks);}
           case "RewardSeven" -> {String reward = "Major Purchase";
           purchaseHandler(reward, 4000, 5, data, tasks);}
           case "RewardEight" -> {String reward = "Weekend Trip";
           purchaseHandler(reward, 2500, 3, data, tasks);}
           case "RewardNine" -> {String reward = "Large Hobby Investment";
           purchaseHandler(reward, 3000, 4, data, tasks);}
           // -------
           default -> System.out.println("Invalid GUI option");
       }
    }
    
    private void purchaseHandler(String reward, int costCredits, int costToken, UserData data, Tasks tasks) {
        String textLine = "Would you like to buy "+reward+" for "+costCredits+" credits or "+costToken+" tokens?";
        String[] options = {"Accept", "Decline"};
        int reply = JOptionPane.showOptionDialog(null, textLine, "Buy reward?", JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (reply == JOptionPane.YES_OPTION)
        {
            String[] options2 = {"Credits", "Tokens"};
            int currencynReply = JOptionPane.showOptionDialog(null, "Which type would you like to use?", "Currency Type", JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, options2, options[0]);
            if (currencynReply == 0){
                data.setCredits(data.getCredits() - costCredits);  tasks.saveData(data); // Removes credits & saves
                System.out.printf("%nDEBUG: Current Credits: %d  Current Tokens: %d",
                        data.getCredits(), data.getTokens());
                if(data.getCredits() < 0){ // Checks if user is less than 0 and refunds credits
                    data.setCredits(data.getCredits() + costCredits); tasks.saveData(data);
                    JOptionPane.showMessageDialog(null, "Unable to complete transaction due to final balance being less than 0!", "Transaction Failure", 2);
                }
            } else if (currencynReply == 1){
                data.setTokens(data.getTokens() - costToken); tasks.saveData(data); // Removes tokens & saves
                System.out.printf("%nDEBUG: Current Credits: %d  Current Tokens: %d",
                        data.getCredits(), data.getTokens()); 
                if(data.getTokens() < 0){ // Checks if user is less than 0 and refunds tokens
                    data.setTokens(data.getTokens() + costToken); tasks.saveData(data);
                    JOptionPane.showMessageDialog(null, "Unable to complete transaction due to final balance being less than 0!", "Transaction Failure", 2);
                }
            } else if (currencynReply == JOptionPane.CLOSED_OPTION){
                System.out.printf("%nCanceled transaction");
            }
        }
    }
}

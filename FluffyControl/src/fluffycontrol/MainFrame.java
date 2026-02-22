package fluffycontrol;

import java.awt.*;
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
        /*
        panel.add(createButton("Nothing here", () -> launchProfile("")));
        panel.add(createButton("Nothing here", () -> launchProfile("")));
        panel.add(createButton("Nothing here", () -> launchProfile("")));
        panel.add(createButton("Nothing here", () -> launchProfile("")));
        */
        
                
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
        button.setFont(new Font("Arial", Font.PLAIN,30)); // set font of text
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
}

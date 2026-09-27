package fluffycontrol;

import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * date: 2026-09-26
 * @author Sovren
 */
public class ConfigWindow extends JFrame{
    private static final Random rand = new Random();
    private Point mouseClickPoint; // Store initial mouse position on click
    private final Font menuFont = new Font("Monospaced", Font.BOLD, 12);

    private final Color backgroundColor = new Color(0, 128, 128);
    private final Color panelColor = new Color(174, 178, 188);
    private final Color phosphorGreen = new Color(0, 255, 65);


    public ConfigWindow(Gson gson) {
        setTitle("Fluffy Control Config Panel v" + FluffyControl.version);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(720, 500);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setResizable(false);

        Config loadedConfig = Config.loadFile(gson, new File(Config.configFile), true);

        JPanel rootPanel = new JPanel(new BorderLayout(10, 10));
        rootPanel.setBackground(backgroundColor);
        rootPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        setContentPane(rootPanel);

        //<editor-fold desc="Top Menu Bar">
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(panelColor);
        menuBar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        JLabel label = new JLabel("  " + "FluffyControl.Config");
        label.setFont(menuFont);
        menuBar.add(label);

        menuBar.add(Box.createHorizontalGlue());

        // Window buttons
        JButton hideBtn = new JButton("-");
        hideBtn.setFont(menuFont);
        hideBtn.setFocusPainted(false);
        hideBtn.addActionListener(e -> fluffycontrol.ConfigWindow.this.setExtendedState(fluffycontrol.ConfigWindow.this.getExtendedState() | Frame.ICONIFIED));
        menuBar.add(hideBtn);

        JButton closeBtn = new JButton("X");
        closeBtn.setFont(menuFont);
        closeBtn.setFocusPainted(false);
        closeBtn.addActionListener(e -> {
            MainFrame.theConfigWindow = null; // Makes sure that config window can be created again
            ConfigWindow.this.dispose();});
        menuBar.add(closeBtn);

        setJMenuBar(menuBar);

        // Drag behavior
        MouseAdapter dragAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Save mouse position relative to the window frame
                mouseClickPoint = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // Calculate current screen coordinates minus click offset
                Point currentScreenLocation = e.getLocationOnScreen();
                setLocation(
                        currentScreenLocation.x - mouseClickPoint.x,
                        currentScreenLocation.y - mouseClickPoint.y
                );
            }
        };
        menuBar.addMouseListener(dragAdapter);
        menuBar.addMouseMotionListener(dragAdapter);
        // </editor-fold>

        //<editor-fold desc="Application Settings">
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(panelColor);

        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Section Box
        JPanel formGroup = new JPanel(new GridLayout(4, 2, 4, 3));
        formGroup.setBackground(panelColor);
        TitledBorder groupBorder = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                " [ Application Settings ] "
        );
        groupBorder.setTitleFont(new Font("Monospaced", Font.BOLD, 12));
        formGroup.setBorder(groupBorder);


        // Objects Here:
        // Autostart config
        /* UI and saving code for future autostart feature
        JLabel autoStartLabel = createLabel("    AutoStart:");
        Boolean[] check = {true,false};
        JList<Boolean> autoStartList = new JList<>(check);
        autoStartList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        autoStartList.setSelectedValue(loadedConfig.isAutoStart(), true); // pre-selects the last selection

        autoStartList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    Boolean strAutoStart = autoStartList.getSelectedValue();
                    if(strAutoStart != null) {
                        System.out.println(LogColors.BLUE + LogColors.BOLD + "Selected: " + strAutoStart + LogColors.RESET);
                        // Change to auto start here / remove auto start here
                        loadedConfig.setAutoStart(strAutoStart); // Temp save
                    }
                }
            }
        });
         */

        // Bedtime config
        JLabel bedtimeLabel = createLabel("    Bedtime:");
        JTextField bedtimeField = createTextField("23,30");
        LocalTime bedtimeTime = loadedConfig.getBedtime();
        if (bedtimeTime != null) { // pre-selects the last selection
            bedtimeField.setText(bedtimeTime.toString());
        } else {
            bedtimeField.setText("09:00");
        }

        bedtimeField.addActionListener(e -> {
            String input = bedtimeField.getText().trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            try{
                LocalTime validatedTime = LocalTime.parse(input, formatter);
                System.out.println(LogColors.BLUE + LogColors.BOLD + "Time set: " + validatedTime + LogColors.RESET);
                //loadedConfig.setBedtime(validatedTime); // Temp save
            } catch (DateTimeException ex) {
                bedtimeField.setText("24-hour HH:mm format only!");
                System.out.println(LogColors.YELLOW + LogColors.BOLD + "[WARN] Invalid format. 24-Hour HH:mm format only"  + LogColors.RESET);
            }
        });


        // Wake up config
        JLabel wakeupLabel = createLabel("    Wake-up:");
        JTextField wakeupField = createTextField("9,0");
        LocalTime wakeupTime = loadedConfig.getWakeup();
        if (wakeupTime != null) { // pre-selects the last selection
            wakeupField.setText(wakeupTime.toString());
        } else {
            wakeupField.setText("09:00");
        }

        wakeupField.addActionListener(e -> {
            String input = wakeupField.getText().trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            try{
                LocalTime validatedTime = LocalTime.parse(input, formatter);
                System.out.println(LogColors.BLUE + LogColors.BOLD + "Time set: " + validatedTime + LogColors.RESET);
                //loadedConfig.setWakeup(validatedTime); // Temp save
            } catch (DateTimeException ex) {
                wakeupField.setText("24-hour HH:mm format only!");
                System.out.println(LogColors.YELLOW + LogColors.BOLD + "[WARN] Invalid format. 24-Hour HH:mm format only"  + LogColors.RESET);
            }
        });

        // Interface config
        JLabel interfaceNameLabel = createLabel("    Network interface:");
        String[] interfaceName = {"enp14s0", "eth0", "wlan0", "wlp15s0"};
        JList<String> interfaceNameList= new JList<>(interfaceName);
        interfaceNameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        interfaceNameList.setSelectedValue(loadedConfig.getINTERFACE_NAME(), true); // pre-selects the last selection

        interfaceNameList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    String strInterfaceName = interfaceNameList.getSelectedValue();
                    if(strInterfaceName != null) {
                        System.out.println(LogColors.BLUE + LogColors.BOLD + "Selected Interface: " + strInterfaceName + LogColors.RESET);
                        NetworkController.INTERFACE_NAME = strInterfaceName; // Change the network interfaced used
                        loadedConfig.setINTERFACE_NAME(strInterfaceName); // Temp save
                    }
                }
            }
        });

        //formGroup.add(autoStartLabel);
        //formGroup.add(autoStartList);
        formGroup.add(bedtimeLabel);
        formGroup.add(bedtimeField);
        formGroup.add(wakeupLabel);
        formGroup.add(wakeupField);
        formGroup.add(interfaceNameLabel);
        formGroup.add(interfaceNameList);
        mainPanel.add(formGroup, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonPanel.setBackground(panelColor);
        JButton saveButton = createButton("[Save]");
        saveButton.addActionListener(e -> {
            // Save code here then ->
            boolean canSave = true;

            String input = bedtimeField.getText().trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            try{
                LocalTime validatedTime = LocalTime.parse(input, formatter);
                System.out.println(LogColors.BLUE + LogColors.BOLD + "Time set: " + validatedTime + LogColors.RESET);
                loadedConfig.setBedtime(validatedTime);
            } catch (DateTimeException ex) {
                bedtimeField.setText("24-hour HH:mm format only!");
                System.out.println(LogColors.YELLOW + LogColors.BOLD + "[WARN] Invalid format. 24-Hour HH:mm format only"  + LogColors.RESET);
                canSave = false;
            }
            String input2 = wakeupField.getText().trim();
            try{
                LocalTime validatedTime = LocalTime.parse(input2, formatter);
                System.out.println(LogColors.BLUE + LogColors.BOLD + "Time set: " + validatedTime + LogColors.RESET);
                loadedConfig.setWakeup(validatedTime);
            } catch (DateTimeException ex) {
                wakeupField.setText("24-hour HH:mm format only!");
                System.out.println(LogColors.YELLOW + LogColors.BOLD + "[WARN] Invalid format. 24-Hour HH:mm format only"  + LogColors.RESET);
                canSave = false;
            }

            // Actual save
            if (canSave){
            String jsonOutput = gson.toJson(loadedConfig);
            Config.writeToFile(new File(Config.configFile), jsonOutput);
            MainFrame.theConfigWindow = null; // Makes sure that config window can be created again
            ConfigWindow.this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid format. Use 24-hour HH:mm format only.","Warning", JOptionPane.ERROR_MESSAGE);
            }
        });


        JButton cancelButton = createButton("[Cancel]");
        cancelButton.addActionListener(e -> {
            MainFrame.theConfigWindow = null; // Makes sure that config window can be created again
            ConfigWindow.this.dispose();
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        rootPanel.add(mainPanel, BorderLayout.CENTER);
        //</editor-fold>

        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(menuFont);
        label.setForeground(Color.BLACK);
        return label;
    }

    private JTextField createTextField(String text) {
        JTextField field = new JTextField(text);
        field.setFont(menuFont);
        field.setBackground(Color.WHITE);
        field.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        return field;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(menuFont);
        button.setBackground(panelColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder(4, 12, 4, 12)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}

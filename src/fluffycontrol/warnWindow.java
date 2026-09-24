package fluffycontrol;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class warnWindow extends JFrame{
    private Point mouseClickPoint; // Store initial mouse position on click
    private final Font menuFont = new Font("Monospaced", Font.BOLD, 12);
    private final Color backgroundColor = new Color(0,128,128);
    private final  Color panelColor = new Color(174, 178, 188);
    private final Color phosphorGreen = new Color(0, 255, 65);

    warnWindow() {
        createWindow("Sleep time\nPlease head to bed now. :3\n>"); // Change console text here
    }

    public void createWindow(String consoleText){
        setUndecorated(true);
        setAlwaysOnTop(true);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(700,480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel rootPanel = new JPanel(new BorderLayout(10, 10));
        rootPanel.setBackground(backgroundColor);
        rootPanel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        setContentPane(rootPanel);

        //<editor-fold desc="Top Menu Bar">
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(panelColor);
        menuBar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        JLabel label = new JLabel("  " + "FluffyControl.Warn");
        label.setFont(menuFont);
        menuBar.add(label);

        menuBar.add(Box.createHorizontalGlue());

        // Minimize window
        JButton hideBtn = new JButton("-");
        hideBtn.setFont(menuFont);
        hideBtn.setFocusPainted(false);
        hideBtn.addActionListener(e -> warnWindow.this.setExtendedState(warnWindow.this.getExtendedState() | Frame.ICONIFIED));
        menuBar.add(hideBtn);
        // Close window
        JButton closeBtn = new JButton("X");
        closeBtn.setFont(menuFont);
        closeBtn.setFocusPainted(false);
        //closeBtn.addActionListener(e -> System.exit(0));
        closeBtn.addActionListener(e -> warnWindow.this.dispose());
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

        //<editor-fold desc="Main Panel">
        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        mainPanel.setBackground(panelColor);

        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder(10,10,10,10)
        ));

        // --- Center Console Text Area ---
        JTextArea consoleOutput = new JTextArea();
        consoleOutput.setText(consoleText);
        consoleOutput.setFont(new Font("Monospaced", Font.PLAIN, 30));
        consoleOutput.setBackground(Color.BLACK);
        consoleOutput.setForeground(phosphorGreen);
        consoleOutput.setCaretColor(phosphorGreen);

        JScrollPane scrollPane = new JScrollPane(consoleOutput);
        scrollPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        rootPanel.add(mainPanel, BorderLayout.CENTER);
        //</editor-fold>

        setVisible(true);
    }
}

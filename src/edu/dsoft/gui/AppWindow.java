package edu.dsoft.gui;


import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {

    private static AppWindow instance;

    private AppWindow() {
        SwingUtilities.invokeLater(this::createGui);
    }

    private void createGui() {
        this.setSize(800,600);
        this.setTitle("Test App");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());
        this.add(new JTextField("testtest"),BorderLayout.NORTH);
    }

    public static AppWindow getInstance() {
        if(instance == null) {
            instance = new AppWindow();
        }
        return instance;
    }


}

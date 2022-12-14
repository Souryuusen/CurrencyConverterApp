package edu.dsoft.gui;


import edu.dsoft.ConverterModel;

import javax.swing.*;
import java.awt.*;

public class ConverterWindow extends JFrame {

    private static ConverterWindow instance;

    private final static int WINDOW_WIDTH = 600;
    private final static int WINDOW_HEIGHT = 800;

    private CurrencySelectionPanel currencySelectionPanel;

    private ConverterWindow() {
        SwingUtilities.invokeLater(this::createGui);
    }

    private void createGui() {
        currencySelectionPanel = new CurrencySelectionPanel();

        this.setSize(800,600);
        this.setTitle("Test App");
        this.setResizable(false);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.add(currencySelectionPanel.getSelectionPanel(), BorderLayout.NORTH);

        this.setVisible(true);
    }

    public void updateData() {
        if(currencySelectionPanel != null) {
            currencySelectionPanel.setCurrentSelectedPair(ConverterModel.getInstance().getCurrentPair());
        }
    }

    public static ConverterWindow getInstance() {
        if(instance == null) {
            instance = new ConverterWindow();
        }
        return instance;
    }


}

package edu.dsoft.gui;


import edu.dsoft.ConverterModel;

import javax.swing.*;
import java.awt.*;

public class ConverterWindow extends JFrame {

    private static ConverterWindow instance;

    private final static int WINDOW_WIDTH = 800;
    private final static int WINDOW_HEIGHT = 600;

    private ExchangePanel exchangePanel;

    private ConverterWindow() {
        SwingUtilities.invokeLater(this::createGui);
    }

    private void createGui() {
        exchangePanel = new ExchangePanel();

        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setTitle("Kalkulator Walutowy v1.0");
        this.setResizable(false);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

//        this.add(currencySelectionPanel.getSelectionPanel(), BorderLayout.NORTH);
        this.setContentPane(exchangePanel.getMainPanel());

        this.pack();
        this.setVisible(true);
    }

    public void updateData() {
        if(exchangePanel != null) {
            exchangePanel.setCurrentSelectedPair(ConverterModel.getInstance().getCurrentPair());
        }
    }

    public static ConverterWindow getInstance() {
        if(instance == null) {
            instance = new ConverterWindow();
        }
        return instance;
    }


}

package edu.dsoft.gui;

import javax.swing.*;

public class ExchangePanel {
    private JPanel mainPanel;
    private JPanel panelNorth;
    private JComboBox comboCurrency1;
    private JComboBox comboCurrency2;
    private JButton btnGetData;
    private JTextField fieldCurrentPair;
    private JTextField fieldCurrentAsk;
    private JTextField fieldCurrentBid;
    private JPanel panelWest;
    private JPanel panelCenter;
    private JPanel panelEast;
    private JButton btnSwapCurrency;
    private JTextField fieldAmountToExchange;
    private JButton btnExchange;
    private JTextField fieldAmountAfterExchange;
    private JButton btnCopy;
    private JPanel panelGridCenter;
    private JPanel panelGridEast;
    private JLabel labelCurrency1;
    private JLabel labelCurrency2;
    private JLabel labelCurrentPair;
    private JLabel labelCurrentAsk;
    private JLabel labelCurrentBid;
    private JLabel labelAmountToExchange;
    private JLabel labelAmountAfterExchange;
    private JPanel panelGridCenterWest;
    private JTextArea areaHistoricalCurrency1;
    private JScrollPane scrollSouthWest;
    private JScrollPane scrollSouthEast;
    private JTextArea areaHistoricalCurrency2;

    public JPanel getMainPanel() {
        return this.mainPanel;
    }
}

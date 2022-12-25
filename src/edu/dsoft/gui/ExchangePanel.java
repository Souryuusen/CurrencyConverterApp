package edu.dsoft.gui;

import edu.dsoft.ConverterController;
import edu.dsoft.ConverterModel;
import edu.dsoft.Pair;
import edu.dsoft.Rate;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class ExchangePanel {
    private JPanel mainPanel;
    private JPanel panelNorth;
    private JComboBox<String> comboCurrency1;
    private JComboBox<String> comboCurrency2;
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
    private JRadioButton radioAsk;
    private JPanel panelFlowNorthCenter;
    private JRadioButton radioBid;

    private String firstCode, secondCode;

    public ExchangePanel() {
        // Fill ComboBox Currencies Data
        setCurrencies();
        // Set Default Values Of ComboBoxes
        firstCode = "PLN";
        comboCurrency1.setSelectedIndex(0);
        secondCode = "PLN";
        comboCurrency2.setSelectedIndex(0);
        // Set Default Calcuation Value To Ask
        radioAsk.setSelected(true);
        // Creation Of GUI Elements Listeners
        btnGetData.addActionListener((e) -> {
            if (firstCode != null && secondCode != null) {
                ConverterController.getInstance().createCurrencyPair(firstCode, secondCode);
                fieldAmountToExchange.setEnabled(true);
                fieldAmountToExchange.setEditable(true);
                btnExchange.setEnabled(true);
                btnSwapCurrency.setEnabled(true);
            } else {
                // TODO: 14.12.2022 Dodanie Obsługi błędów związanych z brakiem wyboru waluty
                fieldAmountToExchange.setEnabled(false);
                fieldAmountToExchange.setEditable(false);
                btnExchange.setEnabled(false);
                btnSwapCurrency.setEnabled(false);
                System.err.println("Something Went Wrong With Codes Assignments");
            }
        });
        comboCurrency1.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                firstCode = (String) (e.getItem());
            }
        });

        comboCurrency2.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                secondCode = (String) (e.getItem());
            }
        });
        btnExchange.addActionListener((e) -> {
            BigDecimal valueToExchange = null;
            try {
                String fieldText = fieldAmountToExchange.getText().trim().replaceAll(",", ".");
                double doubleValue = Double.parseDouble(fieldText);
                valueToExchange = BigDecimal.valueOf(doubleValue);
            } catch (NumberFormatException ex) {
                System.err.println("Miepoprawna wartość do przeliczenia: " + fieldAmountToExchange.getText() + " !!");
                fieldAmountToExchange.setText("0.00");
                ex.printStackTrace();
            }

            if (valueToExchange != null) {
                Pair p = ConverterModel.getInstance().getCurrentPair();
                if (radioAsk.isSelected()) {
                    fieldAmountAfterExchange.setText(p.exchangeCurrencyAsk(valueToExchange).setScale(4, RoundingMode.HALF_EVEN).toString());
                } else if (radioBid.isSelected()) {
                    fieldAmountAfterExchange.setText(p.exchangeCurrencyBid(valueToExchange).setScale(4, RoundingMode.HALF_EVEN).toString());
                } else {
                    fieldAmountAfterExchange.setText("");
                }
            }
        });
        btnSwapCurrency.addActionListener((e) -> {
            ConverterModel.getInstance().swapRates();
            int firstIndex = comboCurrency1.getSelectedIndex();
            int secondIndex = comboCurrency2.getSelectedIndex();
            comboCurrency1.setSelectedIndex(secondIndex);
            comboCurrency2.setSelectedIndex(firstIndex);
        });
        btnCopy.addActionListener((e) -> {
            Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selection = new StringSelection(fieldAmountAfterExchange.getText());
            cb.setContents(selection, null);
        });
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    public void setCurrencies() {
        comboCurrency1.addItem("PLN");
        comboCurrency2.addItem("PLN");
        for (Rate r : ConverterModel.getInstance().getAvailableCurrencies()) {
            String code = r.getCurrencyCode();
            System.out.println(code);
            comboCurrency1.addItem(code);
            comboCurrency2.addItem(code);
        }
    }

    public void setCurrentSelectedPair(Pair p) {
        fieldCurrentPair.setText(p.getPairName());

        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(4);
        format.setMaximumFractionDigits(4);
        fieldCurrentAsk.setText(format.format(p.getPairExchangeRateAsk()));
        fieldCurrentBid.setText(format.format(p.getPairExchangeRateBid()));
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 1));
        mainPanel.setBackground(new Color(-13486793));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        panelNorth = new JPanel();
        panelNorth.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 11, new Insets(0, 0, 0, 0), -1, -1));
        panelNorth.setBackground(new Color(-3287041));
        mainPanel.add(panelNorth, BorderLayout.NORTH);
        panelNorth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        labelCurrency1 = new JLabel();
        labelCurrency1.setText("Waluta 1:");
        panelNorth.add(labelCurrency1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboCurrency1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        comboCurrency1.setModel(defaultComboBoxModel1);
        panelNorth.add(comboCurrency1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelCurrency2 = new JLabel();
        labelCurrency2.setText("Waluta 2:");
        panelNorth.add(labelCurrency2, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboCurrency2 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        comboCurrency2.setModel(defaultComboBoxModel2);
        panelNorth.add(comboCurrency2, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnGetData = new JButton();
        btnGetData.setText("Pobierz Dane");
        panelNorth.add(btnGetData, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelCurrentPair = new JLabel();
        labelCurrentPair.setText("Aktualna Para:");
        panelNorth.add(labelCurrentPair, new com.intellij.uiDesigner.core.GridConstraints(0, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fieldCurrentPair = new JTextField();
        fieldCurrentPair.setColumns(0);
        fieldCurrentPair.setEditable(false);
        fieldCurrentPair.setHorizontalAlignment(0);
        fieldCurrentPair.setText("");
        panelNorth.add(fieldCurrentPair, new com.intellij.uiDesigner.core.GridConstraints(0, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        labelCurrentAsk = new JLabel();
        labelCurrentAsk.setText("Ask:");
        panelNorth.add(labelCurrentAsk, new com.intellij.uiDesigner.core.GridConstraints(0, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fieldCurrentAsk = new JTextField();
        fieldCurrentAsk.setEditable(false);
        fieldCurrentAsk.setHorizontalAlignment(0);
        panelNorth.add(fieldCurrentAsk, new com.intellij.uiDesigner.core.GridConstraints(0, 8, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        labelCurrentBid = new JLabel();
        labelCurrentBid.setText("Bid:");
        panelNorth.add(labelCurrentBid, new com.intellij.uiDesigner.core.GridConstraints(0, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fieldCurrentBid = new JTextField();
        fieldCurrentBid.setEditable(false);
        fieldCurrentBid.setHorizontalAlignment(0);
        panelNorth.add(fieldCurrentBid, new com.intellij.uiDesigner.core.GridConstraints(0, 10, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        panelWest = new JPanel();
        panelWest.setLayout(new BorderLayout(0, 0));
        panelWest.setBackground(new Color(-3287041));
        mainPanel.add(panelWest, BorderLayout.WEST);
        panelWest.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        labelAmountToExchange = new JLabel();
        labelAmountToExchange.setHorizontalAlignment(0);
        labelAmountToExchange.setText("Kwota Do Przeliczenia");
        panelWest.add(labelAmountToExchange, BorderLayout.NORTH);
        panelGridCenterWest = new JPanel();
        panelGridCenterWest.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelWest.add(panelGridCenterWest, BorderLayout.CENTER);
        fieldAmountToExchange = new JTextField();
        fieldAmountToExchange.setEditable(false);
        fieldAmountToExchange.setEnabled(false);
        fieldAmountToExchange.setHorizontalAlignment(0);
        panelGridCenterWest.add(fieldAmountToExchange, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 30), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panelGridCenterWest.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        btnExchange = new JButton();
        btnExchange.setEnabled(false);
        btnExchange.setText("Przelicz");
        panelGridCenterWest.add(btnExchange, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout(0, 0));
        panelCenter.setBackground(new Color(-3287041));
        mainPanel.add(panelCenter, BorderLayout.CENTER);
        panelCenter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        panelGridCenter = new JPanel();
        panelGridCenter.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelGridCenter.setBackground(new Color(-3287041));
        panelCenter.add(panelGridCenter, BorderLayout.CENTER);
        panelGridCenter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        btnSwapCurrency = new JButton();
        btnSwapCurrency.setEnabled(false);
        btnSwapCurrency.setMargin(new Insets(0, 0, 0, 0));
        btnSwapCurrency.setText("< - - - - - >");
        panelGridCenter.add(btnSwapCurrency, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 30), null, 0, false));
        panelFlowNorthCenter = new JPanel();
        panelFlowNorthCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelCenter.add(panelFlowNorthCenter, BorderLayout.NORTH);
        radioAsk = new JRadioButton();
        radioAsk.setText("Ask");
        panelFlowNorthCenter.add(radioAsk);
        radioBid = new JRadioButton();
        radioBid.setText("Bid");
        panelFlowNorthCenter.add(radioBid);
        panelEast = new JPanel();
        panelEast.setLayout(new BorderLayout(0, 0));
        panelEast.setBackground(new Color(-3287041));
        mainPanel.add(panelEast, BorderLayout.EAST);
        panelEast.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        labelAmountAfterExchange = new JLabel();
        labelAmountAfterExchange.setHorizontalAlignment(0);
        labelAmountAfterExchange.setText("Kwota Przeliczona");
        panelEast.add(labelAmountAfterExchange, BorderLayout.NORTH);
        panelGridEast = new JPanel();
        panelGridEast.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelEast.add(panelGridEast, BorderLayout.CENTER);
        fieldAmountAfterExchange = new JTextField();
        fieldAmountAfterExchange.setEditable(false);
        fieldAmountAfterExchange.setHorizontalAlignment(0);
        panelGridEast.add(fieldAmountAfterExchange, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 30), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panelGridEast.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        btnCopy = new JButton();
        btnCopy.setText("Kopiuj");
        panelGridEast.add(btnCopy, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioAsk);
        buttonGroup.add(radioBid);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}

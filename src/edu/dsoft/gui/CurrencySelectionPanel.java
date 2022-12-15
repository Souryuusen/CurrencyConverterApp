package edu.dsoft.gui;

import edu.dsoft.ConverterController;
import edu.dsoft.Pair;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

public class CurrencySelectionPanel {
    protected JPanel selectionPanel;
    private JLabel labelFirstCurrency;
    private JComboBox<String> comboFirstCurrency;
    private JLabel labelSecondCurrency;
    private JComboBox<String> comboSecondCurrency;
    private JButton btnGetData;
    private JPanel pairSelectionPanel;
    private JPanel currenSelectedPanel;
    private JTextField labelCurrentPair;
    private JLabel labelCurrentAsk;
    private JTextField fieldCurrentAsk;
    private JLabel labelCurrentBid;
    private JTextField fieldCurrentBid;

    String[] currencyArray = {"USD", "EUR", "PLN", "JPY"};

    private String firstCode, secondCode;

    public CurrencySelectionPanel() {
        // Combo Items Fill
        addAllCodes(comboFirstCurrency, currencyArray);
        addAllCodes(comboSecondCurrency, currencyArray);
        // Initial Values Of Currency Codes
        this.firstCode = "USD";
        this.secondCode = "USD";
        // Creation Of GUI Listeners
        btnGetData.addActionListener((ActionEvent e) -> {
            if (firstCode != null && secondCode != null) {
                ConverterController.getInstance().createCurrencyPair(firstCode, secondCode);
            } else {
                // TODO: 14.12.2022 Dodanie Obsługi błędów związanych z brakiem wyboru waluty
                System.err.println("Something Went Wrong With Codes Assignments");
            }
        });

        comboFirstCurrency.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                firstCode = (String) (e.getItem());
            }
        });

        comboSecondCurrency.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                secondCode = (String) (e.getItem());
            }
        });

    }

    private void addAllCodes(JComboBox<String> box, String[] options) {
        for (String s : options) {
            if (box != null) {
                box.addItem(s);
            }
        }
    }

    public void setCurrentSelectedPair(Pair p) {
        labelCurrentPair.setText(p.getPairName());

        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(4);
        format.setMaximumFractionDigits(4);
        fieldCurrentAsk.setText(format.format(p.getPairExchangeRateAsk()));
        fieldCurrentBid.setText(format.format(p.getPairExchangeRateBid()));
    }

    protected JPanel getSelectionPanel() {
        return selectionPanel;
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
        selectionPanel = new JPanel();
        selectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 0));
        selectionPanel.setMaximumSize(new Dimension(600, 40));
        selectionPanel.setMinimumSize(new Dimension(600, 40));
        selectionPanel.setPreferredSize(new Dimension(600, 40));
        pairSelectionPanel = new JPanel();
        pairSelectionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        selectionPanel.add(pairSelectionPanel);
        pairSelectionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5), null, TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, null));
        labelFirstCurrency = new JLabel();
        labelFirstCurrency.setAlignmentX(0.5f);
        labelFirstCurrency.setText("Waluta 1");
        pairSelectionPanel.add(labelFirstCurrency);
        comboFirstCurrency = new JComboBox();
        pairSelectionPanel.add(comboFirstCurrency);
        labelSecondCurrency = new JLabel();
        labelSecondCurrency.setText("Waluta 2");
        pairSelectionPanel.add(labelSecondCurrency);
        comboSecondCurrency = new JComboBox();
        pairSelectionPanel.add(comboSecondCurrency);
        btnGetData = new JButton();
        btnGetData.setHideActionText(false);
        btnGetData.setText("Pobierz Dane");
        pairSelectionPanel.add(btnGetData);
        currenSelectedPanel = new JPanel();
        currenSelectedPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        currenSelectedPanel.setAlignmentX(0.5f);
        currenSelectedPanel.setAlignmentY(0.5f);
        selectionPanel.add(currenSelectedPanel);
        currenSelectedPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Aktualna Para:");
        label1.setVerifyInputWhenFocusTarget(false);
        label1.setVerticalAlignment(0);
        label1.setVerticalTextPosition(0);
        currenSelectedPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelCurrentPair = new JTextField();
        labelCurrentPair.setColumns(7);
        labelCurrentPair.setEditable(false);
        labelCurrentPair.setEnabled(true);
        labelCurrentPair.setHorizontalAlignment(0);
        labelCurrentPair.setMaximumSize(new Dimension(150, 30));
        labelCurrentPair.setMinimumSize(new Dimension(150, 30));
        labelCurrentPair.setPreferredSize(new Dimension(83, 30));
        labelCurrentPair.setText("       ");
        selectionPanel.add(labelCurrentPair);
        labelCurrentAsk = new JLabel();
        labelCurrentAsk.setText("Ask:");
        selectionPanel.add(labelCurrentAsk);
        fieldCurrentAsk = new JTextField();
        fieldCurrentAsk.setColumns(5);
        fieldCurrentAsk.setEditable(false);
        fieldCurrentAsk.setEnabled(true);
        fieldCurrentAsk.setHorizontalAlignment(0);
        fieldCurrentAsk.setMaximumSize(new Dimension(150, 30));
        fieldCurrentAsk.setMinimumSize(new Dimension(150, 30));
        fieldCurrentAsk.setPreferredSize(new Dimension(61, 30));
        fieldCurrentAsk.setText("       ");
        selectionPanel.add(fieldCurrentAsk);
        labelCurrentBid = new JLabel();
        labelCurrentBid.setText("Bid:");
        selectionPanel.add(labelCurrentBid);
        fieldCurrentBid = new JTextField();
        fieldCurrentBid.setColumns(5);
        fieldCurrentBid.setEditable(false);
        fieldCurrentBid.setEnabled(true);
        fieldCurrentBid.setHorizontalAlignment(0);
        fieldCurrentBid.setMaximumSize(new Dimension(150, 30));
        fieldCurrentBid.setMinimumSize(new Dimension(150, 30));
        fieldCurrentBid.setPreferredSize(new Dimension(61, 30));
        fieldCurrentBid.setText("       ");
        selectionPanel.add(fieldCurrentBid);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return selectionPanel;
    }

}

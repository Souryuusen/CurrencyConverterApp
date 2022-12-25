package edu.dsoft;

import java.util.HashSet;

public class ConverterModel {
    private static ConverterModel instance;
    private Rate firstRate, secondRate;
    private Pair currentPair;
    private HashSet<Rate> availableCurrencies = new HashSet<>();
    public Rate getFirstRate() {
        return firstRate;
    }

    public Rate getSecondRate() {
        return secondRate;
    }

    public Pair getCurrentPair() {
        return currentPair;
    }

    public HashSet<Rate> getAvailableCurrencies() {
        availableCurrencies.clear();
        availableCurrencies = (HashSet<Rate>) RateDAO.obtainCurrencyList();
        return availableCurrencies;
    }

    protected void createRates(String firstRateCode, String secondRateCode) {
        firstRate = new Rate(firstRateCode);
        secondRate = new Rate(secondRateCode);
        updatePair();
    }

    protected void updatePair() {
        createPair(firstRate, secondRate);
    }

    public void swapRates() {
        Rate tmp = (Rate) firstRate.clone();
        firstRate = (Rate) secondRate.clone();
        secondRate = tmp;
        updatePair();
        updateView();
    }

    private void createPair(Rate first, Rate second){
        currentPair = new Pair(first, second);
        updateView();
    }

    private void updateView() {
        ConverterView.getInstance().updateView();
    }

    public static ConverterModel getInstance() {
        if(instance == null) {
            instance = new ConverterModel();
            instance.availableCurrencies = (HashSet<Rate>) RateDAO.obtainCurrencyList();
        }
        return instance;
    }
}

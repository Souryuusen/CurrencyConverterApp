package edu.dsoft;

public class ConverterModel {
    private static ConverterModel instance;
    private Rate firstRate, secondRate;
    private Pair currentPair;

    public Rate getFirstRate() {
        return firstRate;
    }

    public Rate getSecondRate() {
        return secondRate;
    }

    public Pair getCurrentPair() {
        return currentPair;
    }

    protected void createRates(String firstRateCode, String secondRateCode) {
        firstRate = new Rate(firstRateCode);
        secondRate = new Rate(secondRateCode);
        updatePair();
    }

    protected void updatePair() {
        createPair(firstRate, secondRate);
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
        }
        return instance;
    }
}

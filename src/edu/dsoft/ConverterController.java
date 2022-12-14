package edu.dsoft;

import edu.dsoft.gui.ConverterWindow;

public class ConverterController {

    private static ConverterController instance;
    private ConverterView viewInstance;
    private ConverterModel modelInstance;

    private ConverterController() {

    }

    public static void main(String[] args) {
        getInstance().viewInstance = ConverterView.getInstance();
        getInstance().viewInstance.createApplicationWindow();

        getInstance().modelInstance = ConverterModel.getInstance();

        Rate r1 = new Rate("USD");
        Rate r2 = new Rate("EUR");
        System.out.println(r1.toString());
        System.out.println(r2.toString());

        Pair p = new Pair(r2, r1);
        System.out.println(p.toString());
    }

    public void createCurrencyPair(String firstCode, String secondCode){
        // Creation Of Rate Objects Based On User Selection
        modelInstance.createRates(firstCode, secondCode);
    }

    public static ConverterController getInstance() {
        if(instance == null) {
            instance = new ConverterController();
        }
        return instance;
    }

}

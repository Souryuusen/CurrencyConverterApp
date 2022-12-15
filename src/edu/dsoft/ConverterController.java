package edu.dsoft;


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

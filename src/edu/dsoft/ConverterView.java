package edu.dsoft;

import edu.dsoft.gui.ConverterWindow;

public class ConverterView {

    private static ConverterView instance;

    private ConverterWindow window;

    public static void main(String[] args) {
        instance = ConverterView.getInstance();
        instance.createApplicationWindow();
    }

    protected void createApplicationWindow() {
        window = ConverterWindow.getInstance();
    }

    protected void updateView(){
        window.updateData();
    }

    public static ConverterView getInstance() {
        if(instance == null) {
            instance = new ConverterView();
        }
        return instance;
    }
}

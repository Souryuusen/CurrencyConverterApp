package edu.dsoft;

import edu.dsoft.gui.AppWindow;

public class App {

    private AppWindow window;

    public static void main(String[] args) {
        App app = new App();
        app.window = AppWindow.getInstance();
    }

}

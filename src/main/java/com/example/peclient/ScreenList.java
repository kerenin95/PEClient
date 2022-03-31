package com.example.peclient;

/*
* Controls connectors between displayed screen, inputs to Main and connects to view controllers
* */
public enum ScreenList {
    SPLASHSCREEN("SplashViewController", "splash-view.fxml"),
    SPLASHWAIT("LoadScreenController", "load-screen.fxml"),
    SPASHGUIDE("SplashGuideController", "splash-guide.fxml"),
    MAINUI("MainViewController", "main-view.fxml");

    public final String name;
    public final String assignment;

    ScreenList(String name, String assignement) {
        this.name = name;
        this.assignment = assignement;
    }
}

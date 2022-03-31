package com.example.peclient;

/*
* Controls connectors between displayed screen, inputs to MainView and connects to view controllers
* */
public enum ScreenList {
    SPLASHSCREEN("SplashScreen", "main-view.fxml"),
    SPLASHWAIT("SplashWait", "splash-wait.fxml"),
    SPASHGUIDE("SplashGuideController", "splash-guide.fxml"),
    MAINUI("MainUIController", "main-ui3.fxml");

    public final String name;
    public final String assignment;

    ScreenList(String name, String assignement) {
        this.name = name;
        this.assignment = assignement;
    }
}

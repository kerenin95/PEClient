package com.example.peclient;

public enum SetScreens {
    MAINVIEW("MainView", "main-view.fxml"),
    SPLASHSCREEN("SplashScreen", "splash-screen.fxml"),
    SPLASHWAIT("SplashWait", "splash-wait.fxml"),
    SPASHGUIDE("SplashGuide", "splash-guide.fxml");

    public final String name;
    public final String assignment;

    private SetScreens(String name, String assignement) {
        this.name = name;
        this.assignment = assignement;
    }
}

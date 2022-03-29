package com.example.peclient;

public enum SetScreens {
    MAINVIEW("mainView", "main-view.fxml"),
    SPLASHSCREEN("splashScreen", "splash-screen.fxml");

    public final String name;
    public final String assignment;

    private SetScreens(String name, String assignement) {
        this.name = name;
        this.assignment = assignement;
    }
}

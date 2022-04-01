package com.example.peclient;

/*
* Controls connectors between displayed screen, inputs to Main and connects to view controllers
* */
public enum ScreenList {
    SPLASHVIEW("SplashViewController", "splash-view.fxml"),
    LOADSCREEN("LoadScreenController", "load-screen.fxml"),
    SPLASHGUIDE("SplashGuideController", "splash-guide.fxml"),
    MAINVIEW("MainViewController", "main-view.fxml");

    public final String name;
    public final String assignment;

    ScreenList(String name, String assignement) {
        this.name = name;
        this.assignment = assignement;
    }
}

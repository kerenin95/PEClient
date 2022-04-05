package com.example.peclient;

import com.google.api.client.http.GenericUrl;

/*
* Controls connectors between displayed screen, inputs to Main and connects to view controllers
* */
public enum ScreenList {
    SPLASHVIEW("LaunchScreenController", "launch-screen.fxml"),
    LOADSCREEN("AuthorizationScreenController", "authorization-screen.fxml"),
    SPLASHGUIDE("LoginVerificationScreenController", "login-verification-screen.fxml"),
    MAINVIEW("HomePageController", "home-page.fxml");

    public final String name;
    public final String assignment;

    ScreenList(String name, String assignement) {
        this.name = name;
        this.assignment = assignement;
    }
}

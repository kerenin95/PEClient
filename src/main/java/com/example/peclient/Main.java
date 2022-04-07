package com.example.peclient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Entry point for program with main method
 */
public class Main extends Application {
    public static boolean isInternetUp;
    private static Stage stage;

    public static void main(String[] args){launch();}

    /**
     * PreLoads all screens and stylesheets, sets window params
     * @param stage - parent view for client
     */
    @Override
    public void start(Stage stage) {
        Main.stage = stage;
        ScreenController mainView = new ScreenController();
        mainView.loadScreen(ScreenList.SPLASHVIEW.name, ScreenList.SPLASHVIEW.assignment);
        mainView.loadScreen(ScreenList.LOADSCREEN.name, ScreenList.LOADSCREEN.assignment);
        mainView.loadScreen(ScreenList.SPLASHGUIDE.name, ScreenList.SPLASHGUIDE.assignment);
        mainView.loadScreen(ScreenList.MAINVIEW.name, ScreenList.MAINVIEW.assignment);

        stage.setTitle("Personal Email Client");
        Scene scene = new Scene(mainView, 1080, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        mainView.setScreen(ScreenList.SPLASHVIEW.name);
        stage.show();
    }

    public static Stage getStage(){
        return stage;
    }
}

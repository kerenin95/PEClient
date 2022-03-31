package com.example.peclient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public static boolean isInternetUp;
    private static Stage stage;

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) {
        /*
        * Main Entrypoint for email client
        * @param takes stage
        * @return sets the screen view on program launch
        * */
        Main.stage = stage;
        ScreenController mainView = new ScreenController();
        mainView.loadScreen(ScreenList.SPLASHSCREEN.name, ScreenList.SPLASHSCREEN.assignment);
        mainView.loadScreen(ScreenList.SPLASHWAIT.name, ScreenList.SPLASHWAIT.assignment);
        mainView.loadScreen(ScreenList.SPASHGUIDE.name, ScreenList.SPASHGUIDE.assignment);
        mainView.loadScreen(ScreenList.MAINUI.name, ScreenList.MAINUI.assignment);

        stage.setTitle("Personal Email Client");
        Scene scene = new Scene(mainView, 1080, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        mainView.setScreen(ScreenList.SPLASHSCREEN.name);
        stage.show();
    }

    public static Stage getStage(){

        return stage;
    }


}

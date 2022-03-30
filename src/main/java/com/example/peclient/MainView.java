package com.example.peclient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {
    public static boolean isInternetUp;
    private static Stage stage;

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        /*
        * Main Entrypoint for email client
        * @param takes stage
        * @return sets the screen view on program launch
        * */
        this.stage = stage;
        ScreenController mainView = new ScreenController();
        mainView.loadScreen(SetScreens.MAINVIEW.name, SetScreens.MAINVIEW.assignment);

        stage.setTitle("Personal Email Client");
        Scene scene = new Scene(mainView, 1080, 600);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.sizeToScene();
        mainView.setScreen(SetScreens.SPLASHSCREEN.name);
        stage.show();
    }

    public static Stage getStage(){
        return stage;
    }


}

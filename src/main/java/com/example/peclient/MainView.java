package com.example.peclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {

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

        FXMLLoader mainView = new FXMLLoader(MainView.class.getResource("main-view.fxml"));

        stage.setTitle("Personal Email Client");
        Scene scene = new Scene(mainView.load(), 1080, 600);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.sizeToScene();
        stage.show();
    }

    public static Stage getStage(){
        return stage;
    }
}

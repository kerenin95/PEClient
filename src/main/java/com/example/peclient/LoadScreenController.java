package com.example.peclient;


import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class LoadScreenController implements Screen{

    ScreenController myController;
    public static boolean installed;

    private static Task<Boolean> backgroundTasks;


    @FXML
    private BorderPane splashGuideWait;

    @FXML
    private Label splashWaitLabel;

    @FXML
    void initialize(){

        backgroundTasks = new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                try {
                    Login.startAuthentication();
                    new SynchronizeMessages().fullSync();

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Internet Error at splashWait Task");
                    Main.isInternetUp = false;
                    Platform.runLater(() -> NotifyUser.getNotification("Internet Connection has lost",
                            "Please check your internet connection").showInformation());
                    return false;
                }
                return true;
            }
        };

        backgroundTasks.setOnSucceeded(event -> {
            if(backgroundTasks.getValue()) {
                Main.getStage().setResizable(true);
                myController.setScreen(ScreenList.MAINVIEW.name);
                new SynchronizeMessages().partialSync();
            }
        });


    }

    public static void startBackgroundTasks(){
        Thread bgTasks = new Thread(backgroundTasks);
        bgTasks.setDaemon(true);
        bgTasks.start();
    }

    @Override
    public void setScreenParent(ScreenController screenPage) {
        myController = screenPage;
    }
}

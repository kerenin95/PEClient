package com.example.peclient;


import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Allows all authorizations to be controlled
 * Syncs with Gmail with message request batches
 */
public class AuthorizationScreenController implements Screen {

    ScreenController myController;
    public static boolean installed;

    private static Task<Boolean> backgroundTasks;


    @FXML
    private BorderPane splashGuideWait;

    @FXML
    private Label splashWaitLabel;

    /**
     * Asynchronously runs Google authentication and Syncs messages from Gmail to Controller
     */
    @FXML
    void initialize(){

        backgroundTasks = new Task<>() {

            @Override
            protected Boolean call() throws Exception {

                try {
                    GoogleAuthorizationLogin.startAuthentication();
                    System.out.println("Google Authorization Login Success");
                    new SynchronizeMessages().fullSync();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Internet Error at splashWait Task");
                    Main.isInternetUp = false;
                    Platform.runLater(() -> NotificationBuilder.getNotification("Internet Connection has lost",
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

    /**
     *
     */
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

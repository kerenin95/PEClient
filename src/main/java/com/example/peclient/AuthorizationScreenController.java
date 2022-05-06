package com.example.peclient;


import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * @author bber9
 * @group PEClient
 * @description Middle layer between Google OAuth2 login and Client
 * View that displays connection pending and progress
 * Runs Google Auth
 */
public class AuthorizationScreenController implements Screen {

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

            /**
             * @description connects to Google Auth server and syncs emails else displays error msg
             * @return true if connection is successfully with Google
             * @throws Exception if error occurs
             */
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
     * Runs multithreading for connections
     */
    public static void startBackgroundTasks(){
        Thread bgTasks = new Thread(backgroundTasks);
        bgTasks.setDaemon(true);
        bgTasks.start();
    }

    /**
     * @param screenPage moves screen to mainView on success
     */
    @Override
    public void setScreenParent(ScreenController screenPage) {
        myController = screenPage;
    }
}

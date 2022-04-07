package com.example.peclient;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

/**
 * Warns user to accept Google authentication
 * Starts OAuth processes with Gmail
 */
public class LoginVerificationScreenController implements Screen {

    public StackPane splashGuide;
    ScreenController myController;


    @FXML
    public Label splashGuideLabel;

    @FXML
    public JFXButton continueFromSplash;

    @FXML
    public VBox splashGuideWait;

    @FXML
    void loginFromSplash(ActionEvent event) {
        showInfoDialog();

    }

    /**
     * Display warning dialog to user for authentication to begin
     */
    //this methods shows the information dialog box from where authentication starts
    public void showInfoDialog(){
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Information"));
        content.setBody(new Text("We are going to open your default browser window to let \n" +
                "you choose the gmail account , allow the specified permissions\n" +
                "and then close the window."));
        JFXDialog dialog = new JFXDialog(myController, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Okay");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                AuthorizationScreenController.startBackgroundTasks();
                myController.setScreen(ScreenList.LOADSCREEN.name);

            }
        });
        content.setActions(button);
        dialog.show();
    }


    @FXML
    void initialize() {
    }


    @Override
    public void setScreenParent(ScreenController screenPage) {
        myController = screenPage;
    }
}

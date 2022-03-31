package com.example.peclient;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

public class SplashGuideController implements Screen{

    ScreenController myController;


    @FXML
    private Label splashGuideLabel;

    @FXML
    private JFXButton continueFromSplash;

    @FXML
    private VBox splashGuideWait;

    @FXML
    void loginFromSplash(ActionEvent event) {
        showInfoDialog();

    }

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
                LoadScreenController.startBackgroundTasks();
                myController.setScreen(ScreenList.SPLASHWAIT.name);

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

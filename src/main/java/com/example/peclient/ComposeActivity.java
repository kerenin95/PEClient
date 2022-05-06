package com.example.peclient;

import com.google.api.services.gmail.model.Message;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author bber9
 * @description runs as middle layer between google servers and homePage view
 * allows display for emails and preview
 */
public class ComposeActivity {

    private Stage stage;

    private String to = "";
    private String from = "";
    private String sub = " ";
    private String body = " ";
    private final FormattedMessage formattedMessage;
    private final Message message;
    private final boolean isEditDraft;
    private final boolean isForward;
    public List<File> attachments = new ArrayList<File>();
    long attachmentFilesSize = 0;

    VBox parentContainer = new VBox();
    Label fromLabel = new Label("From");
    Label toLabel = new Label("To");
    Label subLabel = new Label("Subject");
    JFXCheckBox isHtml = new JFXCheckBox("Html");
    JFXTextField fromTextField = new JFXTextField();
    JFXTextField toTextField = new JFXTextField();
    JFXTextField subTextField = new JFXTextField();
    JFXTextArea composeTextArea = new JFXTextArea();
    JFXButton attachFile = new JFXButton();
    JFXButton sendButton = new JFXButton("Send");
    JFXButton saveButton = new JFXButton("Save");
    JFXButton discardButton = new JFXButton("Discard");
    ButtonBar attachedFilesButtonParent = new ButtonBar();

    FileChooser fileChooser = new FileChooser();


    /**
     * @description constructor for creation of email composition
     * @param message message preview
     * @param messageContent message content
     * @param isEditDraft checks if email is in draft mode(not sent)
     * @param isForward checks if email has been forwarded or is direct from sender
     */
    public ComposeActivity(FormattedMessage message, Message messageContent, boolean isEditDraft, boolean isForward){
        formattedMessage = message;
        this.message = messageContent;
        this.isEditDraft = isEditDraft;
        this.isForward = isForward;
        sceneLayout();}

    public  VBox getContent(){  return parentContainer;}


    /**
     * @description download emails to user /temp folder
     */
    public void setInfo(){
        List<File> previouslyAddedAttachments = null;
        if(formattedMessage != null && message != null){
            toTextField.setText(formattedMessage.getToEmailId());
            fromTextField.setText(formattedMessage.getFromEmailId());
            subTextField.setText(formattedMessage.getSubject());
            composeTextArea.setText(formattedMessage.getBodyText());
            try {
                previouslyAddedAttachments = GmailOperations.downloadAttachments(message,System.getProperty("user.home")+"/temp");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (previouslyAddedAttachments != null) {
                for(File f: previouslyAddedAttachments){
                    Button b = new Button(f.getName());
                    attachedFilesButtonParent.getButtons().add(b);
                    b.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            attachments.remove(attachedFilesButtonParent.getButtons().indexOf(b));
                            attachedFilesButtonParent.getButtons().remove(b);
                        }
                    });
                    attachments.add(f);
                }
            }
        }

    }


    /**
     * @description animations for homepage email list view and optional scrollbar(if needed)
     */
    public void sceneLayout(){

        parentContainer.setPrefHeight(430);
        parentContainer.setPrefWidth(460);

        HBox childContainer1 = new HBox();
        childContainer1.setAlignment(Pos.CENTER_LEFT);
        childContainer1.setPrefHeight(40);
        fromTextField.setPrefHeight(30);
        fromTextField.setPrefWidth(275);
        childContainer1.getChildren().add(0,fromLabel);
        childContainer1.getChildren().add(1,fromTextField);
        HBox.setMargin(fromLabel,new Insets(0,20,0,20));


        HBox childContainer2 = new HBox();
        childContainer2.setAlignment(Pos.CENTER_LEFT);
        childContainer2.setPrefHeight(40);
        toTextField.setPrefHeight(30);
        toTextField.setPrefWidth(275);
        childContainer2.getChildren().add(0,toLabel);
        childContainer2.getChildren().add(1,toTextField);
        HBox.setMargin(toLabel,new Insets(0,35,0,20));



        HBox childContainer3 = new HBox();
        childContainer3.setAlignment(Pos.CENTER_LEFT);
        childContainer3.setPrefHeight(40);
        subTextField.setPrefHeight(30);
        subTextField.setPrefWidth(275);
        childContainer3.getChildren().add(0,subLabel);
        childContainer3.getChildren().add(1,subTextField);
        HBox.setMargin(subLabel,new Insets(0,10,0,20));



        HBox childContainer4 = new HBox();
        childContainer4.setAlignment(Pos.CENTER_RIGHT);
        childContainer4.getChildren().add(isHtml);
        HBox.setMargin(isHtml, new Insets(0,20,0,0));



        HBox childContainer5 = new HBox();
        childContainer5.getChildren().add(composeTextArea);
        HBox.setHgrow(composeTextArea, Priority.ALWAYS);



        HBox childContainer6 = new HBox();
        ImageView attachPinImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ic_attach_file_black.png"))));
        attachPinImage.setFitHeight(20);
        attachPinImage.setFitWidth(20);
        attachFile.setGraphic(attachPinImage);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(attachedFilesButtonParent);
        scrollPane.setStyle("-fx-background: #ffffff");
        scrollPane.getStylesheets().add(getClass().getResource("/scrollpane.css").toExternalForm());
        childContainer6.getChildren().add(attachFile);
        childContainer6.getChildren().add(scrollPane);
        HBox.setMargin(attachFile, new Insets(5,5,5,20));
        HBox.setMargin(scrollPane, new Insets(5,0,5,0));
        HBox.setHgrow(scrollPane,Priority.ALWAYS);


        HBox childContainer7 = new HBox();
        childContainer7.setAlignment(Pos.CENTER_RIGHT);
        childContainer7.setPrefHeight(50);
        childContainer7.getChildren().add(sendButton);
        childContainer7.getChildren().add(saveButton);
        childContainer7.getChildren().add(discardButton);
        HBox.setMargin(sendButton,new Insets(0,10,0,5));
        HBox.setMargin(saveButton,new Insets(0,5,0,5));
        HBox.setMargin(discardButton,new Insets(0,5,0,5));



        parentContainer.getChildren().add(childContainer1);
        parentContainer.getChildren().add(childContainer2);
        parentContainer.getChildren().add(childContainer3);
        parentContainer.getChildren().add(childContainer4);
        parentContainer.getChildren().add(childContainer5);
        parentContainer.getChildren().add(childContainer6);
        parentContainer.getChildren().add(childContainer7);

        VBox.setVgrow(childContainer5,Priority.ALWAYS);

        setInfo();

    }

    /**
     * @description gets identifying information from email
     * any attachments associated with email
     * and draft information if needed
     * @param dialog email information to present to view when clicked on
     */
    public void setAction(JFXDialog dialog){
        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setLocalVariables();
                JFXSnackbar snackbar;
                try {
                    if(checkEmptyValues()) {
                        GmailOperations.sendMessage(to, from, sub, body, isHtml.isSelected(), attachments);
                        dialog.close();
                    }
                    else{
                        snackbar = new JFXSnackbar(parentContainer);
                    }
                } catch (MessagingException | IOException e) {
                    e.printStackTrace();
                }

            }
        });

        attachFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                fileChooser.setTitle("Open Attachment File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                        new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));
                List<File> fileList = fileChooser.showOpenMultipleDialog(stage);
                if (fileList != null && checkFilesSize(fileList)) {
                    attachFilesToUI(fileList);
                }
            }
        });
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setLocalVariables();
                if(isEditDraft){
                    try {
                        GmailOperations.updateDraft(formattedMessage.getDraftId(),to,from,sub,body,isHtml.isSelected(),attachments);
                    } catch (IOException | MessagingException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        GmailOperations.createDraft(to, from, sub, body, isHtml.isSelected(), attachments);
                    } catch (IOException | MessagingException e) {
                        e.printStackTrace();
                    }
                }
                dialog.close();
            }
        });


        discardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });

        composeTextArea.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if(event.getDragboard().hasFiles())
                    event.acceptTransferModes(TransferMode.ANY);
            }
        });
        composeTextArea.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                List<File> files = event.getDragboard().getFiles();
                if(files != null && checkFilesSize(files)){
                    attachFilesToUI(files);
                }
                else{
                    NotificationBuilder.getNotification("Attention", "Attachments size exceeded the limit of 35mb").showWarning();
                }
            }
        });

    }

    /**
     * @description downloads files safely based on size
     * @param files input of files to download (of any file type from email)
     * @return check file size for safe downloading
     */
    public boolean checkFilesSize(List<File> files){
        long size = 0;
        for(File file: files){
            size = size + file.length();
        }
        if((attachmentFilesSize + size) < 35000000) {
            System.out.println(size);
            return true;
        }
        System.out.println("Size exceeded : "+size);
        return false;
    }

    /**
     * @description allows files view from get all attachments button on main screen
     * @param fileList file stream established to present in view
     */
    public void attachFilesToUI(List<File> fileList){
        for(File f: fileList){
            Button b = new Button(f.getName());
            attachedFilesButtonParent.getButtons().add(b);
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    attachments.remove(attachedFilesButtonParent.getButtons().indexOf(b));
                    attachedFilesButtonParent.getButtons().remove(b);
                }
            });
            attachments.add(f);
            attachmentFilesSize += f.length();
        }
    }

    /**
     * @description setter method for stage class var
     * @param stage overview
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }

    /**
     * @description checks if email list is empty
     * @return boolean check
     */
    public boolean checkEmptyValues(){
        return (toTextField.getText() != null && !toTextField.getText().equals("")) && (fromTextField.getText() != null && fromTextField.getText().equals(""));
    }

    /**
     * @description checks header information from email for display
     */
    public void setLocalVariables(){
        if(toTextField.getText() != null)
            to = toTextField.getText();
        if(fromTextField.getText() != null)
            from = fromTextField.getText();
        if(subTextField.getText() != null)
            sub = subTextField.getText();
        if(composeTextArea.getText() != null)
            body = composeTextArea.getText();
    }

}
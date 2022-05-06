package com.example.peclient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 * @author bber9
 * @description visuals for email categories and titles
 */
public class NavBarController {
    GridPane listCellBox = new GridPane();
    StackPane profilePic = new StackPane();
    Label name = new Label();
    Label subject = new Label();
    Label dateText = new Label();
    String labelId;

    /**
     * @description constructor sets the id of the label to be displayed and creates a scene for that label
     * @param labelId of the category label to be displayed on the navbar
     */
    public NavBarController(String labelId) {
        this.labelId = labelId;
        sceneLayout(); }

    /**
     * @description visual style for nav bar labels
     */
    public void sceneLayout(){
        ColumnConstraints column0 = new ColumnConstraints(10,45,50);
        ColumnConstraints column1 = new ColumnConstraints(10,120,2048);
        RowConstraints row = new RowConstraints(10,40,40);
        column0.setHgrow(Priority.ALWAYS);
        column1.setHgrow(Priority.ALWAYS);

        BorderPane colParent = new BorderPane();
        profilePic.setPrefWidth(50);
        profilePic.setPrefHeight(50);
        colParent.setCenter(profilePic);

        VBox colParentContainer = new VBox();

        HBox colChildContainer1 = new HBox();
        colChildContainer1.setAlignment(Pos.BOTTOM_LEFT);
        name.setPrefHeight(20);
        name.setPrefWidth(265);
        name.setFont(new Font(14));
        dateText.setAlignment(Pos.CENTER_RIGHT);
        dateText.setPrefWidth(110);
        dateText.setPrefHeight(20);
        colChildContainer1.getChildren().add(0,name);
        colChildContainer1.getChildren().add(1,dateText);
        HBox.setMargin(dateText, new Insets(0,10,2,0));
        HBox.setHgrow(name, Priority.ALWAYS);

        HBox colChildContainer2 = new HBox();
        subject.setPrefHeight(20);
        subject.setPrefWidth(665);
        //materialize delete button by removing comments

        colChildContainer2.getChildren().add(0,subject);
        HBox.setHgrow(subject,Priority.ALWAYS);

        colParentContainer.getChildren().add(0,colChildContainer1);
        colParentContainer.getChildren().add(1,colChildContainer2);
        listCellBox.add(colParent,0,0);
        listCellBox.add(colParentContainer, 1,0);
        listCellBox.setPrefHeight(40);
    }

    /**
     * @description sets title for each category and style
     * @param formattedMessage the message type that will be pulled into display
     */
    public void setInfo(FormattedMessage formattedMessage){
        MaterialStyleGuide td = null ;
        subject.setText(formattedMessage.getSubject());
        switch(labelId){
            case "INBOX" : name.setText(formattedMessage.getFrom());
                td = new MaterialStyleGuide(formattedMessage.getFromProfilePicString(), profilePic);
                break;
            case "SENT" : name.setText(formattedMessage.getTo());
                td = new MaterialStyleGuide(formattedMessage.getToProfilePicString(), profilePic);
                break;
            case "DRAFT" : name.setText(formattedMessage.getTo());
                td = new MaterialStyleGuide(formattedMessage.getToProfilePicString(), profilePic);
                break;
            case "TRASH" : name.setText(formattedMessage.getFrom());
                td = new MaterialStyleGuide(formattedMessage.getFromProfilePicString(), profilePic);
        }
        td.buildCircularTextImage();

        dateText.setText(formattedMessage.getDate());

    }

    public GridPane getListCellBox() { return listCellBox;}

}
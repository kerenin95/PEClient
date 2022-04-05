package com.example.peclient;

import com.jfoenix.controls.JFXListCell;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class CustomListCell extends JFXListCell<FormattedMessage> {

    private final CustomListCellView1 clcv;

    public CustomListCell(String labelId) {
        super();
        clcv = new CustomListCellView1(labelId);
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

    }

    @Override
    public void updateItem(FormattedMessage data, boolean empty) {
        super.updateItem(data, empty);
        if (data != null && !empty) {
            GridPane container = clcv.getListCellBox();
            container.setMouseTransparent(true);
            clcv.setInfo(data);
            this.setPrefWidth(0);
            container.prefWidthProperty().bind(this.widthProperty());
            setGraphic(container);
        }

    }
}

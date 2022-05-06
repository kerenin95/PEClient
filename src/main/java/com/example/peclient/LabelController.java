package com.example.peclient;

import com.jfoenix.controls.JFXListCell;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * @author bber9
 * @description handles graphics for gmail labels and routing for labels
 */
public class LabelController extends JFXListCell<FormattedMessage> {

    private final NavBarController clcv;

    /**
     * @description
     * @param labelId button to control gmail labels
     */
    public LabelController(String labelId) {
        super();
        clcv = new NavBarController(labelId);
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        });
    }

    /**
     * @description allows updates to the gmail labels
     * @param data message component to show notifications
     * @param empty check for routing inbound email to proper label group
     */
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

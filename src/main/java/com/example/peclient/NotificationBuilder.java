package com.example.peclient;

import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class NotificationBuilder {

    public static Notifications getNotification(String title, String text){
        Notifications notifications = Notifications.create().title(title).text(text).graphic(null).hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT);
        return notifications;
    }
}

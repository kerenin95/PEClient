module com.example.peclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.jfoenix;
    requires google.oauth.client;
    requires google.oauth.client.java6;
    requires google.oauth.client.jetty;
    requires google.api.client;
    requires google.http.client;
    requires google.http.client.jackson2;
    requires google.api.services.gmail.v1.rev62;

    opens com.example.peclient to javafx.fxml;
    exports com.example.peclient;
}
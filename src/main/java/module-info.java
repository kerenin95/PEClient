module com.example.peclient {
    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.api.services.gmail;
    requires mail;
    requires com.jfoenix;
    requires google.api.client;
    requires google.http.client;
    requires activation;
    requires java.sql;
    requires javafx.fxml;
    requires com.google.api.client.auth;
    requires com.google.api.client.extensions.java6.auth;
    requires com.google.api.client.extensions.jetty.auth;
    requires com.google.api.client.json.gson;
    requires org.controlsfx.controls;
    requires javafx.web;
    requires google.http.client.jackson2;


    opens com.example.peclient to javafx.fxml;
    exports com.example.peclient;
}
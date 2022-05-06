module com.example.peclient {
    requires javafx.graphics;
    requires javafx.controls;
    requires mail;
    requires com.jfoenix;
    requires google.api.client;
    requires google.http.client;
    requires activation;
    requires java.sql;
    requires javafx.fxml;
    requires com.google.api.client.auth;
    requires com.google.api.client.extensions.java6.auth;
    requires com.google.api.client.json.gson;
    requires javafx.web;
    requires google.oauth.client.jetty;
    requires controlsfx;
    requires google.api.services.gmail.v1.rev62;
    requires jdk.javadoc;


    opens com.example.peclient to javafx.fxml;
    exports com.example.peclient;
}
package com.example.peclient;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class GoogleAuthorizationLogin {


    private static final String CREDENTIALS_FILE_PATH = "client_secret.json";

    private static final String APPLICATION_NAME = "PEClient";

    private static final java.io.File DATA_STORE_DIR =
            new java.io.File(System.getProperty("user.home"), ".credentials/email_credentials");

    private static FileDataStoreFactory dataStoreFactory;

    private static HttpTransport HTTP_TRANSPORT;

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    static Gmail service;

    public static void startAuthentication() throws GeneralSecurityException, IOException {
        HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
        // authorization Collections.singleton(GmailScopes.MAIL_GOOGLE_COM)
        Credential credential = authorize();

        service =  new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private static Credential authorize() throws IOException {
        // load client secrets
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(GoogleAuthorizationLogin.class.getResourceAsStream(CREDENTIALS_FILE_PATH)));
        if (clientSecrets == null) {
            System.out.println("Unable to find path at: " + CREDENTIALS_FILE_PATH);
        }

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, Collections.singleton("https://mail.google.com/"))
                .setDataStoreFactory(new FileDataStoreFactory(DATA_STORE_DIR))
                .setAccessType("offline")
                .build();

        // authorize
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(9089).build();
        System.out.println("flow: " + flow + "\n" + "receiver: " + receiver);
        AuthorizationCodeInstalledApp app = new AuthorizationCodeInstalledApp(flow, receiver);

        Credential credential = null;
        try {
            credential = app.authorize("user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Credential received: " + credential);
        return credential;
    }
}
    

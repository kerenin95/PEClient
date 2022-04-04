package com.example.peclient;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.Gmail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Login {

    private static final String CREDENTIALS_FILE_PATH = "client_secret.json";

    private static final String APPLICATION_NAME = "peclient-346214";

    private static final java.io.File DATA_STORE_DIR =
            new java.io.File(System.getProperty("user.home"), ".credentials/email_credentials");

    private static FileDataStoreFactory dataStoreFactory;

    private static HttpTransport httpTransport;

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    static Gmail service;

    public static void startAuthentication() throws GeneralSecurityException, IOException {
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
        // authorization
        Credential credential = authorize();

        service =  new Gmail.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private static Credential authorize() throws IOException {
        // load client secrets
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(Login.class.getResourceAsStream(CREDENTIALS_FILE_PATH)));
        if (clientSecrets.getDetails().getClientId().startsWith("Enter")
                || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
            System.out.println(
                    "Enter Client ID and Secret from https://code.google.com/apis/console/?api=plus "
                            + "into plus-cmdline-sample/src/main/resources/client_secrets.json");
            System.exit(1);
        }

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, Collections.singleton(GmailScopes.MAIL_GOOGLE_COM))
                .setDataStoreFactory(dataStoreFactory)
                .setAccessType("offline")
                .build();
        // authorize
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
}
    /*private static final String CREDENTIALS_FILE_PATH = "/client_secret.json";
    public static Gmail service = null;
    *//**
     * Application name.
     *//*
    private static final String APPLICATION_NAME =
            "PEClient";

    *//**
     * Directory to store user credentials for this application.
     *//*
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/email_credentials");

    *//**
     * Global instance of the {@link FileDataStoreFactory}.
     *//*
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    *//**
     * Global instance of the JSON factory.
     *//*
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    *//**
     * Global instance of the HTTP transport.
     *//*
    private static HttpTransport HTTP_TRANSPORT;

    *//**
     * Global instance of the scopes required by this quickstart.
     * <p>
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/gmail-java-quickstart
     *//*
    private static final List<String> SCOPES =
            Arrays.asList(GmailScopes.MAIL_GOOGLE_COM);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            //DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    *//**
     * Creates an authorized Credential object.
     *
     * @return an authorized Credential object.
     * @throws IOException
     *//*
    public static Credential authorize() throws IOException {

        *//*Credential credential = null;
        // Load client secrets.
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream in =
                Login.class.getClass().getResourceAsStream("/client_secret.json");
        System.out.println("in: " + in);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + "/client_secret.json");
        }

        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));
        System.out.println("client secrets: " + clientSecrets);
        // Build flow and trigger user authorization request.
        try {
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(new FileDataStoreFactory(DATA_STORE_DIR))
                        .setAccessType("online")
                        .build();
        System.out.println("flow created: " + flow);

            LocalServerReceiver localSrv = new LocalServerReceiver();
            AuthorizationCodeInstalledApp app = new AuthorizationCodeInstalledApp(
                    flow, localSrv);
            System.out.println("app created: " + app);

            credential = app.authorize(null);
            System.out.println(
                    "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());

            //in.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return credential;*//*
    }

    *//**
     * Build and return an authorized Gmail client service.
     *
     * @return an authorized Gmail client service
     * @throws IOException
     *//*
    public static void startAuthentication() throws IOException, GeneralSecurityException {
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
        // authorization
        Credential credential = authorize();

    }
        Credential credential = authorize();
        System.out.println("authorized");
        service =  new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }*/
    

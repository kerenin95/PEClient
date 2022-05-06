package com.example.peclient;

import javafx.concurrent.Task;

import java.io.IOException;
import java.sql.SQLException;

public class SynchronizeMessages {

    public void fullSync() throws IOException, SQLException, ClassNotFoundException {
        GmailOperations.loadMailBox();
        new DatabaseTableInit();
        SaveMessages.saveMailbox();
        DatabaseConnectors.setHelperValues(DatabaseConnectors.loggedIn, "true");
        System.out.println("Sync done");
    }

    public void partialSync(){
        Task<Void> syncNewMails = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GmailOperations.getNewInboxMails();
                    GmailOperations.getNewSentMails();
                    GmailOperations.getNewDraftMails();
                    GmailOperations.getNewTrashMails();

                }
            }
        };

        Thread backgroundThread = new Thread(syncNewMails);
        backgroundThread.setDaemon(true);
        backgroundThread.start();

    }
}

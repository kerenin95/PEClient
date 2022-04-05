package com.example.peclient;

import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTableInit {
    public DatabaseTableInit(){
        initTables();

    }

    public void initTables(){
        try {
            initInbox();
            initSent();
            initDraft();
            initTrash();
            initHelper();
            System.out.println("Tables Created Successfully");
        } catch (Exception e) {
            System.out.println("Error while creating tables in initTables");
            e.printStackTrace();
        }
    }

    private void initInbox() throws SQLException, ClassNotFoundException {
        String sql1 = "DROP TABLE IF EXISTS inbox";
        String sql2 = "CREATE TABLE inbox " +
                "(msgId Varchar(100) NOT NULL, " +
                "threadId Varchar(100) NOT NULL, " +
                "historyId Varchar(100) NOT NULL, " +
                "sentTo VARCHAR(1000) NOT NULL, " +
                "recievedFrom Varchar(1000) NOT NULL, " +
                "mailDate Varchar(1000) NOT NULL," +
                "subject Varchar(10000) , " +
                "isUnread int NOT NULL);";
        Statement statement = DatabaseLink.getConnection().createStatement();
        statement.executeUpdate(sql1);
        statement.executeUpdate(sql2);
        statement.close();
    }

    private void initSent() throws SQLException, ClassNotFoundException {
        String sql1 = "DROP TABLE IF EXISTS sent";
        String sql2 = "CREATE TABLE sent " +
                "(msgId Varchar(100) NOT NULL, " +
                "threadId Varchar(100) NOT NULL, " +
                "historyId Varchar(100) NOT NULL, " +
                "sentTo VARCHAR(1000), " +
                "recievedFrom Varchar(1000) NOT NULL, " +
                "mailDate Varchar(1000) NOT NULL," +
                "subject Varchar(10000), " +
                "isUnread int NOT NULL);";
        Statement statement = DatabaseLink.getConnection().createStatement();
        statement.executeUpdate(sql1);
        statement.executeUpdate(sql2);
        statement.close();
    }

    private void initDraft() throws SQLException, ClassNotFoundException {
        String sql1 = "DROP TABLE IF EXISTS draft";
        String sql2 = "CREATE TABLE draft " +
                "(msgId Varchar(100) NOT NULL, " +
                "threadId Varchar(100) NOT NULL, " +
                "historyId Varchar(100) NOT NULL, " +
                "sentTo VARCHAR(1000), " +
                "recievedFrom Varchar(1000), " +
                "mailDate Varchar(1000) NOT NULL," +
                "subject Varchar(10000), " +
                "isUnread int);";
        Statement statement = DatabaseLink.getConnection().createStatement();
        statement.executeUpdate(sql1);
        statement.executeUpdate(sql2);
        statement.close();
    }

    private void initTrash() throws SQLException, ClassNotFoundException {
        String sql1 = "DROP TABLE IF EXISTS trash";
        String sql2 = "CREATE TABLE trash " +
                "(msgId Varchar(100) NOT NULL, " +
                "threadId Varchar(100) NOT NULL, " +
                "historyId Varchar(100) NOT NULL, " +
                "sentTo VARCHAR(1000), " +
                "recievedFrom Varchar(1000), " +
                "mailDate Varchar(1000) NOT NULL," +
                "subject Varchar(10000), " +
                "isUnread int);";
        Statement statement = DatabaseLink.getConnection().createStatement();
        statement.executeUpdate(sql1);
        statement.executeUpdate(sql2);
        statement.close();
    }

    public void initHelper() throws SQLException, ClassNotFoundException {
        String sql1 = "DROP TABLE IF EXISTS helper";
        String sql2 = "CREATE TABLE helper" +
                "(name varchar(100) NOT NULL, " +
                "pairValue varchar(100) NOT NULL, " +
                "PRIMARY KEY(name));";
        Statement statement = DatabaseLink.getConnection().createStatement();
        statement.executeUpdate(sql1);
        statement.executeUpdate(sql2);
    }

}
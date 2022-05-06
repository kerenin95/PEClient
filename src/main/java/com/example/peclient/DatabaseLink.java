package com.example.peclient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * Embedded database connector
 */
public class DatabaseLink {
    private static Connection connection;
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String url = "jdbc:h2:~/test";
    private static final String user = "sa";
    private static final String pass = "";


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
        }
        return connection;
    }
}
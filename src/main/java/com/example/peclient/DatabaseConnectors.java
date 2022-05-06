package com.example.peclient;

import java.sql.*;

/**
 * @author bber9
 * @description grabs stored credentials in embedded DB and confirms auth
 */
public class DatabaseConnectors {

    public static String loggedIn = "login";

    /**
     * @description checks the user is logged into google to
     * complete connection when required to send or retrieve data from google
     * @param name userName of login user
     * @return the value of the logged in status
     */
    public static String getHelperValues(String name) {
        String res = "";
        try {
            Statement statement = DatabaseLink.getConnection().createStatement();
            String sql = "SELECT pairValue FROM helper WHERE name = '" + name + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                res = resultSet.getString("pairValue");
            resultSet.close();
            statement.close();
            return res;
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @description builds connection and database statement for embedded db
     * @param name key associated with pushed changes
     * @param value contents of change to push
     */
    public static void setHelperValues(String name , String value){

        try {
            PreparedStatement statement = DatabaseLink.getConnection().prepareStatement("INSERT INTO helper VALUES(?,?)");
            statement.setString(1,name);
            statement.setString(2,value);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * @description builds delete statement for embedded db
     * @param name target key of deletion
     */
    public static void deleteHelperValue(String name){
        try {
            Statement s = DatabaseLink.getConnection().createStatement();
            s.executeUpdate("DELETE FROM helper WHERE name = '" + name + "'");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
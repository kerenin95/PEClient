package com.example.peclient;

import java.sql.*;

public class DatabaseConnectors {

    public static String loggedIn = "login";

    public static String getHelperValues(String name) {
        String res = "";
        try {
            /*Statement statement = DatabaseLink.getConnection().createStatement();
            String sql = "SELECT pairValue FROM helper WHERE name = '" + name + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                res = resultSet.getString("pairValue");
            resultSet.close();
            statement.close();*/
            return res;
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

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

    public static void deleteHelperValue(String name){
        try {
            Statement s = DatabaseLink.getConnection().createStatement();
            s.executeUpdate("DELETE FROM helper WHERE name = '" + name + "'");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
package main.ecommerce_d;

import java.sql.*;

public class DatabaseConnection {
    Connection con = null;
    String SQLURL = "jdbc:mysql://localhost:3306/ecommerce?useSSL=false";
    String username = "root";
    String password = "root1234";
    DatabaseConnection() throws SQLException {
        con = DriverManager.getConnection(SQLURL, username, password);
            if(con != null){
                System.out.println("OUR Connection is established with the ecommerce db");
            }
    }

    public ResultSet executeQuery(String query) throws SQLException {

        ResultSet result = null;
        try{
            Statement statement = con.createStatement();
            result = statement.executeQuery(query);
            return result;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public int executeUpdate(String query) throws SQLException {
        int row = 0;
        try {
            Statement statement = con.createStatement();
            row = statement.executeUpdate(query);
            return row;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }
}

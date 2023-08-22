package com.example.ecommerce;
import java.sql.*;

public class DbConnection {
    // dburl is used to connnect the app to db
    //jdbc is the connector name and next database name , url name "//localhost:3306"
    private final String dbUrl = "jdbc:mysql://localhost:3306/ecommerce";
    private final String userName = "root";
    private final String password = "MSgiri0704@-_";
    private Statement getStatement()
    {
        try
        {
            Connection connection = DriverManager.getConnection(dbUrl,userName,password);
            return connection.createStatement();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getQueryTable(String query)
    {
        try
        {
            Statement statement = getStatement();
            return statement.executeQuery(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public int updateDataBase(String query)
    {
        try
        {
            Statement statement = getStatement();
            return statement.executeUpdate(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DbConnection conn = new DbConnection();
        ResultSet rs = conn.getQueryTable("select * from customers");
        if(rs!=null)
        {
            System.out.println("Connection Successful");
        }
        else {
            System.out.println("Connection Failed");
        }
    }
}
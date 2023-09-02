package com.example.ecommerce;

import java.sql.ResultSet;

public class NewUser {
    public static boolean getNewUser(String name,String email,String password,String mobile){
        DbConnection dBconnection=new DbConnection();
        String placeOrder = "insert into customers(name,email,password,mobile) values('" + name + "','" + email + "','" + password + "','" + mobile + "')";
        return dBconnection.updateDataBase(placeOrder) != 0;
}
}

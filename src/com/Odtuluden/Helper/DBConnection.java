package com.Odtuluden.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection createConnectionInstance(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(Constants.URL, Constants.DBUSER, Constants.DBPASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}

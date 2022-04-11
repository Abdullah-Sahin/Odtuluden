package com.Odtuluden.Helper;

import com.Odtuluden.GUI.CourseGUI;
import com.Odtuluden.Model.Course;
import com.Odtuluden.UserModel.User;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

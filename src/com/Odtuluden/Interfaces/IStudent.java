package com.Odtuluden.Interfaces;

import com.Odtuluden.Helper.Constants;
import com.Odtuluden.Helper.DBConnection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IStudent {

    static boolean studyCourseByName(String courseName){
        int success = -1;
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.STUDYCOURSEBYNAME);
            preparedStatement.setString(1,courseName);
            success = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success != -1;
    }

    static boolean studyCourseById(int id){
        int success = -1;
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.STUDYCOURSEBYID);
            preparedStatement.setInt(1, id);
            success = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success == 1;
    }
}

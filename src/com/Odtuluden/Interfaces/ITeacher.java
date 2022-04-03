package com.Odtuluden.Interfaces;

import com.Odtuluden.Helper.Constants;
import com.Odtuluden.Helper.DBConnection;
import com.Odtuluden.Model.Course;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ITeacher {

    static boolean addCourse(String courseName, String courseInfo){
        int success = -1;
        Course newCourse = new Course(courseName, courseInfo);
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.ADDCOURSE);
            preparedStatement.setString(1,courseName);
            preparedStatement.setString(2,courseInfo);
            success = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success != -1;
    }

    static boolean deleteCourseById(int id){
        int success = -1;
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.DELETECOURSEBYID);
            preparedStatement.setInt(1,id);
            success = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success != -1;
    }

    static boolean deleteCourseByName(String name){
        int success = -1;
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.DELETECOURSEBYNAME);
            preparedStatement.setString(1,name);
            success = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success != -1;
    }
}

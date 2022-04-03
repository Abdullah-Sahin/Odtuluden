package com.Odtuluden.Interfaces;

import com.Odtuluden.Helper.Constants;
import com.Odtuluden.Helper.DBConnection;
import com.Odtuluden.Helper.Functions;
import com.Odtuluden.UserModel.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface IAdmin {

    static boolean addUser(User user){
        int success = -1;
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.ADDUSER);
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3,user.getType());

            success = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Functions.showMessage("Ekleme yapılamadı");
            e.printStackTrace();
        }

        return success != -1;
    };
    static boolean deleteUserByName(String name){
        int success = -1;
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.DELETEUSERBYNAME);
            preparedStatement.setString(1,name);
            success = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Functions.showMessage("Silme yapılamadı");
            e.printStackTrace();
        }
        return success != -1;
    }

    static boolean deleteUserByEmail(String email){
        int success = -1;
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.DELETEUSERBYEMAIL);
            preparedStatement.setString(1,email);
            success = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Functions.showMessage("Silme yapılamadı");
            e.printStackTrace();
        }
        return success != -1;
    }

    static boolean deleteUserById(int id){
        int success = -1;
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.DELETEUSERBYID);
            preparedStatement.setInt(1,id);
            success = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Functions.showMessage("Silme yapılamadı");
            e.printStackTrace();
        }
        return success != -1;
    }

    static boolean updateUserByName(String name){
        int success = -1;
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.UPDATEUSERBYNAME);
            preparedStatement.setString(1,name);
            success = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Functions.showMessage("Güncelleme yapılamadı");
            e.printStackTrace();
        }
        return success != -1;
    }

    static boolean updateUserById(int id){
        int success = -1;
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.UPDATEUSERBYID);
            preparedStatement.setInt(1,id);
            success = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Functions.showMessage("Güncelleme yapılamadı");
            e.printStackTrace();
        }
        return success != -1;
    }

    static boolean updateUserByEmail(String email){
        int success = -1;
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.UPDATEUSERBYEMAIL);
            preparedStatement.setString(1,email);
            success = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Functions.showMessage("Güncelleme yapılamadı");
            e.printStackTrace();
        }
        return success != -1;
    }
    static boolean getUserByName(String name){
        int success = -1;
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.GETUSERBYNAME);
            preparedStatement.setString(1,name);
            success = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Functions.showMessage("Kullanıcı Getirilemedi");
            e.printStackTrace();
        }
        return success != -1;
    }
    static boolean getUserByEmail(String email){
        int success = -1;
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.GETUSERBYEMAIL);
            preparedStatement.setString(1,email);
            success = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Functions.showMessage("Kullanıcı Getirilemedi");
            e.printStackTrace();
        }
        return success != -1;
    }
    static boolean getUserById(int id){
        int success = -1;
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.GETUSERBYID);
            preparedStatement.setInt(1,id);
            success = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Functions.showMessage("Kullanıcı Getirilemedi");
            e.printStackTrace();
        }
        return success != -1;
    }

    static List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        User user = new User();
        try {
            Statement statement = DBConnection.createConnectionInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(Constants.GETALLUSERS);
            while(resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setFullName(resultSet.getString("fullname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setType(resultSet.getString("usertype"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}

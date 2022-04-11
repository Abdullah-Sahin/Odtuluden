package com.Odtuluden.UserModel;

import com.Odtuluden.Helper.Constants;
import com.Odtuluden.Helper.DBConnection;
import com.Odtuluden.Helper.Functions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User{

    public Admin(String fullName, String email, String password) {
        super(fullName, email, password);
        setType("yönetici");
    }

    public Admin(int id, String fullName, String email, String password) {
        super(id, fullName, email, password);
        setType("yönetici");
    }

    // ADMIN FUNCTIONS STARTED

    public static List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try {
            Statement statement = DBConnection.createConnectionInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(Constants.GETALLUSERS);
            while(resultSet.next()){
                User user = new User();
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

    public static User getUserById(int id){
        User foundUser = null;
        for(User user: getAllUsers()){
            if(user.getId() == id){
                String userType = user.getType().toLowerCase();
                switch (userType) {
                    case "öğrenci" -> foundUser = new Student(user.getId(), user.getFullName(), user.getEmail(), user.getPassword());
                    case "öğretmen" -> foundUser = new Teacher(user.getId(), user.getFullName(), user.getEmail(), user.getPassword());
                    case "yönetici" -> foundUser = new Admin(user.getId(), user.getFullName(), user.getEmail(), user.getPassword());
                }
            }
        }
        return foundUser;
    }

    public static User getUserByEmail(String email){

        for(User user : getAllUsers()){
            if(user.getEmail().equals(email)){
                String userType = user.getType().toLowerCase();
                switch(userType){
                    case("yönetici") -> user = new Admin(user.getId(), user.getFullName(), user.getEmail(),user.getPassword());
                    case("öğretmen") -> user = new Teacher(user.getId(),user.getFullName(), user.getEmail(),user.getPassword());
                    case("öğrenci") -> user = new Student(user.getId(), user.getFullName(), user.getEmail(),user.getPassword());
                }
                return user;
            }
        }
        return null;
    }

    public static void addUser(User user){
        if(exitsUserByMail(user.getEmail())){
            Functions.showMessage("Bu mail başka bir kullanıcıya ait");
        }
        else {
            try {
                PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.ADDUSER);
                preparedStatement.setString(1, user.getFullName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setString(4, user.getType());
                preparedStatement.execute();
                preparedStatement.close();
                Functions.showMessage("Kullanıcı başarıyla eklendi");
            } catch (SQLException e) {
                Functions.showMessage("Ekleme yapılamadı");
                e.printStackTrace();
            }
            int userId = getUserByEmail(user.getEmail()).getId();
            System.out.println(userId);
            String userType = user.getType().toLowerCase();
            switch (userType) {
                case "öğrenci" -> addStudent(new Student(userId, user.getFullName(),user.getEmail(),user.getPassword()));
                case "öğretmen" -> addTeacher(new Teacher(userId, user.getFullName(),user.getEmail(),user.getPassword()));
            }
        }
    }

    public static void addStudent(Student student){
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.ADDSTUDENT);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getFullName());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getPassword());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            Functions.showMessage("Öğrenci eklemesi yapılamadı");
            e.printStackTrace();
        }
    }

    public static void addTeacher(Teacher teacher){
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.ADDTEACHER);
            preparedStatement.setInt(1, teacher.getId());
            preparedStatement.setString(2, teacher.getFullName());
            preparedStatement.setString(3, teacher.getEmail());
            preparedStatement.setString(4, teacher.getPassword());
            preparedStatement.setString(5, Teacher.getCoursesByTeacherId(teacher.getId()).toString());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            Functions.showMessage("Öğretmen eklemesi yapılamadı");
            e.printStackTrace();
        }
    }

    public static void deleteUserByName(String name){
        for(User user: getAllUsers()){
            if(user.getFullName().equals(name)){
                try {
                    PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.DELETEUSERBYNAME);
                    preparedStatement.setString(1,name);
                    preparedStatement.execute();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void updateUserPasswordById(int id, String newPassword){

        String userType = getUserById(id).getType().toLowerCase();
        switch (userType){
            case "öğretmen" -> updateTeacherPasswordById(id, newPassword);
            case "öğrenci" -> updateStudentPasswordById(id, newPassword);
        }
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.UPDATEUSERPASSWORDBYID);
            preparedStatement.setString(1,newPassword);
            preparedStatement.setInt(2,id);
            preparedStatement.execute();
            preparedStatement.close();
            Functions.showMessage("Parola başarıyla değiştirildi");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateTeacherPasswordById(int id, String newPassword){

        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.UPDATETEACHERPASSWORDBYID);
            preparedStatement.setString(1,newPassword);
            preparedStatement.setInt(2,id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateStudentPasswordById(int id, String newPassword){

        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.UPDATESTUDENTPASSWORDBYID);
            preparedStatement.setString(1,newPassword);
            preparedStatement.setInt(2,id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean exitsUserByMail(String email){
        for(User user:getAllUsers()){
            if(user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

}

package com.Odtuluden.UserModel;

import com.Odtuluden.Helper.Constants;
import com.Odtuluden.Helper.DBConnection;
import com.Odtuluden.Helper.Functions;
import com.Odtuluden.Model.Course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Teacher extends User{

    private List<Course> courses;

    public Teacher(String fullName, String email, String password) {
        super(fullName, email, password);
        setType("öğretmen");
    }

    public Teacher(int id, String fullName, String email, String password) {
        super(id, fullName, email, password);
        setType("öğretmen");
    }

    //TEACHER FUNCTIONS STARTED

    public static List<Course> getCoursesByTeacherId(int id){
        List<Course> myCourses = new ArrayList<>();
        for(Course course: getAllCourses()){
            if(course.getTeacherId() == id){
                myCourses.add(course);
            }
        }
        return myCourses;
    }

    public static List<String> getCourseNamesByTeacherId(int id){
        List<String> names = new ArrayList<>();
        for(Course course: getCoursesByTeacherId(id)){
            names.add(course.getCourseName());
        }
        return names;
    }

    public static List<Course> getAllCourses(){
        List<Course> courses = new ArrayList<>();
        try {
            Statement statement = DBConnection.createConnectionInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(Constants.GETALLCOURSES);
            while(resultSet.next()){
                Course course = new Course();
                course.setId(resultSet.getInt("courseid"));
                course.setCourseName(resultSet.getString("coursename"));
                course.setCourseContent(resultSet.getString("coursecontent"));
                course.setTeacherId(resultSet.getInt("teacherid"));
                courses.add(course);
            }
        } catch (SQLException e) {
            Functions.showMessage("Ders eklenmedi");
            e.printStackTrace();
        }
        return courses;
    }

    public static Course getCourseById(int id){
        for(Course course: getAllCourses()){
            if(course.getId() == id){
                return course;
            }
        }
        return null;
    }

    public static boolean existsCourseByName(String courseName){
        for(Course course: getAllCourses()){
            if(course.getCourseName().equals(courseName)){
                return true;
            }
        }
        return false;
    }

    public static void addCourse(String courseName, String courseContent, int teacherid){

        if(existsCourseByName(courseName)){
            Functions.showMessage("Bu isimde bir ders zaten bulunmaktadır");
        }
        else{
            try {
                PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.ADDCOURSE);
                preparedStatement.setString(1,courseName);
                preparedStatement.setString(2, courseContent);
                preparedStatement.setInt(3, teacherid);
                preparedStatement.execute();
                updateCourses(teacherid);
                Functions.showMessage("Ders başarıyla eklendi");
            } catch (SQLException e) {
                Functions.showMessage("Ekleme yapılamadı");
                e.printStackTrace();
            }
        }
    }

    public static void updateCourses(int teacherid){
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.UPDATETEACHERCOURSEBYID);
            preparedStatement.setString(1, getCourseNamesByTeacherId(teacherid).toString());
            preparedStatement.setInt(2, teacherid);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editCourseById(int courseId, int teacherId, String editedContent){
        Course course = getCourseById(courseId);
        if(course.getTeacherId() == teacherId)
        try {
            PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.EDITCOURSEBYID);
            preparedStatement.setString(1,editedContent);
            preparedStatement.setInt(2,courseId);
            preparedStatement.execute();
            Functions.showMessage("Ders başarıyla güncellendi");
            preparedStatement.close();
        } catch (SQLException e) {
            Functions.showMessage("Ders düzenlenemedi");
            e.printStackTrace();
        }
    }


    public static void deleteCourseById(int teacherId, int courseId){
        System.out.println(courseId);
        Course course = getCourseById(courseId);
        if(course.getTeacherId() == teacherId){
            try {
                PreparedStatement preparedStatement = DBConnection.createConnectionInstance().prepareStatement(Constants.DELETECOURSEBYID);
                preparedStatement.setInt(1, courseId);
                preparedStatement.execute();
                preparedStatement.close();
                updateCourses(teacherId);
                Functions.showMessage("Ders başarıyla silindi");
            } catch (SQLException e) {
                Functions.showMessage("Ders silinemedi");
                e.printStackTrace();
            }
        }
        else {
            Functions.showMessage("Bu ders üzerinde işlem yapma hakkınız yoktur. ID: " + teacherId);
        }
    }
}

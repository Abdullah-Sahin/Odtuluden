package com.Odtuluden.UserModel;

import com.Odtuluden.Model.Course;

import java.util.List;

public class Student extends User{

    public Student(String fullName, String email, String password) {
        super(fullName, email, password);
        setType("öğrenci");
    }

    public Student(int id, String fullName, String email, String password) {
        super(id, fullName, email, password);
        setType("öğrenci");
    }

    // STUDENT METHODS STARTED

    public static String studyCourseByName(String courseName) {

        for(Course course: Teacher.getAllCourses()){
            if(course.getCourseName().equals(courseName)){
                return course.getCourseContent();
            }
        }
        return null;
    }
}

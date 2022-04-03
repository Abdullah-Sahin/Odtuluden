package com.Odtuluden.Model;


public class Course {

    private int id;
    private String courseName;
    private String courseInfo;


    public Course(String courseName, String courseInfo) {
        this.courseName = courseName;
        this.courseInfo = courseInfo;
    }

    public String getCourseName() {
        return courseName;
    }


    public String getCourseInfo() {
        return courseInfo;
    }

    public int getId() {
        return id;
    }
}

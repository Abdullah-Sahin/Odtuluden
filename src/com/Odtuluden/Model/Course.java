package com.Odtuluden.Model;

public class Course {

    private int id;
    private int teacherId;
    private String courseName;
    private String courseContent;

    public Course() {

    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public Course(String courseName, String courseContent, int teacherId) {
        this.courseName = courseName;
        this.courseContent = courseContent;
        this.teacherId = teacherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}

package com.Odtuluden.Helper;

public class Constants {

    public static final String URL = "jdbc:mysql://localhost/Odtuluden";
    public static final String DBUSER = "root";
    public static final String DBPASSWORD = "0000";

    public static final String GETALLUSERS = "select * from Users";
    public static final String GETUSERBYNAME = "select * from Users where name = ?";
    public static final String GETUSERBYID = "select * from Users where id = ?";
    public static final String GETUSERBYEMAIL = "select * from Users where email = ?";
    public static final String DELETEUSERBYNAME = "delete from Users where name = ?";
    public static final String DELETEUSERBYID = "delete from Users where id = ?";
    public static final String DELETEUSERBYEMAIL = "delete from Users where email = ?";
    public static final String UPDATEUSERBYNAME = "set * from Users where name = ?";
    public static final String UPDATEUSERBYID = "set * from Users where name = ?";
    public static final String UPDATEUSERBYEMAIL = "set * from Users where name = ?";
    public static final String ADDUSER = "Insert into Users (fullname, email, password, usertype) VALUES" +
            " (?,?,?,?)";

    public static final String ADDCOURSE = "Insert into Courses (coursename, courseinfo) " +
            "Values = (?,?)";

    public static final String DELETECOURSEBYID = "delete from courses where courseid = ?";

    public static final String DELETECOURSEBYNAME = "delete from courses where coursename = ?";

    public static final String STUDYCOURSEBYNAME = "select courseinfo courses where coursename = ?";

    public static final String STUDYCOURSEBYID = "select courseinfo from courses where courseid = ?";

}

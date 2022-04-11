package com.Odtuluden.Helper;

public class Constants {

    public static final String URL = "jdbc:mysql://localhost/Odtuluden";
    public static final String DBUSER = "root";
    public static final String DBPASSWORD = "0000";

    public static final String ADDUSER = "INSERT INTO `users` (`fullname`, `email`, `password`, `usertype`) VALUES (?,?,?,?)";
    public static final String ADDTEACHER = "INSERT INTO `teachers` (`id`, `fullname`, `email`, `password`, `courses`) VALUES (?,?,?,?,?)";
    public static final String ADDSTUDENT = "INSERT INTO `students` (`id`, `fullname`, `email`, `password`) VALUES (?,?,?,?)";
    public static final String GETALLUSERS = "select * from Users";
    public static final String DELETEUSERBYNAME = "delete from Users where fullname = ?";
    public static final String UPDATEUSERPASSWORDBYID = "Update users set password = ? where id = ?";
    public static final String UPDATETEACHERPASSWORDBYID = "Update teachers set password = ? where id = ?";
    public static final String UPDATESTUDENTPASSWORDBYID = "Update students set password = ? where id = ?";

    public static final String ADDCOURSE = "Insert into Courses (`coursename`, `coursecontent`, `teacherid` ) Values  (?,?,?)";
    public static final String GETALLCOURSES = "select * from courses";
    public static final String DELETECOURSEBYID = "delete from courses where courseid = ?";
    public static final String EDITCOURSEBYID = "Update courses Set coursecontent = ? where courseid = ?";

    public static final String UPDATETEACHERCOURSEBYID = "Update teachers Set courses = ? where id = ?";

}

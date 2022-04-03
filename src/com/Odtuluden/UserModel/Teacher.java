package com.Odtuluden.UserModel;

import com.Odtuluden.Interfaces.ITeacher;

public class Teacher extends User implements ITeacher {

    public Teacher(String fullName, String email, String password, String type) {
        super(fullName, email, password, type);
    }
}

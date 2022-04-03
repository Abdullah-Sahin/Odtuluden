package com.Odtuluden.UserModel;

import com.Odtuluden.Interfaces.IStudent;

public class Student extends User implements IStudent {

    public Student(String fullName, String email, String password, String type) {
        super(fullName, email, password, type);
    }
}

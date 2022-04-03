package com.Odtuluden.UserModel;

import com.Odtuluden.Interfaces.IAdmin;

public class Admin extends User implements IAdmin{


    public Admin(String fullName, String email, String password, String type) {
        super(fullName, email, password, type);
    }
}

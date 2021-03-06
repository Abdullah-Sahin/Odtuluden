package com.Odtuluden.UserModel;

public class User {

    private int id;
    private String fullName;
    private String email;
    private String password;
    private String type;

    public User(){

    }
    public User(int id, String fullName, String email, String password, String type) {
        this.id = id;
        this.fullName = fullName;
        this.email  = email;
        this.password = password;
        this.type = type;
    }

    public User(int id, String fullName, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email  = email;
        this.password = password;
        this.type = "Bilgi Yok";
    }

    public User(String fullName, String email, String password, String type) {
        this.fullName = fullName;
        this.email  = email;
        this.password = password;
        this.type = type;
    }
    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email  = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

package com.Odtuluden.GUI;

import com.Odtuluden.Helper.Functions;
import com.Odtuluden.UserModel.Admin;
import com.Odtuluden.UserModel.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateUserGUI extends JFrame {

    private User user;
    private JPanel panelMain;
    private JLabel labelUserInfo;
    private JLabel labelUserId;
    private JTextField textFieldUserId;
    private JTextField textFieldFullname;
    private JTextField textFieldEmail;
    private JTextField textFieldUserType;
    private JButton buttonSubmitChange;
    private JTextField textFieldPassword;

    public UpdateUserGUI(User user){
        Functions.setLayout();
        this.user = user;
        add(panelMain);

        textFieldUserId.setText(String.valueOf(user.getId()));
        textFieldFullname.setText(user.getFullName());
        textFieldEmail.setText(user.getEmail());
        textFieldUserType.setText(user.getType());
        loadNewPassword();

        setSize(500,400);
        setResizable(false);
        setLocation(Functions.midX(getWidth()), Functions.midY(getHeight()));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        buttonSubmitChange.addActionListener(e -> {
            if(!textFieldPassword.getText().trim().isEmpty()){
                Admin.updateUserPasswordById(user.getId(), textFieldPassword.getText());
                loadNewPassword();
                dispose();
            }
            else {
                Functions.showMessage("Parola alanı boş olamaz!");
            }

        });
    }

    public void loadNewPassword(){
        textFieldPassword.setText(Admin.getUserById(user.getId()).getPassword());
    }

}

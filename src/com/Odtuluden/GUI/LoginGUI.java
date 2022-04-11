package com.Odtuluden.GUI;

import com.Odtuluden.Helper.Functions;
import com.Odtuluden.UserModel.Admin;
import com.Odtuluden.UserModel.Student;
import com.Odtuluden.UserModel.Teacher;
import com.Odtuluden.UserModel.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginGUI extends JFrame{
    private JPanel panelMain;
    private JPanel panelWelcome;
    private JLabel labelWelcome;
    private JComboBox comboBoxUserType;
    private JLabel labelUserLogin;
    private JPanel panelLogin;
    private JLabel labelUseremail;
    private JTextField textFieldUseremail;
    private JLabel labelUserpassword;
    private JPasswordField passwordFieldUserpassword;
    private JButton buttonLogin;
    private JPanel panelRegistration;
    private JLabel labelRegistration;
    private JComboBox comboBoxRegistrationType;
    private JLabel labelRegistrationType;
    private JTextField textFieldRfullname;
    private JTextField textFieldRemail;
    private JPasswordField passwordFieldRpassword;
    private JLabel labelRfullname;
    private JLabel labelRemail;
    private JLabel labelRpassword;
    private JButton buttonRegister;

    public LoginGUI(){
        Functions.setLayout();
        add(panelMain);
        setSize(500,450);
        setResizable(false);
        setLocation(Functions.midX(getWidth()), Functions.midY(getHeight()));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        comboBoxRegistrationType.setSelectedIndex(1);
        comboBoxUserType.setSelectedIndex(2);


        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Checks if password is true
                if(Functions.checkPassword(textFieldUseremail.getText(), passwordFieldUserpassword.getText())){
                    User user = Admin.getUserByEmail(textFieldUseremail.getText());
                    // Checks if selected user type is not true
                    if(user.getType().equals(comboBoxUserType.getSelectedItem().toString().toLowerCase())){
                        String userType = user.getType().toLowerCase();
                        switch (userType) {
                            case "yönetici" -> {
                                new AdminGUI((Admin) user);
                                dispose();
                            }
                            case "öğretmen" -> {
                                new TeacherGUI((Teacher) user);
                                dispose();
                            }
                            case "öğrenci" -> {
                                new StudentGUI((Student) user);
                                dispose();
                            }
                        }
                    }
                    else{
                        Functions.showMessage("Kullanıcı türünü doğru seçtiğinizden emin olun");
                    }
                }
                else {
                    passwordFieldUserpassword.setText(null);
                }
            }
        });


        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldRfullname.getText().trim().isEmpty() || textFieldRemail.getText().trim().isEmpty() || passwordFieldRpassword.getText().trim().isEmpty()) {
                    Functions.showMessage("Tüm alanların doldurulması zorunludur");
                }
                else{
                    User newUser = new User(textFieldRfullname.getText(),
                                            textFieldRemail.getText(),passwordFieldRpassword.getText(),
                                            comboBoxRegistrationType.getSelectedItem().toString().toLowerCase());
                    Admin.addUser(newUser);
                }
            }
        });
    }
}

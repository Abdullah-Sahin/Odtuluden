package com.Odtuluden.GUI;

import com.Odtuluden.Helper.Functions;

import javax.swing.*;

public class LoginGUI extends JFrame{
    private JPanel panelMain;
    private JPanel panelWelcome;
    private JLabel labelWelcome;
    private JComboBox comboBox1;
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
    }
}

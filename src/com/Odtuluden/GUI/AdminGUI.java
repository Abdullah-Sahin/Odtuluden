package com.Odtuluden.GUI;

import com.Odtuluden.Helper.Functions;
import com.Odtuluden.Interfaces.IAdmin;
import com.Odtuluden.UserModel.Admin;
import com.Odtuluden.UserModel.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminGUI extends JFrame{
    private Admin admin;
    private JPanel panelMain;
    private JPanel panelTop;
    private JLabel labelWelcome;
    private JButton buttonLogout;
    private JTabbedPane tabbedPane1;
    private JTable tableUsers;
    private JPanel panelUsers;
    private JPanel panelAdminControls;
    private JLabel labelFullname;
    private JTextField textFieldFullname;
    private JLabel labelEmail;
    private JTextField textFieldEmail;
    private JLabel labelPassword;
    private JPasswordField passwordFieldpassword;
    private JComboBox comboBox1;
    private JButton buttonRegister;
    private JLabel labelId;
    private JTextField textFieldId;
    private JButton ButtonDelete;
    private JLabel labelAddUser;
    private JLabel labelUserType;
    private DefaultTableModel modelUsers;
    private Object[] cols;

    public AdminGUI(Admin admin) {
        this.admin = admin;
        add(panelMain);
        labelWelcome.setText("Hoşgeldiniz " + admin.getFullName());

        modelUsers = new DefaultTableModel();
        cols = new Object[]{"id", "Ad soyad", "E-mail", "Şifre", "Kullanıcı Türü"};
        modelUsers.setColumnIdentifiers(cols);
        for (User user: IAdmin.getAllUsers()){
            Object[] row = {user.getId(), user.getFullName(), user.getEmail(), user.getPassword(), user.getType()};
            modelUsers.addRow(row);
        }
        tableUsers.setModel(modelUsers);
        tableUsers.getTableHeader().setReorderingAllowed(false);


        setSize(1100,650);
        setLocation(Functions.midX(getWidth()), Functions.midY(getHeight()));
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

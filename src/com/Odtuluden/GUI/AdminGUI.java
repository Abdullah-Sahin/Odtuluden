package com.Odtuluden.GUI;

import com.Odtuluden.Helper.Functions;
import com.Odtuluden.UserModel.Admin;
import com.Odtuluden.UserModel.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JComboBox comboBoxUserType;
    private JButton buttonRegister;
    private JLabel labelId;
    private JTextField textFieldId;
    private JLabel labelAddUser;
    private JLabel labelUserType;
    private JRadioButton RbuttonDelete;
    private JButton buttonSubmit;
    private JRadioButton RButtonUpdate;
    private JLabel labelChosenUserEmail;
    private JButton buttonRefreshTable;
    private JButton buttonUserSettings;
    private DefaultTableModel modelUsers;

    public AdminGUI(Admin admin) {

        this.admin = admin;
        add(panelMain);
        labelWelcome.setText("Hoşgeldiniz " + admin.getFullName());

        modelUsers = new DefaultTableModel();
        modelUsers.setColumnIdentifiers(new Object[]{"id", "Ad soyad", "E-mail", "Şifre", "Kullanıcı Türü"});
        setModelUsers();
        tableUsers.setModel(modelUsers);
        tableUsers.getTableHeader().setReorderingAllowed(false);


        setSize(1100,650);
        setLocation(Functions.midX(getWidth()), Functions.midY(getHeight()));
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        buttonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginGUI();
                dispose();
            }
        });
        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setFullName(textFieldFullname.getText());
                user.setEmail(textFieldEmail.getText());
                user.setPassword(passwordFieldpassword.getText());
                user.setType(comboBoxUserType.getSelectedItem().toString());
                Admin.addUser(user);
                setModelUsers();
            }
        });
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tableUsers.getSelectedRow()==-1){
                    Functions.showMessage("Lütfen satırı seçiniz");
                }
                else if(RbuttonDelete.isSelected()){
                    if(admin.getEmail().equals(tableUsers.getModel().getValueAt(tableUsers.getSelectedRow(), 2).toString())){
                        Functions.showMessage("Kendinizi silemezsiniz");
                    }
                    else{
                        Admin.deleteUserByName(tableUsers.getModel().getValueAt(tableUsers.getSelectedRow(), 1).toString());
                        textFieldId.setText(null);
                    }
                }
                else if(RButtonUpdate.isSelected()){
                    int id = Integer.parseInt(tableUsers.getValueAt(tableUsers.getSelectedRow(), 0).toString());
                    User user = Admin.getUserById(id);
                    new UpdateUserGUI(user);
                }
                else{
                    Functions.showMessage("Geçerli seçimleri yaptığınızdan emin olun!");
                }
                setModelUsers();
            }
        });

        tableUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try{
                    textFieldId.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 0).toString());
                }catch (Exception exception){

                }

            }
        });
        buttonRefreshTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setModelUsers();
            }
        });
        buttonUserSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(admin.getId());
                new UpdateUserGUI(admin);
            }
        });
    }

    public void setModelUsers(){
        DefaultTableModel model = modelUsers;
        model.setRowCount(0);
        Object[] row = new Object[5];
        for(User user: Admin.getAllUsers()){
            int i = 0;
            row[i++] = user.getId();
            row[i++] = user.getFullName();
            row[i++] = user.getEmail();
            row[i++] = user.getPassword();
            row[i++] = user.getType();
            modelUsers.addRow(row);
        }
    }
}

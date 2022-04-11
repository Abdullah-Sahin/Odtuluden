package com.Odtuluden.Helper;

import com.Odtuluden.UserModel.Admin;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Functions {

    public static void showMessage(String message){
        JOptionPane.showMessageDialog(null,message);
    }

    public static int midX(int frameWidth){
        return (Toolkit.getDefaultToolkit().getScreenSize().width -frameWidth) / 2;
    }

    public static int midY(int frameHeight){
        return (Toolkit.getDefaultToolkit().getScreenSize().height -frameHeight)/2;
    }

    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
            if(info.getClassName().equals("Nimbus")){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static boolean checkPassword(String eMail, String pass){

        if(Admin.getUserByEmail(eMail) == null){
            Functions.showMessage("Bu mail adresine ait bir kullanıcı bulunmamaktadır");
            return false;
        }
        else{
            if(Objects.requireNonNull(Admin.getUserByEmail(eMail)).getPassword().equals(pass))
                return true;
            else{
                showMessage("Şifre yanlış");
                return false;
            }
        }

    }
}

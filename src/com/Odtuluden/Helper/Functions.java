package com.Odtuluden.Helper;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;

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
}

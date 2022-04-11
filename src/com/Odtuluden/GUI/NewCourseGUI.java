package com.Odtuluden.GUI;

import com.Odtuluden.Helper.Functions;
import com.Odtuluden.UserModel.Teacher;

import javax.swing.*;

public class NewCourseGUI extends JFrame{
    private JPanel panelMain;
    private JPanel panelTop;
    private JButton buttonExit;
    private JLabel labelWelcome;
    private JPanel panelCourse;
    private JPanel panelCourseName;
    private JLabel labelCourseName;
    private JTextField textFieldCourseName;
    private JButton buttonAddCourse;
    private JLabel labelCourseContent;
    private JTextArea textAreaContent;
    private JPanel panelCourseContent;
    private JPanel panelContentArea;

    public NewCourseGUI(Teacher teacher) {

        Functions.setLayout();

        add(panelMain);


        setSize(800,600);
        setResizable(false);
        setLocation(Functions.midX(getWidth()), Functions.midY(getHeight()));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        buttonExit.addActionListener(e -> dispose());
        buttonAddCourse.addActionListener(e -> Teacher.addCourse(textFieldCourseName.getText(), textAreaContent.getText(), teacher.getId()));
    }
}

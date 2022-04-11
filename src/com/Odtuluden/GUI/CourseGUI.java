package com.Odtuluden.GUI;

import com.Odtuluden.Helper.Functions;
import com.Odtuluden.Model.Course;

import javax.swing.*;


public class CourseGUI extends JFrame{
    private JPanel panelMain;
    private JTabbedPane tabbedPaneCourseName;
    private JLabel labelCourseContent;
    private JPanel panelTop;
    private JLabel labelWelcome;
    private JButton buttonLogout;

    public CourseGUI(Course course){

        Functions.setLayout();
        add(panelMain);
        setTitle(course.getCourseName());
        setSize(800,600);
        setLocation(Functions.midX(getWidth()), Functions.midY(getHeight()));
        labelWelcome.setText(course.getCourseName() + " dersine hoşgeldiniz");

        labelCourseContent.setText(course.getCourseContent());

        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        buttonLogout.addActionListener(e -> dispose());
    }
}

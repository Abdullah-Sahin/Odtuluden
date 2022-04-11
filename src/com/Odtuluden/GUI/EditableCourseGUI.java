package com.Odtuluden.GUI;

import com.Odtuluden.Helper.Functions;
import com.Odtuluden.Model.Course;
import com.Odtuluden.UserModel.Teacher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditableCourseGUI extends JFrame{
    private Course course;
    private JPanel panelMain;
    private JPanel panelTop;
    private JPanel panelMid;
    private JPanel panelBottom;
    private JLabel labelCourseName;
    private JButton buttonExit;
    private JButton buttonCancelChanges;
    private JButton buttonSaveChanges;
    private JTextArea textAreaCourseContent;
    private JTabbedPane tabbedPaneCourseName;

    public EditableCourseGUI(Course course){
        Functions.setLayout();
        this.course = course;
        add(panelMain);

        labelCourseName.setText(course.getCourseName());

        textAreaCourseContent.setText(course.getCourseContent());

        setSize(1000,750);
        setResizable(false);
        setLocation(Functions.midX(getWidth()), Functions.midY(getHeight()));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonCancelChanges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaCourseContent.setText(course.getCourseContent());
            }
        });
        buttonSaveChanges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newContent = textAreaCourseContent.getText();
                Teacher.editCourseById(course.getId(), course.getTeacherId(), newContent);
                Functions.showMessage("Değişiklikler Kaydedildi");
            }
        });
    }
}

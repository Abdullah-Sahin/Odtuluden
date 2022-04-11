package com.Odtuluden.GUI;

import com.Odtuluden.Helper.Functions;
import com.Odtuluden.Model.Course;
import com.Odtuluden.UserModel.Student;
import com.Odtuluden.UserModel.Teacher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGUI extends JFrame{

    private Student student;
    private JPanel panelMain;
    private JPanel panelTop;
    private JLabel labelWelcome;
    private JButton buttonLogout;
    private JTable tableCourses;
    private JPanel panelBottom;
    private JButton buttonStudy;
    private JTabbedPane tabbedPane;
    private JPanel panelCourses;
    private JButton buttonUserSettings;
    private DefaultTableModel tableModel;

    public StudentGUI(Student student) {

        Functions.setLayout();

        this.student = student;
        add(panelMain);
        setSize(800,600);
        setResizable(false);
        setLocation(Functions.midX(getWidth()), Functions.midY(getHeight()));
        labelWelcome.setText("Hoşgeldiniz " + student.getFullName());


        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Course ID", "Course Name", "Teacher Id"});
        setTableModel();
        tableCourses.setModel(tableModel);
        tableCourses.getTableHeader().setReorderingAllowed(false);





        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        buttonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginGUI();
                dispose();
            }
        });
        buttonStudy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int courseId = Integer.parseInt(tableCourses.getValueAt(tableCourses.getSelectedRow(), 0).toString());
                Course course = Teacher.getCourseById(courseId);
                new CourseGUI(course);
            }
        });
        buttonUserSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateUserGUI(student);
            }
        });
    }

    public void setTableModel(){
        DefaultTableModel model = (DefaultTableModel) tableModel;
        model.setRowCount(0);
        for(Course course: Teacher.getAllCourses()){
            Object[] row = {course.getId(),course.getCourseName(), course.getTeacherId()};
            tableModel.addRow(row);
        }
    }

}

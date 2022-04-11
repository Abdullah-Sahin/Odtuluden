package com.Odtuluden.GUI;

import com.Odtuluden.Helper.Functions;
import com.Odtuluden.Model.Course;
import com.Odtuluden.UserModel.Teacher;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherGUI extends JFrame{
    private Teacher teacher;
    private JPanel panelMain;
    private JPanel panelWelcome;
    private JLabel labelWelcome;
    private JButton buttonLogout;
    private JTabbedPane tabbedPane1;
    private JTable tableAllCourses;
    private JPanel panelAllCourses;
    private JTextField textFieldCourseName;
    private JButton buttonAddCourse;
    private JLabel labelCourseSelected;
    private JButton ButtonSubmit;
    private JButton buttonUpdateList;
    private JButton buttonUserSettings;
    private JTable tableMyCourses;
    private JPanel panelCourseManagement;
    private JComboBox comboBox1;
    private JButton onaylaSubmit;
    private JLabel labelChosenCourseName;
    private JPanel panelMyCourses;
    private JButton buttonLook;
    private JLabel labelSelectedCourseInfo;
    private JButton ButtonUpdateAllCourses;
    private DefaultTableModel tableModel;
    private DefaultTableModel myCoursesModel;

    public TeacherGUI(Teacher teacher){

        this.teacher = teacher;
        Functions.setLayout();

        add(panelMain);

        labelWelcome.setText("Hoşgeldiniz " + teacher.getFullName());


        Object[] rowAll = new Object[]{"Course Id", "Course Name", "Teacher Id"}; // column identifiers
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(rowAll);
        setTableModel();
        tableAllCourses.setModel(tableModel);
        tableAllCourses.getColumnModel().getColumn(0).setMaxWidth(75);
        tableAllCourses.getColumnModel().getColumn(2).setMaxWidth(75);
        tableAllCourses.getTableHeader().setReorderingAllowed(false);

        Object[] row = new Object[]{"Course Id", "Course Name"}; // column identifiers
        myCoursesModel = new DefaultTableModel();
        myCoursesModel.setColumnIdentifiers(row);
        setMyCoursesModel();
        tableMyCourses.setModel(myCoursesModel);
        tableMyCourses.getColumnModel().getColumn(0).setMaxWidth(75);
        tableMyCourses.getTableHeader().setReorderingAllowed(false);

        setSize(800,600);
        setLocation(Functions.midX(getWidth()),Functions.midY(getHeight()));
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
        buttonUserSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateUserGUI(teacher);
            }
        });

        tableAllCourses.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try{
                    labelSelectedCourseInfo.setText("Seçilen Kurs Adı: " + tableAllCourses.getValueAt(tableAllCourses.getSelectedRow(), 1));
                }catch (Exception exception){

                }

            }
        });
        buttonUpdateList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMyCoursesModel();
            }
        });
        buttonAddCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewCourseGUI newCourseGUI = new NewCourseGUI(teacher);
            }
        });
        onaylaSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getSelectedItem().toString().toLowerCase().equals("düzenle")){
                    Course course = Teacher.getCourseById(Integer.parseInt(tableMyCourses.getValueAt(tableAllCourses.getSelectedRow(), 0).toString()));
                    new EditableCourseGUI(course);
                }else if(comboBox1.getSelectedItem().toString().toLowerCase().equals("sil")){
                    int courseId = Integer.parseInt(tableMyCourses.getValueAt(tableMyCourses.getSelectedRow(), 0).toString());
                    Teacher.deleteCourseById(teacher.getId(), courseId);
                }
                else{
                    Functions.showMessage("Lütfen tüm seçimleri yaptığınızdan emin olun.");
                }
                setTableModel();
                setMyCoursesModel();
            }
        });

        tableMyCourses.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try{
                    labelChosenCourseName.setText("Seçilen Kurs : " + tableMyCourses.getValueAt(tableMyCourses.getSelectedRow(), 1).toString());
                }catch (Exception exception){

                }

            }
        });
        ButtonUpdateAllCourses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTableModel();
            }
        });
    }


    //Functions outside constructor

    public void setTableModel(){

        DefaultTableModel model = (DefaultTableModel) tableModel;
        model.setRowCount(0);
        Object[] row = new Object[3];
        for(Course course: Teacher.getAllCourses()){
            row[0] = course.getId();
            row[1] = course.getCourseName();
            row[2] = course.getTeacherId();
            tableModel.addRow(row);
        }

    }

    public void setMyCoursesModel(){
        DefaultTableModel model = (DefaultTableModel) myCoursesModel;
        model.setRowCount(0);
        Object[] row = new Object[2];
        for(Course course: Teacher.getCoursesByTeacherId(teacher.getId())){
            row[0] = course.getId();
            row[1] = course.getCourseName();
            myCoursesModel.addRow(row);
        }
    }

}

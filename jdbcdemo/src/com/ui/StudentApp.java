package com.ui;

import com.dao.StudentDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.dao.StudentDAO;
import com.core.Student;

import java.util.List;

/**
 * Created by Andrei on 14-Mar-16.
 */
public class StudentApp extends JFrame {



    private JPanel StudentApp;
    private JTextField nameTextField;
    private JButton searchButton;
    private JTable table1;

    private StudentDAO studentDao;

    public StudentApp() {
        //create DAO
        try{
            studentDao = new StudentDAO();
        }catch(Exception exc){

            JOptionPane.showMessageDialog(this, "Error: "+ exc,"Error", JOptionPane.ERROR_MESSAGE);
        }


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get name from text field

                //call DAO and get student for name

                //if name is empty then get all

                //print out
                try {
                    String name = nameTextField.getText();

                    List<Student> students = null;

                    if (name != null && name.trim().length() > 0) {
                        //students = studentDao.searchStudents(name);
                    } else {
                        students = studentDao.getAllStudents();
                    }


					for (Student temp : students) {
						System.out.println(temp);
					}

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(StudentApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

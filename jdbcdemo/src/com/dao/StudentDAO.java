package com.dao;
import com.core.*;

import java.sql.*;
import java.io.*;
import java.util.List;
import java.util.*;

/**
 * Created by Andrei on 14-Mar-16.
 */
public class StudentDAO {
    private Connection myConn;

    public StudentDAO() throws Exception{

        //get db properties
        Properties props = new Properties();
        props.load(new FileInputStream("access.properties"));
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String dburl = props.getProperty("dburl");

        //database connection
        myConn = DriverManager.getConnection(dburl,user,password);

        System.out.println("DB Connection to " + dburl + "successful");
    }

    //methods.
    public List<Student> getAllStudents() throws Exception {
        List<Student> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT * FROM students");

            while (myRs.next()) {
                Student tempStudent = convertRowToStudent(myRs);
                list.add(tempStudent);
            }

            return list;
        }
        finally {
            close(myStmt, myRs);
        }
    }

    private Student convertRowToStudent(ResultSet myRs) throws SQLException {

        int id = myRs.getInt("idstudents");
        String name = myRs.getString("name");
        String birthday = myRs.getString("birthdate");
        String address = myRs.getString("address");


        Student tempStudent = new Student(id, name, birthday, address);

        return tempStudent;
    }

    public void insertStudent() throws SQLException{
        ResultSet myRs = null;

        Statement myStmt = null;


        try{
            myStmt = myConn.createStatement();
            //INSERT
            String sql = "insert into students"
                    + "(name, birthdate, address)"
                    + "values ('bam', '1995', 'ruble@foo.com')";

            myStmt.executeUpdate(sql);
            System.out.println("Insert complete");
        }finally {
            close(myStmt,myRs);
        }
    }

    public void enrollStudent() throws SQLException{
        ResultSet myRs = null;

        Statement myStmt = null;


        try{
            myStmt = myConn.createStatement();
            //Enroll
            String sql = "insert into enrollment"
                    + "(year, idstudent, idcourse)"
                    + "values (2,1, 1)";

            myStmt.executeUpdate(sql);
            System.out.println("Enrollment complete");
        }finally {
            close(myStmt,myRs);
        }
    }

    public void updateStudent() throws SQLException{
        ResultSet myRs = null;

        Statement myStmt = null;


        try{
            myStmt = myConn.createStatement();
    //UPDATE
            String sql = "UPDATE students "
                    + " set address = 'demo@luv.com'"
                    + " WHERE idstudents = 11";

            myStmt.executeUpdate(sql);

            System.out.println("Update complete");
        }finally {
            close(myStmt,myRs);
        }
    }

    public void deleteStudent() throws SQLException{
        ResultSet myRs = null;

        Statement myStmt = null;


        try{

            //DELETE
            String sql = "delete from students where name = 'bam'";
            int rowsAffected = myStmt.executeUpdate(sql);

            System.out.println("Rows affected: " + rowsAffected);
            System.out.println("Delete complete.");
        }finally {
            close(myStmt,myRs);
        }
    }


    private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {
        if(myRs != null)
            myRs.close();
        if (myStmt!=null)

        if(myConn != null)
            myRs.close();
    }

    private static void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null,myStmt,myRs);
    }

    public List<Student> searchStudents(String name) throws Exception {
        List<Student> list = new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            name += "%";
            myStmt = myConn.prepareStatement("select * from students where name like ?");

            myStmt.setString(1, name);

            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                Student tempStudent = convertRowToStudent(myRs);
                list.add(tempStudent);
            }

            return list;
        }
        finally {
            close(myStmt, myRs);
        }
    }

    public static void main(String[] args) throws Exception {

        StudentDAO dao = new StudentDAO();
        //dao.insertStudent();
        //dao.enrollStudent();
        dao.updateStudent();
        //dao.deleteStudent();

        System.out.println(dao.searchStudents("bam"));
        //System.out.println(dao.getAllStudents());
    }


}

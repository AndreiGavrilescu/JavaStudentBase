package jdbcDemo;

import java.sql.*;

/**
 * Created by Andrei on 13-Mar-16.
 */


public class Driver {
    public static void main(String[] args) throws SQLException {
        ResultSet myRs = null;
        Connection myConn= null;
        PreparedStatement myStmt = null;

        try {
            //1. get a connection
            String dbUrl = "jdbc:mysql://eu-cdbr-azure-west-c.cloudapp.net:3306/acsm_53451fbb8c7558f";
            String user = "bae48f804eaf2c";
            String pass = "518e4c38";

            myConn = DriverManager.getConnection(dbUrl,user,pass);
            //2. prepare a statement
            myStmt = myConn.prepareStatement("SELECT * FROM employees WHERE salary > ? and department = ?");

            //3. set the parameters
            myStmt.setDouble(1, 80000);
            myStmt.setString(2, "Legal");

            //4. execute sql query
            myRs= myStmt.executeQuery();

            //5. display the result set
            diplay(myRs);

            //reuse the prepared statement
            System.out.println("\n\nReuse the prepared statement: salary >25000, department = HR");

            //6 set the parameters
            myStmt.setDouble(1,25000);
            myStmt.setString(2,"HR");

            //7.executing sql query

            myRs = myStmt.executeQuery();

            diplay(myRs);

            //3. execute SQL query
            //INSERT
            /*String sql = "insert into employees"
                    + "(last_name, first_name, email)"
                    + "values ('bam', 'bam', 'ruble@foo.com')";

            myStmt.executeUpdate(sql);
            System.out.println("Insert complete");*/
            //UPDATE
            /*String sql = "UPDATE employees"
                    + " set email = 'demo@luv.com'"
                    + " WHERE id_employee = 21";

            myStmt.executeUpdate(sql);

            System.out.println("Update complete");*/

            //DELETE
            /*String sql = "delete from employees where last_name = 'bam'";
            int rowsAffected = myStmt.executeUpdate(sql);

            System.out.println("Rows affected: " + rowsAffected);
            System.out.println("Delete complete.");*/

            //clearDB autoincrements indexes with 10s instead of 1s
            //4. process the result set

        }
        catch (Exception exc){
            exc.printStackTrace();
        }
        finally{
            if(myStmt != null){
                myStmt.close();
            }
            if(myConn != null){
                myConn.close();
            }
        }

    }

    private static void diplay(ResultSet myRs) throws SQLException{
        while(myRs.next()){
            String lastName = myRs.getString("last_name");
            String firstName = myRs.getString("first_name");
            double salary = myRs.getDouble("salary");
            String department = myRs.getString("department");

            System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
        }
    }

}

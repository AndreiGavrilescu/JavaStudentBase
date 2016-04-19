package storedProcedures;
import java.sql.*;


/**
 * Created by Andrei on 14-Mar-16.
 */
public class SProcs {
    public static void main(String[] args) throws Exception{
        Connection myConn = null;
        CallableStatement myStmt = null;

        try {
            //get connection to DB
            myConn = DriverManager.getConnection("jdbc:mysql://eu-cdbr-azure-west-c.cloudapp.net:3306/acsm_53451fbb8c7558f",  //database url
                            "bae48f804eaf2c",                              //user
                            "518e4c38");                                    //pass
            String theDepartment = "Engineering";
            int theIncreaseAmount= 10000;

            //show salaries before
            System.out.println("Salaries BEFORE\n");
            
            showSalaries(myConn, theDepartment);

            //prepare the sored procedure call
            myStmt = myConn.prepareCall("{call increase_salaries_for_department(?, ?)}");

            //Set the parameters
            myStmt.setString(1,theDepartment);
            myStmt.setDouble(2,theIncreaseAmount);

            //Call the stored procedure
            System.out.println("\n\nCalling stored procedure. increase_salaries_for_department('"+ theDepartment + "', " + theIncreaseAmount+ ")");
            myStmt.execute();
            System.out.println("Finished calling stored proc");

            //Show salaries after
            System.out.println("\n\nSalaries AFTER");
            showSalaries(myConn, theDepartment);
        }
        catch (Exception exc){
            exc.printStackTrace();
        }finally{
            close(myConn,myStmt,null);
        }


    }

    private static void showSalaries(Connection myConn, String theDepartment) throws SQLException {
        PreparedStatement myStmt = null;
        ResultSet myRs= null;

        try{
            //prepare statement
            myStmt = myConn.prepareStatement("SELECT * from employees where department =?");
            myStmt.setString(1, theDepartment);

            //Execute SQL query
            myRs = myStmt.executeQuery();

            //process result set
            while(myRs.next()){
                String lastName = myRs.getString("last_name");
                String firstName = myRs.getString("first_name");
                double salary = myRs.getDouble("salary");
                String department = myRs.getString("department");

                System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
            }
        }catch (Exception exc){
            exc.printStackTrace();
        }finally {
            close(myStmt,myRs);
        }
    }

    private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {
        if(myRs != null)
            myRs.close();
        if (myStmt!=null)
            myStmt.close();
        if(myRs != null)
            myRs.close();
    }

    private static void close(PreparedStatement myStmt, ResultSet myRs) throws SQLException {
        close(null,myStmt,myRs);
    }
}

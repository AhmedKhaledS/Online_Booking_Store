package controller;
import java.sql.*;

public class DatabaseConnector {

    static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    static final String SCHEMA_NAME = "Book_Store";

    static final String CONNECTION_NAME = "jdbc:mysql://localhost:3306/";
    static final String USERNAME = "root";
    static final String PASSWORD = "admin";

    static final String LAST_MONTH_SALES = "./last-month-sales.pdf";
    static final String TOP_FIVE_CUSTOMERS = "./top-five-customers.pdf";
    static final String TOP_TEN_SALES = "./top-10-sales.pdf";

    private static Connection con;
    private static GenerateReport reportGenerator;

    static {
        registerDriver();
        initializeConnection();
    }

    public static void registerDriver() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void initializeConnection () {
        try {
            con = DriverManager.getConnection(
                    CONNECTION_NAME + SCHEMA_NAME,USERNAME,PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Report generation.
        reportGenerator = new GenerateReport();
    }

    public static ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try{
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception e){
            System.out.println(e);
        }
        return rs;
    }

    public static boolean executeModify (String sql) {
        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            return true;
        } catch (Exception e){
            System.out.println(e);
        } finally {
            reportGenerator.generatePDF(con, LAST_MONTH_SALES);
            reportGenerator.generatePDF(con, TOP_FIVE_CUSTOMERS);
            reportGenerator.generatePDF(con, TOP_TEN_SALES);
        }
        return false;
    }
}


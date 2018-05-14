package controller;
import java.sql.*;

public class DatabaseConnector {

    static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    static final String SCHEMA_NAME = "Book_Store";
    static final String CONNECTION_NAME = "jdbc:mysql://localhost:3306/";
    static final String USERNAME = "root";
    static final String PASSWORD = "admin";
    private static Connection con;

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
    }

    public static ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try{
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
//            while(rs.next())
//                System.out.println(rs.getInt(1));
//            con.close();
        } catch (Exception e){
            System.out.println(e);
        }
        return  rs;
    }

    public static boolean executeModify (String sql) {
        boolean executionState = false;
        try{
            Statement stmt = con.createStatement();
            executionState = stmt.execute(sql);
//            con.close();
        } catch (Exception e){
            System.out.println(e);
        }
        return executionState;
    }
}


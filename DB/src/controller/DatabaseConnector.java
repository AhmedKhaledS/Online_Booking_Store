package controller;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import controller.ReportData;
import controller.GenerateReport;

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
        // Here is the report generation.
        GenerateReport.generatePDF(con);

    }

    public static ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try{
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
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


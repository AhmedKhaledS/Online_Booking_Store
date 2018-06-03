package controller;
import java.sql.*;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

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

        //------------------Jasper testing is here.--------------------------------------------
        HashMap hm = null;
        try {
            System.out.println("Generating report...");
            String jrxmlFileName = "/home/ahmed/jasper_reports/report1.jrxml";
            String jasperFileName = "/home/ahmed/jasper_reports/report1.jasper";
            String pdfFileName = "/home/ahmed/jasper_reports/report1.pdf";

            JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);
            hm = new HashMap();
            hm.put("country", "Egypt");
            hm.put("name", "Ahmed Khaled");

            // Generate jasper print
            JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, hm, con);

            // Export pdf file
            JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);

            System.out.println("Done exporting reports to pdf.");

        } catch (Exception e) {
            System.out.print("Exception" + e);
        }
        //--------------------------------------------------------------------------------------------
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


package controller;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GenerateReport {
    public void generatePDF(Connection con, String PDFFileName) {
        //------------------Jasper testing is here.--------------------------------------------
        Map<String, Object> mapParameters = new HashMap<>();
        try {
            System.out.println("Generating report...");
            File jrxmlFileName = new File(getClass().getClassLoader().getResource("report1.jrxml").getFile());
            String jasperFileName = "./report1.jasper";
           // String pdfFileName = "./report1.pdf";
            ArrayList<ReportData> data = generateData();
//            JRBeanCollectionDataSource dataList = new JRBeanCollectionDataSource(data);
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFileName.getAbsolutePath());
            InputStream input = new FileInputStream(jrxmlFileName.getAbsolutePath());
            mapParameters.put("ReportTitle", "Previous-Month-Sales");

            // Generate jasper print
            JasperPrint jasperPrint = (JasperPrint) JasperFillManager.fillReport(jasperReport,
                    mapParameters, con);

            // Export pdf file
            JasperExportManager.exportReportToPdfFile(jasperPrint, PDFFileName);

            System.out.println("Done exporting reports to pdf.");

        } catch (Exception e) {
            System.out.print("Exception" + e);
        }
        //--------------------------------------------------------------------------------------------
    }

    public static ArrayList generateData() {
        ArrayList<ReportData> data = new ArrayList<>();
        data.add(new ReportData("Ahmed", "Miami"));
        data.add(new ReportData("Khaled", "Miami2"));
        data.add(new ReportData("Saad", "Miami3"));
        return data;
    }
}

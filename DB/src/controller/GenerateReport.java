package controller;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GenerateReport {
    public static void generatePDF(Connection con) {
        //------------------Jasper testing is here.--------------------------------------------
        Map<String, Object> mapParameters = new HashMap<>();
        try {
            System.out.println("Generating report...");
            String jrxmlFileName = "/home/ahmed/jasper_reports/report1.jrxml";
            String jasperFileName = "/home/ahmed/jasper_reports/report1.jasper";
            String pdfFileName = "/home/ahmed/jasper_reports/report1.pdf";
            ArrayList<ReportData> data = generateData();
            JRBeanCollectionDataSource dataList = new JRBeanCollectionDataSource(data);
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFileName);
            InputStream input = new FileInputStream(jrxmlFileName);
            mapParameters.put("field1", "Egypt");
            mapParameters.put("field2", "Ahmed Khaled");

            // Generate jasper print
            JasperPrint jasperPrint = (JasperPrint) JasperFillManager.fillReport(jasperReport,
                    mapParameters, dataList);

            // Export pdf file
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFileName);

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

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
import controller.util.ReportConstants;

public class GenerateReport {

    public void generatePDF(Connection con, String PDFFileName) {
        Map<String, Object> mapParameters = new HashMap<>();
        try {
            System.out.println("Generating report...");
            File jrxmlFileName = null;
            switch (PDFFileName) {
                case ReportConstants.LAST_MONTH_SALES_PDF :
                    jrxmlFileName = new File(getClass().getClassLoader().getResource(
                            ReportConstants.LAST_MONTH_SALES_JRXML).getFile());
                    mapParameters.put(ReportConstants.REPORT_TITLE_PARAMETER_KEY, ReportConstants.LAST_MONTH_SALES_TTILE);
                    break;
                case ReportConstants.TOP_FIVE_CUSTOMERS_PDF :
                    jrxmlFileName = new File(getClass().getClassLoader().getResource(
                            ReportConstants.TOP_FIVE_CUSTOMERS_JRXML).getFile());
                    mapParameters.put(ReportConstants.REPORT_TITLE_PARAMETER_KEY, ReportConstants.TOP_FIVE_CUSTOMERS_TITLE);
                    break;
                case ReportConstants.TOP_TEN_SALES_PDF :
                    jrxmlFileName = new File(getClass().getClassLoader().getResource(
                            ReportConstants.TOP_TEN_SALES_JRXML).getFile());
                    mapParameters.put(ReportConstants.REPORT_TITLE_PARAMETER_KEY, ReportConstants.TOP_TEN_SALES_TITLE);
                    break;
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFileName.getAbsolutePath());

            // Generate jasper print
            JasperPrint jasperPrint = (JasperPrint) JasperFillManager.fillReport(jasperReport,
                    mapParameters, con);

            // Export pdf file
            JasperExportManager.exportReportToPdfFile(jasperPrint, PDFFileName);

            System.out.println("Done exporting reports to pdf.");

        } catch (Exception e) {
            System.out.print("Exception" + e);
        }
    }

}

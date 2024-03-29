package model.reports;

import controller.util.ReportConstants;
import net.sf.jasperreports.engine.*;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ReportGeneratorModel {

    private static ReportGeneratorModel instance = new ReportGeneratorModel();

    private ReportGeneratorModel() {

    }

    public static ReportGeneratorModel getInstance() {
        return instance;
    }
    public void generateReports(String PDFFileName, Connection connection) {
        Map<String, Object> mapParameters = new HashMap<>();
        try {
            System.out.println("Generating report...");
            File jrxmlFileName = null;
            switch (PDFFileName) {
                case ReportConstants.LAST_MONTH_SALES_PDF :
                    /*jrxmlFileName = new File(getClass().getClassLoader().getResource(
                            ReportConstants.LAST_MONTH_SALES_JRXML).getFile());*/
                    jrxmlFileName = new File("D:\\College\\3rdYear\\2ndTerm\\3_Database Systems\\Project\\Online_Booking_Store\\DB\\src\\" + ReportConstants.LAST_MONTH_SALES_JRXML);
                    mapParameters.put(ReportConstants.REPORT_TITLE_PARAMETER_KEY, ReportConstants.LAST_MONTH_SALES_TTILE);
                    break;
                case ReportConstants.TOP_FIVE_CUSTOMERS_PDF :
                    /*jrxmlFileName = new File(getClass().getClassLoader().getResource(
                            ReportConstants.TOP_FIVE_CUSTOMERS_JRXML).getFile());*/
                    jrxmlFileName = new File("D:\\College\\3rdYear\\2ndTerm\\3_Database Systems\\Project\\" +
                            "Online_Booking_Store\\DB\\src\\" + ReportConstants.TOP_FIVE_CUSTOMERS_JRXML);

                    mapParameters.put(ReportConstants.REPORT_TITLE_PARAMETER_KEY, ReportConstants.TOP_FIVE_CUSTOMERS_TITLE);
                    break;
                case ReportConstants.TOP_TEN_SALES_PDF :
                    /*jrxmlFileName = new File(getClass().getClassLoader().getResource(
                            ReportConstants.TOP_TEN_SALES_JRXML).getFile());*/
                    jrxmlFileName = new File("D:\\College\\3rdYear\\2ndTerm\\3_Database Systems\\Project\\" +
                            "Online_Booking_Store\\DB\\src\\" + ReportConstants.TOP_TEN_SALES_JRXML);
                    mapParameters.put(ReportConstants.REPORT_TITLE_PARAMETER_KEY, ReportConstants.TOP_TEN_SALES_TITLE);
                    break;
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFileName.getAbsolutePath());

            // Generate jasper print
            JasperPrint jasperPrint = (JasperPrint) JasperFillManager.fillReport(jasperReport,
                    mapParameters, connection);

            // Export pdf file
            JasperExportManager.exportReportToPdfFile(jasperPrint, PDFFileName);

            System.out.println("Done exporting reports to pdf.");

        } catch (Exception e) {
            System.out.print("Exception" + e);
        }
    }
}

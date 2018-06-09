package controller;

import model.reports.ReportGeneratorModel;

import java.sql.Connection;

public class ReportGenerator {

    final String LAST_MONTH_SALES = "./last-month-sales.pdf";
    final String TOP_FIVE_CUSTOMERS = "./top-five-customers.pdf";
    final String TOP_TEN_SALES = "./top-10-sales.pdf";

    private Connection connection;

    public ReportGenerator(Connection connection) {
        this.connection = connection;
    }

    private void generatePDF(String PDFFileName) {
        ReportGeneratorModel.getInstance().generateReports(PDFFileName, this.connection);
    }

    public void generateReports() {
        generatePDF(LAST_MONTH_SALES);
        generatePDF(TOP_FIVE_CUSTOMERS);
        generatePDF(TOP_TEN_SALES);
    }
}

package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	private static ReportGenerator reportGenerator;

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

	public static void initializeConnection() {
		try {
			con = DriverManager.getConnection(CONNECTION_NAME + SCHEMA_NAME, USERNAME, PASSWORD);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Report generation.
		reportGenerator = new ReportGenerator(con);
	}

	public static ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

	public static boolean executeModify(String sql) {
		try {
			Statement stmt = con.createStatement();
			stmt.execute(sql);
			return true;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// reportGenerator.generatePDF(LAST_MONTH_SALES);
			// reportGenerator.generatePDF(TOP_FIVE_CUSTOMERS);
			// reportGenerator.generatePDF(TOP_TEN_SALES);
		}
		return false;
	}

	public static void setCommitLevel(boolean level) {
		try {
			con.setAutoCommit(level);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commitDB() {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollDB() {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
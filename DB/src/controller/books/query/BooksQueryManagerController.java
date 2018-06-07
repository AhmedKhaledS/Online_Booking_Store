package controller.books.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import controller.DatabaseConnector;
import controller.Utils;
import model.ManagerQuery;

public class BooksQueryManagerController {

	public static Vector<Vector<String>> getBooksList(String key, String value,
                                                      BooksQueryUtil.Operator operator) {
        return ManagerQuery.getInstance().getBooksList(key, value, operator);
	}

	public static void addBook(String[] originalData) {

		ManagerQuery.getInstance().addBook(originalData);
	}

	public static void deleteBook(String ISBN) {

		ManagerQuery.getInstance().deleteBook(ISBN);
	}


}

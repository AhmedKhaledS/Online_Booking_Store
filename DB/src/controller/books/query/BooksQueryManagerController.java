package controller.books.query;

import java.util.Vector;

import model.ManagerQuery;

public class BooksQueryManagerController {

	public static Vector<Vector<String>> getBooksList(String key, String value, BooksQueryUtil.Operator operator) {
		return ManagerQuery.getInstance().getBooksList(key, value, operator);
	}

	public static void addBook(String[] originalData) {

		ManagerQuery.getInstance().addBook(originalData);
	}

	public static void deleteBook(String ISBN) {

		ManagerQuery.getInstance().deleteBook(ISBN);
	}

}

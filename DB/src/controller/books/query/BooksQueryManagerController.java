package controller.books.query;

import java.util.Vector;

import model.ManagerOrdersModel;

public class BooksQueryManagerController {

	public static Vector<Vector<String>> getBooksList(String key, String value, BooksQueryUtil.Operator operator) {
		return ManagerOrdersModel.getInstance().getBooksList(key, value, operator);
	}

	public static boolean addBook(String[] originalData) {

		return ManagerOrdersModel.getInstance().addBook(originalData);
	}

	public static void deleteBook(String ISBN) {

		ManagerOrdersModel.getInstance().deleteBook(ISBN);
	}

}

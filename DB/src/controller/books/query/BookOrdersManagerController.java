package controller.books.query;

import java.util.Vector;

import controller.DatabaseConnector;
import controller.users.UsersUtil;
import model.ManagerOrdersModel;
import model.UserModel;

import javax.swing.*;

public class BookOrdersManagerController {

	public static Vector<Vector<String>> getBooksList(String key, String value, BooksQueryUtil.Operator operator) {
		return ManagerOrdersModel.getInstance().getBooksList(key, value, operator);
	}

	public static boolean addBook(String[] originalData) {

		return ManagerOrdersModel.getInstance().addBook(originalData);
	}

	public static void deleteBook(String ISBN) {

		ManagerOrdersModel.getInstance().deleteBook(ISBN);
	}
	public static boolean modifyBook(String ISBN, String[] data) {

		boolean successfullyUpdated = ManagerOrdersModel.getInstance().updateTitle(ISBN, data[1])
				| ManagerOrdersModel.getInstance().updatePublisher(ISBN, data[2])
				| ManagerOrdersModel.getInstance().updatePublicationYear(ISBN, data[3])
				| ManagerOrdersModel.getInstance().updateCategory(ISBN, data[4])
				| ManagerOrdersModel.getInstance().updatePrice(ISBN, data[5])
				| ManagerOrdersModel.getInstance().updateNoOfCoies(ISBN, data[6])
				| ManagerOrdersModel.getInstance().updateMinQuantity(ISBN, data[7])
                | ManagerOrdersModel.getInstance().updateISBN(ISBN, data[0]);
		if (successfullyUpdated) {
            DatabaseConnector.commitDB();
            JOptionPane.showMessageDialog(null, "Your book is updated successfully.");
		} else {
			JOptionPane.showMessageDialog(null, "Error occurred while updating profile!");
		}
		return successfullyUpdated;
	}

}

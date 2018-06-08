package model;

import javax.swing.JOptionPane;

import controller.DatabaseConnector;
import view.TableFrame;
import view.util.table.frame.definer.PromoteUserTableFrameDefiner;

public class ManagerOrdersModel extends UserQuery {

	private static ManagerOrdersModel instance;

	private ManagerOrdersModel() {
	}

	public static ManagerOrdersModel getInstance() {
		if (instance == null) {
			instance = new ManagerOrdersModel();
		}
		return instance;
	}

	public boolean addBook(String[] originalData) {
		
		boolean error = false;

		String data[] = new String[8];
		String authors[] = originalData[8].split(",");

		for (int i = 0; i < data.length; i++) {
			data[i] = originalData[i];
		}

		String add_book_sql = "INSERT INTO BOOK VALUES (" + data[0] + "," + "'" + data[1] + "'" + "," + data[2] + ","
				+ "'" + data[3] + "'" + "," + "'" + data[4] + "'" + "," + data[5] + "," + data[6] + "," + data[7]
				+ ");";

		if (DatabaseConnector.executeModify(add_book_sql)) {

			String add_author_sql;

			for (int i = 0; i < authors.length && !error; i++) {

				add_author_sql = "INSERT INTO BOOK_AUTHORS VALUES (" + data[0] + "," + "'" + authors[i] + "'" + ");";

				if (!DatabaseConnector.executeModify(add_author_sql)) {
					error = true;
					DatabaseConnector.rollDB();
					JOptionPane.showMessageDialog(null, "Error! Please Try Again");
				}
			}

			if (!error) {
				DatabaseConnector.commitDB();
				JOptionPane.showMessageDialog(null, "Added Successfully");
			}

		} else {
			error = true;
			DatabaseConnector.rollDB();
			JOptionPane.showMessageDialog(null, "Error! Please Try Again");
		}

		DatabaseConnector.setCommitLevel(true);
		
		return !error;
	}



	public void deleteBook(String ISBN) {

		System.out.println(ISBN);
		String sql_author_data = "DELETE FROM BOOK_AUTHORS WHERE ISBN=" + ISBN + ";";
		String sql_basic_data = "DELETE FROM BOOK WHERE ISBN=" + ISBN + ";";
		DatabaseConnector.executeModify(sql_author_data);
		DatabaseConnector.executeModify(sql_basic_data);
    }

	public void promoteUser(String rank) {
		String promote_user_sql = "UPDATE USER SET User_type = 'Manager' WHERE E_mail='" + rank + "';";

		System.out.println(promote_user_sql);

		if (DatabaseConnector.executeModify(promote_user_sql)) {
			System.out.println("DONE");
			TableFrame.changeWindow(new PromoteUserTableFrameDefiner());
		} else {
			System.out.println("ERROR");
		}
	}

	

    public boolean updateISBN(String isbn, String newISBN) {
	    return false;
    }

    public boolean updateTitle(String isbn, String newTitle) {
        return false;
	}

    public boolean updatePublisher(String isbn, String newPublisher) {
        return false;
	}

    public boolean updatePublicationYear(String isbn, String newYear) {
        return false;
	}

    public boolean updateCategory(String isbn, String newCategory) {
        return false;
	}

    public boolean updatePrice(String isbn, String newPrice) {
        return false;
	}

    public boolean updateNoOfCoies(String isbn, String newNoOfCopies) {
        return false;
	}

    public boolean updateMinQuantity(String isbn, String newMinQuantity) {
        return false;
	}
}

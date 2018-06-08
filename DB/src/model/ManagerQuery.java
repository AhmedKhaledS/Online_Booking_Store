package model;

import javax.swing.JOptionPane;

import controller.DatabaseConnector;

public class ManagerQuery extends UserQuery {

	private static ManagerQuery instance;

	private ManagerQuery() {
	}

	public static ManagerQuery getInstance() {
		if (instance == null) {
			instance = new ManagerQuery();
		}
		return instance;
	}

	public boolean updateBook(String[] originalData) {
		boolean error = false;

		String data[] = new String[8];
		String authors[] = originalData[8].split(",");

		for (int i = 0; i < data.length; i++) {
			data[i] = originalData[i];
		}

		String add_book_sql = "UPDATE BOOK SET (" + data[0] + "," + "'" + data[1] + "'" + "," + data[2] + ","
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

	public void deleteAuthors(String ISBN) {

		System.out.println(ISBN);
		String sql_author_data = "DELETE FROM BOOK_AUTHORS WHERE ISBN=" + ISBN + ";";
		// String sql_basic_data = "DELETE FROM BOOK WHERE ISBN=" + ISBN + ";";
		DatabaseConnector.executeModify(sql_author_data);
		// DatabaseConnector.executeModify(sql_basic_data);
	}

}

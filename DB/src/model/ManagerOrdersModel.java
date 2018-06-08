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

	public boolean manageOrders(String orderID) {
        String accept_order_sql = "DELETE FROM MANAGER_ORDERS WHERE Order_id=" + orderID + ";";

        System.out.println(accept_order_sql);

        if (DatabaseConnector.executeModify(accept_order_sql)) {
            System.out.println("DONE");
            return true;
        }
        System.out.println("ERROR");
        return false;
    }

	

    public boolean updateISBN(String isbn, String newISBN) {
        String queryStmt = "UPDATE BOOK SET `ISBN`=" + newISBN +
                " WHERE `ISBN`=" + isbn + ";";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating book-ISBN!");
            return false;
        }
        return true;
    }

    public boolean updateTitle(String isbn, String newTitle) {
        String queryStmt = "UPDATE BOOK SET `Title`=" + "'" + newTitle + "'" +
                " WHERE `ISBN`=" + isbn + ";";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating book-title!");
            return false;
        }
        return true;
	}

    public boolean updatePublisher(String isbn, String newPublisherId) {
        String queryStmt = "UPDATE BOOK SET `Publisher_id`=" + newPublisherId  +
                " WHERE `ISBN`=" + isbn + ";";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating publisher-id!");
            return false;
        }
        return true;
	}

    public boolean updatePublicationYear(String isbn, String newYear) {
        String queryStmt = "UPDATE BOOK SET `Publication_year`=" + "'" + newYear + "'" +
                " WHERE `ISBN`=" + isbn + ";";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating publication year!");
            return false;
        }
        return true;
	}

    public boolean updateCategory(String isbn, String newCategory) {
        String queryStmt = "UPDATE BOOK SET `Category`=" + "'" + newCategory + "'" +
                " WHERE `ISBN`=" + isbn + ";";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating category of book!");
            return false;
        }
        return true;
	}

    public boolean updatePrice(String isbn, String newPrice) {
        String queryStmt = "UPDATE BOOK SET `Price`=" + newPrice +
                " WHERE `ISBN`=" + isbn + ";";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating price of book!");
            return false;
        }
        return true;
	}

    public boolean updateNoOfCoies(String isbn, String newNoOfCopies) {
        String queryStmt = "UPDATE BOOK SET `No_of_copies`=" + newNoOfCopies +
                " WHERE `ISBN`=" + isbn + ";";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating number of book-copies!");
            return false;
        }
        return true;
	}

    public boolean updateMinQuantity(String isbn, String newMinQuantity) {
        String queryStmt = "UPDATE BOOK SET `Min_Quantity`=" + newMinQuantity +
                " WHERE `ISBN`=" + isbn + ";";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating minimim quantity of book-copies!");
            return false;
        }
        return true;
	}
}

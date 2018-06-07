package controller.books.editor.actions;

import javax.swing.JOptionPane;

import controller.DatabaseConnector;
import view.AddItemFrame;

public class AddAction implements EditorAction {

	@Override
	public String get_target_button_name() {
		return "ADD";
	}

	@Override
	public void target_button_action(String[] orginalData) {

		String data[] = new String[8];
		String authors[] = orginalData[8].split(",");

		for (int i = 0; i < data.length; i++) {
			data[i] = orginalData[i];
		}

		String add_book_sql = "INSERT INTO BOOK VALUES (" + data[0] + "," + "'" + data[1] + "'" + "," + data[2] + ","
				+ "'" + data[3] + "'" + "," + "'" + data[4] + "'" + "," + data[5] + "," + data[6] + "," + data[7]
				+ ");";

		if (DatabaseConnector.executeModify(add_book_sql)) {

			String add_author_sql;
			for (int i = 0; i < authors.length; i++) {
				add_author_sql = "INSERT INTO BOOK_AUTHORS VALUES (" + data[0] + "," + "'" + authors[i] + "'" + ");";
				DatabaseConnector.executeModify(add_author_sql);
			}
			JOptionPane.showMessageDialog(null, "Added Successfully");
		} else {
			JOptionPane.showMessageDialog(null, "Error! Please Try Again");
		}
	}

	@Override
	public void back_button_action() {
		AddItemFrame.changeWindow();
	}

	@Override
	public String[] get_initial_data() {
		return null;
	}

}
package controller.books.viewer.actions;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTable;

import controller.DatabaseConnector;
import controller.books.editor.actions.EditAction;
import view.AddBookFrame;
import view.ManagerFrame;
import view.util.GUIConstants;

public class ManagerEditBookAction extends UserAction {

	public ManagerEditBookAction() {
		this.actionName = "Edit";
		this.targetUser = TargetUser.MANAGER;
	}

	@Override
	public void accept(JTable jTable, Integer row) {

		String ISBN = (String) jTable.getModel().getValueAt(row, 1);

		String[] data = new String[9];

		String sql_basic_data = "SELECT * FROM BOOK WHERE ISBN=" + ISBN + ";";
		String sql_author_data = "SELECT * FROM BOOK_AUTHORS WHERE ISBN=" + ISBN + ";";

		ResultSet basic_data = DatabaseConnector.executeQuery(sql_basic_data);
		ResultSet author_data = DatabaseConnector.executeQuery(sql_author_data);

		try {
			basic_data.next();
			for (int i = 0; i < 8; i++) {
				data[i] = basic_data.getString(i + 1);
			}
			data[8] = "";
			while (author_data.next()) {
				data[8] += author_data.getString("Author_name") + ",";
			}
			if(data[8].length() > 1)
				data[8] = data[8].substring(0, data[8].length() - 1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		AddBookFrame.changeWindow(new EditAction(data));
	}

	@Override
	public void addToFrame(Container container) {
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ManagerFrame.changeWindow();
			}
		});
		backButton.setBounds(GUIConstants.initX, GUIConstants.initY * 10, GUIConstants.width, GUIConstants.height);
		container.add(backButton);
	}

	@Override
	public Vector<Vector<String>> getData() {
		return null;
	}
}
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
import model.OldBookData;
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

		OldBookData.loadOldBookData(ISBN, data);

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
package controller.books.viewer.actions;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTable;

import controller.books.editor.actions.EditAction;
import view.AddBookFrame;
import view.ManagerFrame;
import view.util.GUIConstants;

public class ManagerAction extends UserAction {

	public ManagerAction() {
		this.targetUser = TargetUser.MANAGER;
	}

	@Override
	public void accept(JTable jTable, Integer row) {
		Vector<String> dataRow = new Vector<>();
		for (int i = 1; i < jTable.getModel().getColumnCount(); i++) {
			dataRow.add((String) jTable.getModel().getValueAt(row, i));
		}
		for (int i = 0; i < dataRow.size(); i++) {
			System.out.println(dataRow.get(i));
		}
		/// TO DO: GO TO EDIT
//		String[] data = null;
//		AddBookFrame.changeWindow(new EditAction(data));
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
}
package controller.books.viewer.actions;

import java.awt.Container;
import java.util.Vector;

import javax.swing.JTable;

import model.ManagerOrdersModel;

public class PromoteUserAction extends UserAction {

	public PromoteUserAction() {
		this.actionName = "PROMOTE";
	}

	@Override
	public void accept(JTable jTable, Integer row) {

		Vector<String> dataRow = new Vector<>();
		for (int i = 1; i < jTable.getModel().getColumnCount(); i++) {
			dataRow.add((String) jTable.getModel().getValueAt(row, i));
		}
		ManagerOrdersModel.getInstance().promoteUser(dataRow.get(0));

	}

	@Override
	public void addToFrame(Container container) {

	}

	@Override
	public Vector<Vector<String>> getData() {
		return null;
	}
}

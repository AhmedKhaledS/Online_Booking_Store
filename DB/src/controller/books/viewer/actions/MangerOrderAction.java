package controller.books.viewer.actions;

import java.awt.Container;
import java.util.Vector;

import javax.swing.JTable;

public class MangerOrderAction extends UserAction {

	public MangerOrderAction() {
		this.actionName = "RECIEVED";
	}

	@Override
	public void accept(JTable jTable, Integer row) {

		Vector<String> dataRow = new Vector<>();
		for (int i = 1; i < jTable.getModel().getColumnCount(); i++) {
			dataRow.add((String) jTable.getModel().getValueAt(row, i));
		}

		System.out.println("DONE");
	}

	@Override
	public void addToFrame(Container container) {

	}

	@Override
	public Vector<Vector<String>> getData() {
		return null;
	}
}

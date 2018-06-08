package controller.books.viewer.actions;

import java.awt.Container;
import java.util.Vector;

import javax.swing.JTable;

import controller.DatabaseConnector;
import view.TableFrame;
import view.util.table.frame.definer.PromoteUserTableFrameDefiner;

public class MangerOrderAction extends UserAction {

	public MangerOrderAction() {
		this.actionName = "RECIEVE";
	}

	@Override
	public void accept(JTable jTable, Integer row) {

		Vector<String> dataRow = new Vector<>();
		for (int i = 1; i < jTable.getModel().getColumnCount(); i++) {
			dataRow.add((String) jTable.getModel().getValueAt(row, i));
		}

		String accept_order_sql = "DELETE FROM MANAGER_ORDERS WHERE Order_id=" + dataRow.get(0) + ";";

		System.out.println(accept_order_sql);

		if (DatabaseConnector.executeModify(accept_order_sql)) {
			System.out.println("DONE");
			TableFrame.changeWindow(new PromoteUserTableFrameDefiner());
		} else {
			System.out.println("ERROR");
		}
	}

	@Override
	public void addToFrame(Container container) {

	}

	@Override
	public Vector<Vector<String>> getData() {
		return null;
	}
}

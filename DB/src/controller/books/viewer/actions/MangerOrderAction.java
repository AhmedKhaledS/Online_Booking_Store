package controller.books.viewer.actions;

import java.awt.Container;
import java.util.Vector;

import javax.swing.JTable;

import controller.DatabaseConnector;
import model.ManagerOrdersModel;
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
		boolean done = ManagerOrdersModel.getInstance().manageOrders(dataRow.get(0));
        boolean isManaged = ManagerOrdersModel.getInstance().manageOrders(dataRow.get(0));
        if (isManaged) {
            TableFrame.changeWindow(new PromoteUserTableFrameDefiner());
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

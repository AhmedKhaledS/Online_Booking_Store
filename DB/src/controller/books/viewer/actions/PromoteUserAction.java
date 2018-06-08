package controller.books.viewer.actions;

import java.awt.Container;
import java.util.Vector;

import javax.swing.JTable;

import controller.DatabaseConnector;
import view.TableFrame;
import view.util.table.frame.definer.PromoteUserTableFrameDefiner;

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

		String promote_user_sql = "UPDATE USER SET User_type = 'Manager' WHERE E_mail='" + dataRow.get(0) + "';";

		System.out.println(promote_user_sql);

		if (DatabaseConnector.executeModify(promote_user_sql)) {
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

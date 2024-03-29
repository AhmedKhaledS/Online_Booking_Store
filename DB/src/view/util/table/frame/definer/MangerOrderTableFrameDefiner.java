package view.util.table.frame.definer;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;

import controller.DatabaseConnector;
import controller.books.viewer.actions.MangerOrderAction;
import view.ManagerFrame;
import view.util.GUIConstants;
import view.util.WindowClosure;

public class MangerOrderTableFrameDefiner extends TableFrameDefiner implements ActionListener {

	private JButton backButton;

	public MangerOrderTableFrameDefiner() {
		this.action = new MangerOrderAction();
	}

	@Override
	public void update(Vector<Vector<String>> data) {

	}

	@Override
	public Vector<String> defineTableAttributes() {
		Vector<String> columnNames = new Vector<>();
		columnNames.add(action.getActionName());
		columnNames.add("Order_id");
		columnNames.add("ISBN");
		columnNames.add("Quantity");
		return columnNames;
	}

	@Override
	public void modifyFrame(Container container) {

		backButton = new JButton("Back");

		backButton.setBounds(GUIConstants.initX, GUIConstants.initY + GUIConstants.offsetY * 8, GUIConstants.width,
				GUIConstants.height);

		setActionListeners();
		addToContainer(container);

		Vector<Vector<String>> data = new Vector<>();

		ResultSet orders = DatabaseConnector.executeQuery("SELECT * FROM MANAGER_ORDERS");

		try {
			while (orders.next()) {
				Vector<String> dataRow = new Vector<>();
				dataRow.add("DONE");
				dataRow.add(orders.getString(1));
				dataRow.add(orders.getString(2));
				dataRow.add(orders.getString(3));
				data.add(dataRow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.definableTableFrame.setData(data);

	}

	private void setActionListeners() {
		backButton.addActionListener(this);
	}

	private void addToContainer(Container container) {
		container.add(backButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			WindowClosure.closeActiveWindows();
			ManagerFrame.changeWindow();
		}
	}
}
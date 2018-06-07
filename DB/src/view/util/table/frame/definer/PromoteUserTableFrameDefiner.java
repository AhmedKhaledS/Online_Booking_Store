package view.util.table.frame.definer;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;

import controller.DatabaseConnector;
import controller.books.viewer.actions.PromoteUserAction;
import view.util.GUIConstants;

public class PromoteUserTableFrameDefiner extends TableFrameDefiner implements ActionListener {

	private JButton backButton;

	public PromoteUserTableFrameDefiner() {
		this.action = new PromoteUserAction();
	}

	@Override
	public void update(Object eventSource) {

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

		ResultSet orders = DatabaseConnector.executeQuery("SELECT * FROM BOOK");

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
			System.out.println("BACK");
		}
	}
}
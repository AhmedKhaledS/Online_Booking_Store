package view.util.table.frame.definer;

import static view.util.GUIConstants.offsetX;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.books.query.BookOrdersManagerController;
import controller.books.query.BooksQueryUtil;
import controller.books.viewer.actions.ManagerEditBookAction;
import view.ManagerFrame;
import view.util.GUIConstants;
import view.util.WindowClosure;

public class BooksEditorTableFrameDefiner extends TableFrameDefiner implements ActionListener {

	private JLabel searchKeyLabel;
	private JTextField searchKeyTextField;
	private JComboBox<String> possibleKeys;
	private JButton searchButton;
	private JButton clearButton;
	private JButton backButton;
	private JLabel quantityLabel;
	private JTextField quantityTextField;

	public BooksEditorTableFrameDefiner() {
		this.action = new ManagerEditBookAction();
	}

	@Override
	public void update(Vector<Vector<String>> data) {

	}

	@Override
	public Vector<String> defineTableAttributes() {
		Vector<String> columnNames = new Vector<>();
		columnNames.add(action.getActionName());
		columnNames.addAll(Constants.getBookTableColumnNames());
		return columnNames;
	}

	@Override
	public void modifyFrame(Container container) {
		quantityTextField = new JTextField();
		quantityLabel = new JLabel("Book Quantity");
		searchKeyLabel = new JLabel("Search By");
		searchKeyTextField = new JTextField();
		String[] keys = { "Title", "Publisher_Name", "Publication_Year", "Category" };
		possibleKeys = new JComboBox<>(keys);
		searchButton = new JButton("Search");
		clearButton = new JButton("Clear");
		backButton = new JButton("Back");
		searchKeyLabel.setBounds(GUIConstants.initX, GUIConstants.initY, GUIConstants.width, GUIConstants.height);
		searchKeyTextField.setBounds(GUIConstants.initX + offsetX, GUIConstants.initY, GUIConstants.width,
				GUIConstants.height);
		possibleKeys.setBounds(GUIConstants.initX + offsetX * 2, GUIConstants.initY, GUIConstants.width,
				GUIConstants.height);
		searchButton.setBounds(GUIConstants.initX + offsetX * 3, GUIConstants.initY, GUIConstants.width,
				GUIConstants.height);
		clearButton.setBounds(GUIConstants.initX + offsetX * 6, GUIConstants.initY, GUIConstants.width,
				GUIConstants.height);
		backButton.setBounds(GUIConstants.initX, GUIConstants.initY + GUIConstants.offsetY * 8, GUIConstants.width,
				GUIConstants.height);
		quantityLabel.setBounds(GUIConstants.initX + offsetX, GUIConstants.initY + GUIConstants.offsetY * 8,
				GUIConstants.width, GUIConstants.height);
		quantityTextField.setBounds(GUIConstants.initX + offsetX * 2, GUIConstants.initY + GUIConstants.offsetY * 8,
				GUIConstants.width, GUIConstants.height);
		setActionListeners();
		addToContainer(container);
	}

	private void setActionListeners() {
		searchButton.addActionListener(this);
		backButton.addActionListener(this);
	}

	private void addToContainer(Container container) {
		container.add(searchKeyLabel);
		container.add(searchKeyTextField);
		container.add(possibleKeys);
		container.add(searchButton);
		container.add(clearButton);
		container.add(backButton);
		container.add(quantityLabel);
		container.add(quantityTextField);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			try {
				Class.forName("controller.DatabaseConnector");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			Vector<Vector<String>> data = new Vector<>();
			data = BookOrdersManagerController.getBooksList(String.valueOf(possibleKeys.getSelectedItem()),
					searchKeyTextField.getText(), BooksQueryUtil.Operator.EQUALITY);
			this.definableTableFrame.setData(data);
		} else if (e.getSource() == clearButton) {
			searchKeyTextField.setText("");
		} else if (e.getSource() == backButton) {
			WindowClosure.closeActiveWindows();
			ManagerFrame.changeWindow();
		}
	}
}

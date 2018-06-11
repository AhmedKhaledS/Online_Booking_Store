package view;

import controller.books.query.BookOrdersCustomerController;
import controller.books.viewer.actions.UserAction;
import controller.users.UserProfileController;
import controller.users.UsersUtil;
import view.util.GUIConstants;
import view.util.WindowChanger;
import view.util.table.ButtonColumn;
import view.util.table.ButtonEditor;
import view.util.table.ButtonRenderer;
import view.util.table.frame.definer.BooksOrderTableFrameDefiner;
import view.util.table.frame.definer.TableFrameDefiner;
import view.util.table.frame.definer.DefinableTableFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Vector;

import static controller.books.query.BookOrdersManagerController.getAllBooks;
import static view.util.GUIConstants.initX;
import static view.util.GUIConstants.initY;
import static view.util.GUIConstants.offsetY;

public class TableFrame extends JFrame implements ActionListener, WindowChanger, DefinableTableFrame {

	/** main container. */
	Container container = getContentPane();

	/** The class observing the frame. */
	TableFrameDefiner observer;

	/** Main table components. */
	Vector<Vector<String>> data;
	Vector<String> columnNames;
	HashSet<Integer> editableColumns = new HashSet<>();
	DefaultTableModel dm = new DefaultTableModel() {
		@Override
		public boolean isCellEditable (int row, int column) {
			return column == 0 || editableColumns.contains(new Integer(column));
		}
	};
	JTable table = new JTable(dm);
	JScrollPane scroll;
	JButton nextPageButton = new JButton("Next");
	JButton prevPageButton = new JButton("Prev");

	JButton backButton = new JButton("Back");

	String rowButtonActionName;
	UserAction rowButtonAction;

	/** To support dividing table into multiple pages. */
	int pageIndex = 0;
	final int MAX_PAGE_LEN = 10;

	TableFrame(TableFrameDefiner observer) {
		this.data = new Vector<>();
		this.observer = observer;
		this.observer.setDefinableTableFrame(this);
		this.columnNames = this.observer.defineTableAttributes();
		this.rowButtonAction = this.observer.getAction();
		this.rowButtonActionName = this.rowButtonAction.getActionName();
		initializeTable();
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		addActionEvent();
		this.observer.modifyFrame(container);
	}

	private void initializeTable () {
		dm.setDataVector(new Vector<>(),columnNames);
		setRowButtonSettings();
		table.setVisible(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scroll = new JScrollPane(table);
		scroll.setVisible(true);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.getColumnModel().getColumn(0).setPreferredWidth(100);

		setData(BookOrdersCustomerController.getSpecifiedTuples(MAX_PAGE_LEN, pageIndex));
	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {

		scroll.setBounds(initX, GUIConstants.initY + offsetY,
				GUIConstants.width * 4, GUIConstants.height * 10);
		nextPageButton.setBounds(initX + GUIConstants.offsetX * 3,
				GUIConstants.initY + offsetY, GUIConstants.width, GUIConstants.height);
		prevPageButton.setBounds(initX + GUIConstants.offsetX * 3,
				GUIConstants.initY + offsetY * 2, GUIConstants.width, GUIConstants.height);
		backButton.setBounds(initX, initY + offsetY * 9, GUIConstants.width, GUIConstants.height);
	}

	public void addComponentsToContainer() {
		container.add(nextPageButton);
		container.add(prevPageButton);
		container.add(backButton);
		container.add(scroll);
	}

	public void addActionEvent() {
		nextPageButton.addActionListener(this);
		prevPageButton.addActionListener(this);
		backButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nextPageButton) {
			Vector<Vector<String>> books = BookOrdersCustomerController.getSpecifiedTuples(MAX_PAGE_LEN,
					pageIndex + MAX_PAGE_LEN);
			if (!books.isEmpty()) {
				pageIndex += MAX_PAGE_LEN;
				//dm.setDataVector(books, columnNames);
				setData(books);
			}
		} else if (e.getSource() == prevPageButton) {
		    if (pageIndex - MAX_PAGE_LEN < 0) {
		        return;
            }
			Vector<Vector<String>> books = BookOrdersCustomerController.getSpecifiedTuples(MAX_PAGE_LEN,
					pageIndex - MAX_PAGE_LEN);
			if (!books.isEmpty()) {
				pageIndex -= MAX_PAGE_LEN;
				//dm.setDataVector(books, columnNames);
                setData(books);
            }
		} else if (e.getSource() == backButton) {
			dispose();
			UsersUtil.UserType userType = UserProfileController.getInstance().getCurrentLoggedInUser().getType();
			if (userType == UsersUtil.UserType.CUSTOMER) {
				CustomerFrame.changeWindow();
			} else if (userType == UsersUtil.UserType.MANAGER) {
				ManagerFrame.changeWindow();
			}
		}
		setRowButtonSettings();
	}

	public static void changeWindow (TableFrameDefiner definer) {
		TableFrame frame = new TableFrame(definer);
//		TableFrame frame = new TableFrame(new ShoppingCartTableFrameDefiner());
		frame.setTitle("TableFrame");
		frame.setVisible(true);
		frame.setBounds(200, 80, 950, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);
	}

	@Override
	public void setData (Vector<Vector<String>> data) {
		this.data = data;
		dm.setDataVector(data, columnNames);
		setRowButtonSettings();
		return;
	}

	@Override
	public void setEditableColumn(int editableColumn) {
		this.editableColumns.add(editableColumn);
	}

	private void setRowButtonSettings () {
		Action buttonAction = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				JTable table = (JTable)e.getSource();
				int modelRow = Integer.valueOf( e.getActionCommand() );
				rowButtonAction.accept(table, modelRow);
			}
		};
		ButtonColumn buttonColumn = new ButtonColumn(this.table, buttonAction, 0);
		buttonColumn.setMnemonic(KeyEvent.VK_D);

	}

	@Override
	public Vector<Vector<String>> getTableData() {
		return null;
	}

	public static void main (String[] args) {
		TableFrame.changeWindow(new BooksOrderTableFrameDefiner());
	}
}



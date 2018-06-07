package view;

import view.util.GUIConstants;
import view.util.WindowChanger;
import view.util.observer.BooksTableFrameObserver;
import view.util.observer.TableFrameObserver;
import view.util.observer.ObservableTableFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class TableFrame extends JFrame implements ActionListener, WindowChanger, ObservableTableFrame {

	/** main container. */
	Container container = getContentPane();

	/** The class observing the frame. */
	TableFrameObserver observer;

	/** Main table components. */
	Vector<Vector<String>> data;
	Vector<String> columnNames;
	DefaultTableModel dm = new DefaultTableModel();
	JTable table = new JTable(dm);
	JScrollPane scroll;
	JButton nextPageButton = new JButton("Next");
	JButton prevPageButton = new JButton("Prev");

	/** To support dividing table into multiple pages. */
	int pageIndex = 0;
	final int MAX_PAGE_LEN = 10;

	TableFrame(TableFrameObserver observer) {
		this.data = new Vector<>();
		this.observer.setObservableTableFrame(this);
		this.observer = observer;
		this.observer.setObservableTableFrame(this);
		this.observer.defineTableAttributes();
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		addActionEvent();
		this.observer.modifyFrame();

	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		table.setVisible(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scroll = new JScrollPane(table);
		scroll.setVisible(true);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		scroll.setBounds(GUIConstants.initX, GUIConstants.initY + GUIConstants.offsetY,
				GUIConstants.width * 4, GUIConstants.height * 10);
		nextPageButton.setBounds(GUIConstants.initX + GUIConstants.offsetX * 3,
				GUIConstants.initY + GUIConstants.offsetY, GUIConstants.width, GUIConstants.height);
		prevPageButton.setBounds(GUIConstants.initX + GUIConstants.offsetX * 3,
				GUIConstants.initY + GUIConstants.offsetY * 2, GUIConstants.width, GUIConstants.height);
	}

	public void addComponentsToContainer() {
		container.add(scroll);
		container.add(nextPageButton);
		container.add(prevPageButton);
	}

	public void addActionEvent() {
		nextPageButton.addActionListener(this);
		prevPageButton.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nextPageButton) {
			if (pageIndex + MAX_PAGE_LEN < data.size()) {
				pageIndex += MAX_PAGE_LEN;
				dm.setDataVector(new Vector<>(data.subList(pageIndex, Math.min(pageIndex + MAX_PAGE_LEN, data.size()))),
						columnNames);
			}
		} else if (e.getSource() == prevPageButton) {
			if (pageIndex - MAX_PAGE_LEN >= 0) {
				pageIndex -= MAX_PAGE_LEN;
				dm.setDataVector(new Vector<>(data.subList(pageIndex, Math.min(pageIndex + MAX_PAGE_LEN, data.size()))),
						columnNames);
			}
		} else {
			this.observer.update(e.getSource());
		}
	}

	public static void changeWindow () {
		TableFrame frame = new TableFrame(new BooksTableFrameObserver());
		frame.setTitle("TableFrame");
		frame.setVisible(true);
		frame.setBounds(10, 10, 950, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);
	}

	@Override
	public void notifyObservers() {

	}

	@Override
	public Container getFrameContainer() {
		return this.container;
	}

	@Override
	public JTable getTable() {
		return this.table;
	}

	public static void main (String[] args) {
		TableFrame.changeWindow();
	}
}


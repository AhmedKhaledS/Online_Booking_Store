package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import view.util.WindowChanger;

public class ManagerFrame extends JFrame implements WindowChanger {

	Container container = getContentPane();

	JButton btnPlaceOrder = new JButton("PLACE ORDER");
	JButton btnAddBook = new JButton("ADD BOOK");
	JButton btnModifyBook = new JButton("MODIFY BOOK");
	JButton btnManageOrders = new JButton("MANAGE ORDERS");
	JButton btnPromoteUser = new JButton("PROMOTE USER");
	JButton btnViewReports = new JButton("VIEW REPORTS");
	JButton btnLogOut = new JButton("LOG OUT");

	ManagerFrame() {
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		addActionEvent();

	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		btnPlaceOrder.setBounds(139, 49, 170, 25);
		btnAddBook.setBounds(139, 88, 170, 25);
		btnModifyBook.setBounds(139, 125, 170, 25);
		btnManageOrders.setBounds(139, 162, 170, 25);
		btnPromoteUser.setBounds(139, 199, 170, 25);
		btnViewReports.setBounds(139, 236, 170, 25);
		btnLogOut.setBounds(342, 27, 96, 15);
	}

	public void addComponentsToContainer() {
		container.add(btnPlaceOrder);
		container.add(btnAddBook);
		container.add(btnModifyBook);
		container.add(btnManageOrders);
		container.add(btnPromoteUser);
		container.add(btnViewReports);
		container.add(btnLogOut);
	}

	public void addActionEvent() {

		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// TO BE FILLED...
			}
		});

		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBookFrame.changeWindow();
				dispose();
			}
		});

		btnModifyBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// TO BE FILLED...
			}
		});

		btnManageOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// TO BE FILLED...
			}
		});

		btnPromoteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// TO BE FILLED...
			}
		});

		btnViewReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// TO BE FILLED...
			}
		});

		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// TO BE FILLED...
			}
		});

	}

	public static void changeWindow() {
		ManagerFrame frame = new ManagerFrame();
		frame.setTitle("Manager Frame");
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}

}

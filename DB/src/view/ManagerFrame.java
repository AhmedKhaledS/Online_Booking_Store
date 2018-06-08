package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.books.viewer.actions.CustomerAddBookAction;
import controller.books.viewer.actions.ManagerAction;
import view.util.WindowChanger;
import view.util.table.frame.definer.MangerOrderTableFrameDefiner;
import view.util.table.frame.definer.PromoteUserTableFrameDefiner;

public class ManagerFrame extends JFrame implements WindowChanger {

	Container container = getContentPane();

	JButton btnPlaceOrder = new JButton("PLACE ORDER");
	JButton btnAddItem = new JButton("ADD ITEM");
	JButton btnModifyItem = new JButton("MODIFY ITEM");
	JButton btnManageOrders = new JButton("MANAGE ORDERS");
	JButton btnPromoteUser = new JButton("PROMOTE USER");
	JButton btnViewReports = new JButton("VIEW REPORTS");
	JButton btnLogOut = new JButton("LOG OUT");
	JButton btnEditProfile = new JButton("EDIT PROFILE");

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
		btnAddItem.setBounds(139, 88, 170, 25);
		btnModifyItem.setBounds(139, 125, 170, 25);
		btnManageOrders.setBounds(139, 162, 170, 25);
		btnPromoteUser.setBounds(139, 199, 170, 25);
		btnViewReports.setBounds(139, 236, 170, 25);
		btnEditProfile.setBounds(139, 270, 170, 25);
		btnLogOut.setBounds(342, 27, 96, 15);
	}

	public void addComponentsToContainer() {
		container.add(btnPlaceOrder);
		container.add(btnAddItem);
		container.add(btnModifyItem);
		container.add(btnManageOrders);
		container.add(btnPromoteUser);
		container.add(btnViewReports);
		container.add(btnEditProfile);
		container.add(btnLogOut);
	}

	public void addActionEvent() {

		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerAddBookAction customerAddBookAction = new CustomerAddBookAction();
				BooksViewerFrame.changeWindow("ADD", customerAddBookAction);
				dispose();
			}
		});

		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddItemFrame.changeWindow();
				dispose();
			}
		});

		btnModifyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BooksViewerFrame.changeWindow("EDIT", new ManagerAction());
				dispose();
			}
		});

		btnManageOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableFrame.changeWindow(new MangerOrderTableFrameDefiner());
				dispose();
			}
		});

		btnPromoteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableFrame.changeWindow(new PromoteUserTableFrameDefiner());
				dispose();
			}
		});

		btnViewReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnEditProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditProfileFrame.changeWindow();
				dispose();
			}
		});

		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?",
						"Exit Program Message Box", JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					StartUpFrame.changeWindow();
					dispose();
				}
			}
		});

	}

	public static void changeWindow() {
		ManagerFrame frame = new ManagerFrame();
		frame.setTitle("Options");
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setResizable(false);
	}

	public static void main(String[] args) {
		ManagerFrame.changeWindow();
	}
}
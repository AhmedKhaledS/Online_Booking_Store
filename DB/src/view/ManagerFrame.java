package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.books.viewer.actions.ManagerEditBookAction;
import view.util.WindowChanger;
import view.util.table.frame.definer.BooksEditorTableFrameDefiner;
import view.util.table.frame.definer.BooksOrderTableFrameDefiner;
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
		int mainX = 385;
		int mainY = 120;
		int offset = 54;
		int mainSizeX = 190;
		int mainSizeY = 35;
		btnPlaceOrder.setBounds(mainX, mainY, mainSizeX, mainSizeY);
		btnAddItem.setBounds(mainX, mainY + offset * 1, mainSizeX, mainSizeY);
		btnModifyItem.setBounds(mainX, mainY + offset * 2, mainSizeX, mainSizeY);
		btnManageOrders.setBounds(mainX, mainY + offset * 3, mainSizeX, mainSizeY);
		btnPromoteUser.setBounds(mainX, mainY + offset * 4, mainSizeX, mainSizeY);
		btnViewReports.setBounds(mainX, mainY + offset * 5, mainSizeX, mainSizeY);
		btnEditProfile.setBounds(mainX, mainY + offset * 6, mainSizeX, mainSizeY);
		btnLogOut.setBounds(833, 27, 96, 25);
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
		
		MouseListener m = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(e.getX());	
				System.out.println(e.getY());				

			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		container.addMouseListener(m);

		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableFrame.changeWindow(new BooksOrderTableFrameDefiner());
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
				TableFrame.changeWindow(new BooksEditorTableFrameDefiner());
				// BooksViewerFrame.changeWindow("", new ManagerEditBookAction());
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
		frame.setBounds(200, 80, 950, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setResizable(false);
	}

	public static void main(String[] args) {
		ManagerFrame.changeWindow();
	}
}
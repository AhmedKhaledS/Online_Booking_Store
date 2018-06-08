package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.util.WindowChanger;
import view.util.table.frame.definer.BooksOrderTableFrameDefiner;

public class UserFrame extends JFrame implements WindowChanger {

	Container container = getContentPane();

	JButton btnPlaceOrder = new JButton("PLACE ORDER");
	JButton btnEditProfile = new JButton("EDIT PROFILE");
	JButton btnLogOut = new JButton("LOG OUT");

	UserFrame() {
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		addActionEvent();

	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		btnPlaceOrder.setBounds(339, 150, 200, 50);
		btnEditProfile.setBounds(339, 270, 200, 50);
		btnLogOut.setBounds(500, 27, 100, 25);
	}

	public void addComponentsToContainer() {
		container.add(btnPlaceOrder);
		container.add(btnEditProfile);
		container.add(btnLogOut);
	}

	public void addActionEvent() {

		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableFrame.changeWindow(new BooksOrderTableFrameDefiner());
				dispose();
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
		UserFrame frame = new UserFrame();
		frame.setTitle("Options");
		frame.setVisible(true);
		frame.setBounds(200, 80, 950, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setResizable(false);
	}

	public static void main(String[] args) {
		UserFrame.changeWindow();
	}
}
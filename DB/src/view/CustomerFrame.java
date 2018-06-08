package view;

import view.util.WindowChanger;
import view.util.table.frame.definer.BooksEditorTableFrameDefiner;
import view.util.table.frame.definer.BooksOrderTableFrameDefiner;
import view.util.table.frame.definer.MangerOrderTableFrameDefiner;
import view.util.table.frame.definer.PromoteUserTableFrameDefiner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerFrame extends JFrame implements WindowChanger {

	Container container = getContentPane();

	JButton btnPlaceOrder = new JButton("PLACE ORDER");
	JButton btnLogOut = new JButton("LOG OUT");
	JButton btnEditProfile = new JButton("EDIT PROFILE");

	CustomerFrame() {
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
		btnEditProfile.setBounds(139, 88, 170, 25);
		btnLogOut.setBounds(342, 27, 96, 15);
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
		CustomerFrame frame = new CustomerFrame();
		frame.setTitle("Options");
		frame.setVisible(true);
		frame.setBounds(200, 80, 950, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setResizable(false);
	}

	public static void main(String[] args) {
		CustomerFrame.changeWindow();
	}
}
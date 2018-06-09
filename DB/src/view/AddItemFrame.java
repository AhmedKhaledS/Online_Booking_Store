package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.books.editor.actions.AddAction;
import view.util.WindowChanger;

public class AddItemFrame extends JFrame implements WindowChanger {

	Container container = getContentPane();

	private JButton buttonAddBook = new JButton("ADD NEW BOOK");
	private JButton btnAddPublisher = new JButton("ADD NEW PUBLISHER");
	private JButton btnBack = new JButton("BACK");

	AddItemFrame() {
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
		int mainY = 220;
		int offset = 70;
		int mainSizeX = 190;
		int mainSizeY = 35;
		buttonAddBook.setBounds(mainX, mainY, mainSizeX, mainSizeY);
		btnAddPublisher.setBounds(mainX, mainY + offset, mainSizeX, mainSizeY);
		btnBack.setBounds(39, 550, 96, 25);
	}

	public void addComponentsToContainer() {
		container.add(buttonAddBook);
		container.add(btnAddPublisher);
		container.add(btnBack);
	}

	public void addActionEvent() {

		btnAddPublisher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPublisherFrame.changeWindow();
				dispose();
			}
		});

		buttonAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBookFrame.changeWindow(new AddAction());
				dispose();
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerFrame.changeWindow();
				dispose();
			}
		});

	}

	public static void changeWindow() {
		AddItemFrame frame = new AddItemFrame();
		frame.setTitle("Add Item");
		frame.setVisible(true);
		frame.setBounds(200, 80, 950, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setResizable(false);
	}

}
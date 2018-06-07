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

	private JButton buttonAddBook = new JButton("ADD BOOK");
	private JButton btnAddPublisher = new JButton("ADD PUBLISHER");
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
		buttonAddBook.setBounds(145, 153, 143, 54);
		btnAddPublisher.setBounds(145, 75, 143, 54);
		btnBack.setBounds(12, 263, 117, 25);
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
				/// TO BE FILLED...
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setResizable(false);
	}

}
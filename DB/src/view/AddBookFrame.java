package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.DatabaseConnector;
import view.util.WindowChanger;

public class AddBookFrame extends JFrame implements WindowChanger {

	Container container = getContentPane();

	private JLabel lblIsbn = new JLabel("ISBN");
	private JLabel lblTitle = new JLabel("Title");
	private JLabel lblPublisher = new JLabel("Publisher");
	private JLabel lblYear = new JLabel("Year");
	private JLabel lblCategory = new JLabel("Category");
	private JLabel lblPrice = new JLabel("Price");
	private JLabel lblQunatity = new JLabel("Quantity");
	private JLabel lblThreshold = new JLabel("Minimum");
	private JLabel lblAuthors = new JLabel("Authors");

	private JTextField book_ISBN = new JTextField();
	private JTextField book_tile = new JTextField();
	private JTextField book_publisher = new JTextField();
	private JTextField book_year = new JTextField();
	private JComboBox book_category = new JComboBox();
	private JTextField book_price = new JTextField();
	private JTextField book_quantity = new JTextField();
	private JTextField book_thershold = new JTextField();
	private JTextField book_authors = new JTextField();

	private JButton btnAdd = new JButton("ADD");
	private JButton btnReset = new JButton("RESET");
	private JButton btnBack = new JButton("BACK");
	private JButton btnViewPublishers = new JButton("View Publishers");

	AddBookFrame() {
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		addActionEvent();
	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {

		lblIsbn.setBounds(35, 14, 70, 15);
		lblTitle.setBounds(35, 62, 70, 15);
		lblPublisher.setBounds(12, 110, 67, 15);
		lblYear.setBounds(35, 161, 70, 15);
		lblCategory.setBounds(230, 14, 70, 15);
		lblPrice.setBounds(242, 62, 70, 15);
		lblQunatity.setBounds(230, 110, 70, 15);
		lblThreshold.setBounds(229, 161, 70, 15);
		lblAuthors.setBounds(74, 198, 70, 15);

		book_ISBN.setBounds(97, 12, 114, 19);
		book_tile.setBounds(97, 60, 114, 19);
		book_publisher.setBounds(97, 108, 114, 19);
		book_year.setBounds(97, 159, 114, 19);
		book_category.setModel(
				new DefaultComboBoxModel(new String[] { "Science", "Art", "Religion", "History", "Geography" }));
		book_category.setBounds(307, 9, 104, 24);
		book_price.setBounds(307, 60, 114, 19);
		book_quantity.setBounds(307, 108, 114, 19);
		book_thershold.setBounds(307, 159, 114, 19);
		book_authors.setBounds(179, 196, 180, 19);

		btnAdd.setBounds(242, 242, 77, 25);
		btnReset.setBounds(333, 242, 77, 25);
		btnBack.setBounds(12, 263, 117, 25);
		btnViewPublishers.setBounds(22, 130, 176, 19);

	}

	public void addComponentsToContainer() {
		container.add(book_ISBN);
		container.add(book_tile);
		container.add(book_publisher);
		container.add(book_year);
		container.add(book_category);
		container.add(book_price);
		container.add(book_quantity);
		container.add(book_thershold);
		container.add(book_authors);

		container.add(btnAdd);
		container.add(btnReset);
		container.add(btnBack);
		container.add(btnViewPublishers);

		container.add(lblIsbn);
		container.add(lblTitle);
		container.add(lblPublisher);
		container.add(lblYear);
		container.add(lblCategory);
		container.add(lblPrice);
		container.add(lblQunatity);
		container.add(lblThreshold);
		container.add(lblAuthors);

	}

	public void addActionEvent() {
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String authors[] = book_authors.getText().split(",");
				String data[] = new String[8];
				data[0] = book_ISBN.getText();
				data[1] = book_tile.getText();
				data[2] = book_publisher.getText();
				data[3] = book_year.getText();
				data[4] = (String) book_category.getSelectedItem();
				data[5] = book_price.getText();
				data[6] = book_quantity.getText();
				data[7] = book_thershold.getText();

				String add_book_sql = "INSERT INTO BOOK VALUES (" + data[0] + "," + "'" + data[1] + "'" + "," + data[2]
						+ "," + "'" + data[3] + "'" + "," + "'" + data[4] + "'" + "," + data[5] + "," + data[6] + ","
						+ data[7] + ");";

				if (DatabaseConnector.executeModify(add_book_sql)) {

					String add_author_sql;
					for (int i = 0; i < authors.length; i++) {
						add_author_sql = "INSERT INTO BOOK_AUTHORS VALUES (" + data[0] + "," + "'" + authors[i] + "'"
								+ ");";
						DatabaseConnector.executeModify(add_author_sql);
					}

					JOptionPane.showMessageDialog(container, "Added Successfully");
					reset_entries();
				} else {
					JOptionPane.showMessageDialog(container, "Error! Please Try Again");
				}

			}
		});

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset_entries();
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddItemFrame.changeWindow();
				dispose();
			}
		});

	}

	private void reset_entries() {
		book_ISBN.setText("");
		book_tile.setText("");
		book_publisher.setText("");
		book_year.setText("");
		book_category.setSelectedIndex(0);
		book_price.setText("");
		book_quantity.setText("");
		book_thershold.setText("");
		book_authors.setText("");
	}

	public static void changeWindow() {
		AddBookFrame frame = new AddBookFrame();
		frame.setTitle("Add New Book");
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setResizable(false);
	}

	public static void main(String[] args) {
		AddBookFrame.changeWindow();
	}
}
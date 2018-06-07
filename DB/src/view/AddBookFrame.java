package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.books.editor.actions.AddAction;
import controller.books.editor.actions.EditorAction;
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

	private JButton btnAdd = new JButton();
	private JButton btnReset = new JButton("RESET");
	private JButton btnBack = new JButton("BACK");
	private JButton btnViewPublishers = new JButton("View Publishers");

	AddBookFrame(EditorAction editorAction) {

		String btnType = editorAction.get_target_button_name();
		btnAdd.setText(btnType);

		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();

		if (btnType == "APPLY")
			initializeData(editorAction.get_initial_data());

		addActionEvent(editorAction);
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

	public void addActionEvent(EditorAction editorAction) {
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data[] = fetch_data();
				editorAction.target_button_action(data);
			}
		});

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset_entries();
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorAction.back_button_action();
				dispose();
			}
		});

	}

	private void initializeData(String[] data) {
		book_ISBN.setText(data[0]);
		book_tile.setText(data[1]);
		book_publisher.setText(data[2]);
		book_year.setText(data[3]);
		book_category.setSelectedItem(data[4]);
		book_price.setText(data[5]);
		book_quantity.setText(data[6]);
		book_thershold.setText(data[7]);
		book_authors.setText(data[8]);
	}

	private String[] fetch_data() {
		String data[] = new String[9];
		data[0] = book_ISBN.getText();
		data[1] = book_tile.getText();
		data[2] = book_publisher.getText();
		data[3] = book_year.getText();
		data[4] = (String) book_category.getSelectedItem();
		data[5] = book_price.getText();
		data[6] = book_quantity.getText();
		data[7] = book_thershold.getText();
		data[8] = book_authors.getText();
		return data;
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

	public static void changeWindow(EditorAction editorAction) {
		AddBookFrame frame = new AddBookFrame(editorAction);
		frame.setTitle("Add New Book");
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setResizable(false);
	}

	public static void main(String[] args) {
		AddBookFrame.changeWindow(new AddAction());
	}

	private void edit_action() {

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

		String update_query = "UPDATE BOOK SET ISBN=?,Title=?,Publisher_id=?,Publication_year=?,Category=?,Price=?,No_of_copies=?,Min_Quantity=?,WHERE ISBN=?";

		update_query.replaceFirst("?", data[0]);
		update_query.replaceFirst("?", '\'' + data[1] + '\'');
		update_query.replaceFirst("?", data[2]);
		update_query.replaceFirst("?", '\'' + data[3] + '\'');
		update_query.replaceFirst("?", '\'' + data[4] + '\'');
		update_query.replaceFirst("?", data[5]);
		update_query.replaceFirst("?", data[6]);
		update_query.replaceFirst("?", data[7]);

		System.out.println(update_query);
	}
	
}
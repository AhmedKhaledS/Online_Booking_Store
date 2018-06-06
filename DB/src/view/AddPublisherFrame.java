package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.DatabaseConnector;
import view.util.WindowChanger;

public class AddPublisherFrame extends JFrame implements WindowChanger {

	Container container = getContentPane();

	private JLabel lblId = new JLabel("ID");
	private JLabel lblName = new JLabel("Name");
	private JLabel lblAddress = new JLabel("Address");
	private JLabel lblPhone = new JLabel("Phone");

	private JTextField publisher_id = new JTextField();
	private JTextField publisher_name = new JTextField();
	private JTextField publisher_phone = new JTextField();
	private JTextField publisher_address = new JTextField();

	private JButton btnAdd = new JButton("ADD");
	private JButton btnReset = new JButton("RESET");
	private JButton btnBack = new JButton("BACK");

	AddPublisherFrame() {
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		addActionEvent();
	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {

		lblId.setBounds(65, 43, 70, 15);
		lblName.setBounds(65, 94, 70, 15);
		lblAddress.setBounds(65, 145, 70, 15);
		lblPhone.setBounds(65, 196, 70, 15);

		publisher_id.setBounds(164, 41, 114, 19);
		publisher_name.setBounds(164, 92, 114, 19);
		publisher_address.setBounds(164, 143, 114, 19);
		publisher_phone.setBounds(164, 194, 114, 19);

		btnAdd.setBounds(242, 242, 77, 25);
		btnReset.setBounds(333, 242, 77, 25);
		btnBack.setBounds(12, 263, 117, 25);

	}

	public void addComponentsToContainer() {

		container.add(lblId);
		container.add(lblName);
		container.add(lblAddress);
		container.add(lblPhone);

		container.add(publisher_id);
		container.add(publisher_name);
		container.add(publisher_address);
		container.add(publisher_phone);

		container.add(btnAdd);
		container.add(btnReset);
		container.add(btnBack);

	}

	public void addActionEvent() {
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String data[] = new String[4];
				data[0] = publisher_id.getText();
				data[1] = publisher_name.getText();
				data[2] = publisher_address.getText();
				data[3] = publisher_phone.getText();

				String add_publisher_sql = "INSERT INTO PUBLISHER VALUES (" + data[0] + "," + "'" + data[1] + "'" + ","
						+ "'" + data[2] + "'" + "," + "'" + data[3] + "'" + ");";

				System.out.println(add_publisher_sql);

				if (DatabaseConnector.executeModify(add_publisher_sql)) {
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
		publisher_id.setText("");
		publisher_name.setText("");
		publisher_address.setText("");
		publisher_phone.setText("");
	}

	public static void changeWindow() {
		AddPublisherFrame frame = new AddPublisherFrame();
		frame.setTitle("Add New Publisher");
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setResizable(false);
	}

}

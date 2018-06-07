package view;

import controller.DatabaseConnector;
import view.util.GUIConstants;
import view.util.WindowChanger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.util.GUIConstants.*;

public class SignUpFrame extends JFrame implements ActionListener, WindowChanger {

    Container container = getContentPane();
    // Labels
    JLabel userLabel = new JLabel("Username");
    JLabel emailLabel = new JLabel("E-Mail");
    JLabel nameLabel = new JLabel("Name");
    JLabel phoneLabel = new JLabel("Phone");
    JLabel shoppingAddressLabel = new JLabel("Shopping Address");
    JLabel passwordLabel = new JLabel("Password");
    JLabel confirmPasswordLabel = new JLabel("Confirm Password");
    JLabel accountTypeLabel = new JLabel("Account Type");
    // Error Label to display error in sign up info
    JLabel errorLabel = new JLabel("");

    // Text fields
    JTextField userTextField = new JTextField();
    JTextField emailTextField = new JTextField();
    JTextField nameTextField = new JTextField();
    JTextField phoneTextField = new JTextField();
    JTextField shoppingAddressTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField confirmPasswordField = new JPasswordField();
    // Buttons
    JButton signUpButton = new JButton("Sign Up");
    JButton resetButton = new JButton("Reset");

    //Radio Buttons for Account Type
    JRadioButton customerRB = new JRadioButton("Customer");
    JRadioButton managerRB = new JRadioButton("Manager");

    SignUpFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {

        nameLabel.setBounds(initX, initY,
                GUIConstants.width, GUIConstants.height);
        nameTextField.setBounds(initX + offsetX, initY, width, height);

        userLabel.setBounds(initX , initY + offsetY, width, height);
        userTextField.setBounds(initX + offsetX, initY + offsetY, width, height);

        emailLabel.setBounds(initX , initY + 2 * offsetY, width, height);
        emailTextField.setBounds(initX + offsetX, initY + 2 * offsetY, width, height);

        passwordLabel.setBounds(initX, initY + 3 * offsetY, width, height);
        passwordField.setBounds(initX + offsetX, initY + 3 * offsetY, width, height);

        confirmPasswordLabel.setBounds(initX, initY + 4 * offsetY, width, height);
        confirmPasswordField.setBounds(initX + offsetX, initY + 4 * offsetY, width, height);

        phoneLabel.setBounds(initX, initY + 5 * offsetY, width, height);
        phoneTextField.setBounds(initX + offsetX, initY + 5 * offsetY, width, height);

        shoppingAddressLabel.setBounds(initX, initY + 6 *offsetY, width, height);
        shoppingAddressTextField.setBounds(initX + offsetX, initY + 6 *offsetY, width, height);

        accountTypeLabel.setBounds(initX, initY + 7 * offsetY, width, height);
        customerRB.setBounds(initX + 100, initY + 7 * offsetY, width, height);
        managerRB.setBounds(initX + width * 2, initY + offsetY * 7, width, height);
        ButtonGroup group = new ButtonGroup();
        group.add(customerRB);
        group.add(managerRB);


        signUpButton.setBounds(initX, initY + 8 * offsetY, width, height);
        resetButton.setBounds(initX + offsetX, initY + 8 * offsetY, width, height);

        errorLabel.setBounds(initX, initY + offsetY * 9, width, height);
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(confirmPasswordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(confirmPasswordField);
        container.add(signUpButton);
        container.add(resetButton);
        container.add(nameLabel);
        container.add(nameTextField);
        container.add(emailLabel);
        container.add(emailTextField);
        container.add(phoneLabel);
        container.add(phoneTextField);
        container.add(shoppingAddressLabel);
        container.add(shoppingAddressTextField);
        container.add(customerRB);
        container.add(managerRB);
        container.add(accountTypeLabel);
        container.add(errorLabel);
    }

    public void addActionEvent() {
        signUpButton.addActionListener(this);
        resetButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        errorLabel.setText("");
        //Coding Part of LOGIN button
        if (e.getSource() == signUpButton) {
            String userText;
            String pwdText;
            String confirmedPwdText;
            userText = userTextField.getText();
            pwdText = new String (passwordField.getPassword());
            confirmedPwdText = new String (confirmPasswordField.getPassword());
            if (!pwdText.equals(confirmedPwdText)) {
                errorLabel.setText("Password and confirmed password do not match !");
            }

        } else if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
        }

        passwordField.setEchoChar('*');
        confirmPasswordField.setEchoChar('*');
    }

    public static void changeWindow () {
        SignUpFrame frame = new SignUpFrame();
        frame.setTitle("Sign Up Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    public static void main (String[] args) {
        SignUpFrame.changeWindow();

    }
}


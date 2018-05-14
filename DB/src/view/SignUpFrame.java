package view;

import controller.DatabaseConnector;
import view.util.WindowChanger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpFrame extends JFrame implements ActionListener, WindowChanger {

    Container container = getContentPane();
    // Labels
    JLabel userLabel = new JLabel("E-mail");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JLabel confirmPasswordLabel = new JLabel("Confirm PASSWORD");
    // Error Label to display error in sign up info
    JLabel errorLabel = new JLabel("");

    // Text fields
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField confirmPasswordField = new JPasswordField();
    // Buttons
    JButton signUpButton = new JButton("Sign Up");
    JButton resetButton = new JButton("RESET");

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
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        confirmPasswordLabel.setBounds(50, 300, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        confirmPasswordField.setBounds(150, 300, 150, 30);
        signUpButton.setBounds(50, 370, 100, 30);
        resetButton.setBounds(200, 370, 100, 30);

        errorLabel.setBounds(50, 420, 200, 40);
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
            pwdText = passwordField.getText();
            confirmedPwdText = confirmPasswordField.getText();
            if (!pwdText.equals(confirmedPwdText)) {
                errorLabel.setText("Password and confirmed password do not match !");
            }
        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
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
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}

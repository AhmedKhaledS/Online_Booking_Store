package view;

import view.util.WindowChanger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUpFrame extends JFrame implements ActionListener, WindowChanger {

    Container container = getContentPane();
    JLabel welcome = new JLabel("Welcome");
    // Buttons
    JButton loginButton = new JButton("LOGIN");
    JButton signUpButton = new JButton("Sign Up");

    StartUpFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        welcome.setBounds(50, 150, 100, 30);
        loginButton.setBounds(50, 200, 100, 30);
        signUpButton.setBounds(50, 250, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(welcome);
        container.add(loginButton);
        container.add(signUpButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        signUpButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        if (e.getSource() == loginButton) {
            LoginFrame.changeWindow();
        }
        //Coding Part of RESET button
        if (e.getSource() == signUpButton) {
            SignUpFrame.changeWindow();
        }
    }

    public static void changeWindow () {
        StartUpFrame frame = new StartUpFrame();
        frame.setTitle("Welcome");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}



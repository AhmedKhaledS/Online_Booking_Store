package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AppMainScreen {
    public static void main(String[] a) {
        try {
            Class.forName("controller.DatabaseConnector");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        src.view.StartUpFrame.changeWindow();
    }

}

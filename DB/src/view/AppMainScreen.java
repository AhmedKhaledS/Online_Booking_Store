package view;

import javax.swing.*;

public class AppMainScreen {
    public static void main(String[] a) {
        try {
            Class.forName("controller.DatabaseConnector");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        StartUpFrame.changeWindow();
    }

}

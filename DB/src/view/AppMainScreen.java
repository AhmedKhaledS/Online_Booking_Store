package view;

public class AppMainScreen {
    public static void main(String[] a) {
        try {
            Class.forName("controller.DatabaseConnector");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        StartUpFrame.changeWindow();
    }

}

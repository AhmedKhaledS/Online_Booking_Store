package controller.books.query;

import controller.users.UserProfileController;
import model.CustomerOrdersModel;
import model.UserOrderDataModel;

import javax.swing.*;

public class BookOrdersCustomerController {

    public static void insertOrder(String[] order) {
        UserOrderDataModel userOrder = new UserOrderDataModel(order);
        boolean isInserted =  CustomerOrdersModel.getInstance().insertOrder(userOrder);
        if (isInserted) {
            JOptionPane.showMessageDialog(null, "You order is placed successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Error occurred while inserting order!");
        }
    }
}

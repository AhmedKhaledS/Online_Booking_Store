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

    public static void confirmOrders() {
        if (CustomerOrdersModel.getInstance().confirmOrders()) {
            JOptionPane.showMessageDialog(null, "Orders are confirmed successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Error occurred while confirming order!");
        }
    }

    public static void deleteOrders() {
        CustomerOrdersModel.getInstance().deleteOrders();
    }

    public static void deleteSpecificOrder(String email, String orderID) {
        boolean success = CustomerOrdersModel.getInstance().deleteSpecificOrder(email, orderID);
        if (!success) {
            JOptionPane.showMessageDialog(null, "Error occurred while deleting specific order!");
        }
    }
}

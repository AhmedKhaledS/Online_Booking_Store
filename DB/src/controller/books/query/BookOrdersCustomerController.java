package controller.books.query;

import controller.users.UserProfileController;
import model.CustomerOrdersModel;
import model.UserOrderDataModel;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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

    public static Vector<Vector<String>> getSpecificOrder(String email) {
        ResultSet orders = CustomerOrdersModel.getInstance().getSpecificOrders(email);
        Vector<Vector<String>> data = new Vector<>();
        try {
            while (orders.next()) {
                Vector<String> dataRow = new Vector<>();
                dataRow.add(orders.getString(1));  // Email
                dataRow.add(String.valueOf(orders.getInt(2))); // ISBN
                dataRow.add(String.valueOf(orders.getInt(3))); // Quantity
                dataRow.add(orders.getString(4));  // State
                dataRow.add(orders.getString(/**/5)); // Date
                dataRow.add(String.valueOf(orders.getInt(6))); // Order_Id
                data.add(dataRow);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return data;
    }
}

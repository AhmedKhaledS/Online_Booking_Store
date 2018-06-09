package model;

import controller.DatabaseConnector;

public class CustomerOrdersModel extends UserOrder {
    private static CustomerOrdersModel ourInstance = new CustomerOrdersModel();

    public static CustomerOrdersModel getInstance() {
        return ourInstance;
    }

    private CustomerOrdersModel() {
    }

    public boolean deleteSpecificOrder(String email, String orderID) {
        String deletionStmt = "DELETE FROM `ORDER` WHERE `E_mail`  = '"
                + email + "' AND `Order_id` = '" + orderID + "';";
        if (DatabaseConnector.executeModify(deletionStmt)) {
            return true;
        }
        return false;
    }
}

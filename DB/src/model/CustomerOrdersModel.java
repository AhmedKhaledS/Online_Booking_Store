package model;

public class CustomerOrdersModel extends UserOrder {
    private static CustomerOrdersModel ourInstance = new CustomerOrdersModel();

    public static CustomerOrdersModel getInstance() {
        return ourInstance;
    }

    private CustomerOrdersModel() {
    }
}

package view.util.table.frame.definer;

import controller.books.query.BookOrdersCustomerController;
import controller.books.viewer.actions.CustomerRemoveBookAction;
import controller.users.UserProfileController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import static view.util.GUIConstants.*;

public class ShoppingCartTableFrameDefiner extends TableFrameDefiner implements ActionListener {

    private JButton checkoutShoppingCartButton;
    private JLabel totalPriceLabel;
    private JLabel errorLabel;

    private JLabel creditCardLabel = new JLabel("Credit Card No");
    private JTextField creditCardNo = new JTextField();
    private JLabel dateLabel = new JLabel("Expiry Date");
    private JTextField dateTextField = new JTextField();

    private Vector<Vector<String>> data;

    public ShoppingCartTableFrameDefiner (Vector<Vector<String>> data) {
        this.action = new CustomerRemoveBookAction(data);
        ((CustomerRemoveBookAction) this.action).setTableFrameDefine(this);
        this.data = data;

    }


    @Override
    public void update(Vector<Vector<String>> data) {
        this.data = data;
        totalPriceLabel.setText("Total Price : " + getTotalPriceString());
        System.out.println(getTotalPriceString());
    }

    @Override
    public Vector<String> defineTableAttributes() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add(action.getActionName());
        columnNames.addAll(Constants.getBookTableColumnNames());
        columnNames.set(7, "Quantity");
        columnNames.remove(8);
        columnNames.add("Order_Id"); // 8
        return columnNames;
    }

    public String getTotalPriceString () {
        long price = 0;
        for (Vector<String> dataRow : data) {
            long unitPrice = Long.parseLong(dataRow.get(6));
            long quantity = Long.parseLong(dataRow.get(7));
            price += unitPrice  * quantity;
        }
        return String.valueOf(price);
    }

    @Override
    public void modifyFrame(Container container) {
        preProcessData();
        this.definableTableFrame.setData(data);
        totalPriceLabel = new JLabel("Total Price : " + getTotalPriceString());
        errorLabel = new JLabel("");
        checkoutShoppingCartButton = new JButton("Check Out");
        checkoutShoppingCartButton.setBounds(initX, initY, width, height);
        totalPriceLabel.setBounds(initX, initY + offsetY * 7, width, height);
        creditCardLabel.setBounds(initX , initY + offsetY * 8, width, height);
        creditCardNo.setBounds(initX + offsetX, initY + offsetY * 8, width, height);
        dateLabel.setBounds(initX + offsetX * 2, initY + offsetY * 8, width, height);
        dateTextField.setBounds(initX + offsetX * 3, initY + offsetY * 8, width, height);
        errorLabel.setBounds(initX + offsetX, initY, width, height);
        checkoutShoppingCartButton.addActionListener(this);
        container.add(checkoutShoppingCartButton);
        container.add(totalPriceLabel);
        container.add(errorLabel);
        container.add(creditCardNo);
        container.add(dateTextField);
        container.add(dateLabel);
        container.add(creditCardLabel);
    }

    private boolean isValidCreditCardInfo () {
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkoutShoppingCartButton) {
            data = this.action.getData();
            if (data.size() == 0) {
                errorLabel.setText("Error Empty Shopping Cart");
                return;
            }
            if (!isValidCreditCardInfo ()) {
                errorLabel.setText("Error Invalid Credit Card Info");
            }
            BookOrdersCustomerController.confirmOrders();
        }
    }

    private void preProcessData () {
        Vector<Vector<String>> databaseData = BookOrdersCustomerController.
                getSpecificOrder(UserProfileController.getInstance().getCurrentLoggedInUser().getEmail());
        int index = 0;
        for (Vector<String> dataRow : data) {
            dataRow.add(0, "Remove");
            dataRow.remove(7);
            dataRow.remove(7);
            dataRow.add(databaseData.get(index).get(5));
            index++;
        }
        return;
    }
}

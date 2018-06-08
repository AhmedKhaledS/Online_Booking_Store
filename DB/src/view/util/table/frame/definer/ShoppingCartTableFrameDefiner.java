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
    private Vector<Vector<String>> data;

    public ShoppingCartTableFrameDefiner (Vector<Vector<String>> data) {
        this.action = new CustomerRemoveBookAction();
        this.data = data;
    }

    @Override
    public void update(Object eventSource) {

    }

    @Override
    public Vector<String> defineTableAttributes() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add(action.getActionName());
        columnNames.addAll(Constants.getBookTableColumnNames());
        columnNames.set(7, "Quantity");
        columnNames.remove(8);
        return columnNames;
    }

    @Override
    public void modifyFrame(Container container) {
        preProcessData();
        this.definableTableFrame.setData(data);
        checkoutShoppingCartButton = new JButton("Check Out");
        checkoutShoppingCartButton.setBounds(initX, initY, width, height);
        checkoutShoppingCartButton.addActionListener(this);
        container.add(checkoutShoppingCartButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkoutShoppingCartButton) {
            for (Vector<String> dataRow : data) {
                String[] insertOrderParameters = new String[4];
                insertOrderParameters[0] = UserProfileController.getInstance()
                        .getCurrentLoggedInUser().getEmail();
                insertOrderParameters[1] =  dataRow.get(1);
                insertOrderParameters[2] = dataRow.get(dataRow.size() - 1);
                insertOrderParameters[3] = "IN_PROGRESS";
                BookOrdersCustomerController.insertOrder(insertOrderParameters);
            }

        }
    }

    private void preProcessData () {
        for (Vector<String> dataRow : data) {
            dataRow.add(0, "Remove");
            dataRow.remove(7);
            dataRow.remove(7);
        }
        return;
    }
}

package controller.books.viewer.actions;

import controller.books.query.BookOrdersCustomerController;
import controller.users.UserProfileController;
import view.util.GUIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class CustomerAddBookAction extends UserAction {

    private Vector<Vector<String>> shoppingCart;

    public CustomerAddBookAction() {
        this.actionName = "Add";
        shoppingCart = new Vector<>();
        this.targetUser = TargetUser.CUSTOMER;
    }

    @Override
    public void accept(JTable jTable, Integer row) {
        Vector<String> dataRow = new Vector<>();
        Vector<String> orderRow = new Vector<>();
        for (int i = 1; i < jTable.getModel().getColumnCount(); i++) {
            dataRow.add((String) jTable.getModel().getValueAt(row, i));
            orderRow.add((String) jTable.getModel().getValueAt(row, i));
        }
        shoppingCart.add(dataRow);
        placeOrder(orderRow);
    }

    @Override
    public void addToFrame(Container container) {
        JButton viewShoppingCartButton = new JButton("View Shopping Cart");
        /// TODO : Switch to Shopping Cart Frame
        viewShoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        viewShoppingCartButton.setBounds(GUIConstants.initX, GUIConstants.initY  * 10,
                GUIConstants.width, GUIConstants.height);
        container.add(viewShoppingCartButton);
    }

    @Override
    public Vector<Vector<String>> getData() {
        return getShoppingCart();
    }

    @Override
    public void setData(Vector<Vector<String>> data) {
        shoppingCart = new Vector<>();
    }

    private Vector<Vector<String>> getShoppingCart () {
        return shoppingCart;
    }

    private void placeOrder (Vector<String> dataRow) {
            preProcessData(dataRow);
            String[] insertOrderParameters = new String[5];
            insertOrderParameters[0] = UserProfileController.getInstance()
                    .getCurrentLoggedInUser().getEmail();
            insertOrderParameters[1] =  dataRow.get(1);
            insertOrderParameters[2] = dataRow.get(dataRow.size() - 1);
            insertOrderParameters[3] = "IN_PROGRESS";
            insertOrderParameters[4] = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            if (insertOrderParameters[2] != null) { // Check on the quantity.
                BookOrdersCustomerController.insertOrder(insertOrderParameters);
                return;
            }
            JOptionPane.showMessageDialog(null, "Please specify the quantity!");
    }

    private void preProcessData (Vector<String> dataRow) {
            dataRow.add(0, "Remove");
            dataRow.remove(7);
            dataRow.remove(7);
        return;
    }
}

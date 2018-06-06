package controller.books.viewer.actions;

import view.util.GUIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class CustomerUserAction extends UserAction {

    private Vector<Vector<String>> shoppingCart;

    public CustomerUserAction () {
        shoppingCart = new Vector<>();
        this.targetUser = TargetUser.CUSTOMER;
    }

    @Override
    public void accept(JTable jTable, Integer row) {
        Vector<String> dataRow = new Vector<>();
        for (int i = 1; i < jTable.getModel().getColumnCount(); i++) {
            dataRow.add((String) jTable.getModel().getValueAt(row, i));
        }
        shoppingCart.add(dataRow);
    }

    @Override
    public void addToGUI(Container container) {
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

    public Vector<Vector<String>> getShoppingCart () {
        return shoppingCart;
    }
}

package view.util.table.frame.definer;

import controller.books.viewer.actions.CustomerRemoveBookAction;

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
        this.definableTableFrame.setData(data);
        checkoutShoppingCartButton = new JButton("Check Out");
        checkoutShoppingCartButton.setBounds(initX, initY, width, height);
        checkoutShoppingCartButton.addActionListener(this);
        container.add(checkoutShoppingCartButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkoutShoppingCartButton) {

        }
    }
}

package controller.books.viewer.actions;

import javax.swing.*;
import java.util.Vector;

public class CustomerUserAction extends UserAction {

    private Vector<Vector<String>> shoppingCart;

    public CustomerUserAction () {
        shoppingCart = new Vector<>();
    }

    @Override
    public void accept(JTable jTable, Integer row) {
        Vector<String> dataRow = new Vector<>();
        for (int i = 1; i < jTable.getModel().getColumnCount(); i++) {
            dataRow.add((String) jTable.getModel().getValueAt(row, i));
        }
        shoppingCart.add(dataRow);
    }

    public Vector<Vector<String>> getShoppingCart () {
        return shoppingCart;
    }
}

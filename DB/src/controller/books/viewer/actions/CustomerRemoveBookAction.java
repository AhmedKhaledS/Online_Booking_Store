package controller.books.viewer.actions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class CustomerRemoveBookAction extends UserAction {

    private Vector<Vector<String>> data;

    public CustomerRemoveBookAction () {
        data = new Vector<>();
        this.actionName = "Remove";
        this.targetUser = TargetUser.CUSTOMER;
    }

    @Override
    public void accept(JTable jTable, Integer row) {
        data = new Vector<>();
        ((DefaultTableModel)jTable.getModel()).removeRow(row);
        for (int j = 0; j < jTable.getModel().getRowCount(); j++) {
            Vector<String> dataRow = new Vector<>();
            for (int i = 1; i < jTable.getModel().getColumnCount(); i++) {
                dataRow.add((String) jTable.getModel().getValueAt(j, i));
            }
            data.add(dataRow);
        }
    }

    @Override
    public void addToFrame(Container container) {

    }

    @Override
    public Vector<Vector<String>> getData() {
        return data;
    }

    @Override
    public void setData(Vector<Vector<String>> data) {

    }
}

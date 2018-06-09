package controller.books.viewer.actions;

import view.TableFrame;
import view.util.table.frame.definer.TableFrameDefiner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class CustomerRemoveBookAction extends UserAction {

    private Vector<Vector<String>> data;
    private TableFrameDefiner definer;

    public CustomerRemoveBookAction (Vector<Vector<String>> data) {
        this.data = data;
        this.actionName = "Remove";
        this.targetUser = TargetUser.CUSTOMER;
    }

    @Override
    public void accept(JTable jTable, Integer row) {
        data = new Vector<>();
        /// TODO : Order Undo (Remove from Orders' Table)
        ((DefaultTableModel)jTable.getModel()).removeRow(row);
        for (int j = 0; j < jTable.getModel().getRowCount(); j++) {
            Vector<String> dataRow = new Vector<>();
            for (int i = 0; i < jTable.getModel().getColumnCount(); i++) {
                dataRow.add((String) jTable.getModel().getValueAt(j, i));
            }
            data.add(dataRow);
        }
        definer.update(data);
    }

    public void setTableFrameDefine (TableFrameDefiner definer) {
        this.definer = definer;
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

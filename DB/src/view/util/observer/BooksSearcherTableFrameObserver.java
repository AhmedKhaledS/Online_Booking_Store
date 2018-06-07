package view.util.observer;

import controller.books.viewer.actions.CustomerAction;

import javax.swing.*;
import java.util.Vector;

public class BooksSearcherTableFrameObserver extends TableFrameObserver {

    public BooksSearcherTableFrameObserver(String actionName) {
        this.action = new CustomerAction();
    }

    @Override
    public void update(Object eventSource) {

    }

    @Override
    public Vector<String> defineTableAttributes() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add(action.getActionName());
        columnNames.add("ISBN");
        columnNames.add("ISBN");
        columnNames.add("Title");
        columnNames.add("Publisher Name");
        columnNames.add("Publication Year");
        columnNames.add("Category");
        columnNames.add("Price");
        columnNames.add("No of Copies");
        columnNames.add("Min_Quantity");
        return columnNames;
    }

    @Override
    public void modifyFrame() {

    }
}

package view.util.observer;

import javax.swing.*;
import java.util.Vector;

public class BooksTableFrameObserver extends TableFrameObserver {

    public BooksTableFrameObserver(String actionName) {
        super(actionName);
    }

    @Override
    public void update(Object eventSource) {

    }

    @Override
    public void setObservableTableFrame(ObservableTableFrame observableTableFrame) {

    }

    @Override
    public void defineTableAttributes() {
        JTable table = this.observableTableFrame.getTable();
        Vector<String> columnNames = new Vector<>();
        columnNames.add("ISBN");
        columnNames.add(actionName);
        columnNames.add("ISBN");
        columnNames.add("Title");
        columnNames.add("Publisher Name");
        columnNames.add("Publication Year");
        columnNames.add("Category");
        columnNames.add("Price");
        columnNames.add("No of Copies");
        columnNames.add("Min_Quantity");
    }

    @Override
    public void modifyFrame() {

    }
}

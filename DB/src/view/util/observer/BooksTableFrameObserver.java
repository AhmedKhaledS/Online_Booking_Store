package view.util.observer;

import javax.swing.*;
import java.util.Vector;

public class BooksTableFrameObserver extends TableFrameObserver {

    public BooksTableFrameObserver() {
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
        /// TODO :
    }

    @Override
    public void modifyFrame() {

    }
}

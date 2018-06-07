package view.util.observer;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public interface ObservableTableFrame {

    public void notifyObservers ();

    public Container getFrameContainer ();
    public Vector<Vector<String>> getTableData();
}

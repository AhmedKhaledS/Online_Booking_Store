package view.util.observer;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public interface DefinableTableFrame {

    public Vector<Vector<String>> getTableData();
    public void setData (Vector<Vector<String>> data);

}

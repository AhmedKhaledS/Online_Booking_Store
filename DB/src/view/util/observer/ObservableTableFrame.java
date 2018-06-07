package view.util.observer;

import javax.swing.*;
import java.awt.*;

public interface ObservableTableFrame {

    public void notifyObservers ();

    public Container getFrameContainer ();
    public JTable getTable();
}

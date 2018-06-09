package view.util.table.frame.definer;

import controller.books.viewer.actions.UserAction;

import java.awt.*;
import java.util.Vector;

public abstract class TableFrameDefiner {

    protected UserAction action;

    /** Observer Design Pattern Attribute. */
    protected DefinableTableFrame definableTableFrame;

    public TableFrameDefiner() {}

    /** Observer Design Pattern Method. */
    public abstract void update(Vector<Vector<String>> data);

    public void setDefinableTableFrame(DefinableTableFrame definableTableFrame) {
        this.definableTableFrame = definableTableFrame;
    }

    public abstract Vector<String> defineTableAttributes();

    public UserAction getAction() {
        return action;
    }

    public abstract void modifyFrame(Container container);
}

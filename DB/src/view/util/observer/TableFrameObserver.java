package view.util.observer;

import controller.books.viewer.actions.UserAction;
import view.TableFrame;

import java.util.Vector;

public abstract class TableFrameObserver {

    protected UserAction action;

    /** Observer Design Pattern Attribute. */
    protected ObservableTableFrame observableTableFrame;

    public TableFrameObserver () {}

    /** Observer Design Pattern Method. */
    public abstract void update(Object eventSource);

    public void setObservableTableFrame (ObservableTableFrame observableTableFrame) {
        this.observableTableFrame = observableTableFrame;
    }

    public abstract Vector<String> defineTableAttributes();

    public UserAction getAction() {
        return action;
    }

    public abstract void modifyFrame();
}

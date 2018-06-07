package view.util.observer;

import view.TableFrame;

public abstract class TableFrameObserver {

    protected String actionName;

    /** Observer Design Pattern Attribute. */
    protected ObservableTableFrame observableTableFrame;

    public TableFrameObserver (String actionName) {
        this.actionName = actionName;
    }

    /** Observer Design Pattern Method. */
    public abstract void update(Object eventSource);

    public void setObservableTableFrame (ObservableTableFrame observableTableFrame) {
        this.observableTableFrame = observableTableFrame;
    }

    public abstract void defineTableAttributes();

    public abstract void modifyFrame();
}

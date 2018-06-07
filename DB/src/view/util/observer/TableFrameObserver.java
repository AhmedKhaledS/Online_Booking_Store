package view.util.observer;

public abstract class TableFrameObserver {

    // Observer Design Pattern Attribute and Method.
    protected ObservableTableFrame observableTableFrame;
    public abstract void update(Object eventSource);
    public void setObservableTableFrame (ObservableTableFrame observableTableFrame) {
        this.observableTableFrame = observableTableFrame;
    }

    public abstract void defineTableAttributes();

    public abstract void modifyFrame();
}

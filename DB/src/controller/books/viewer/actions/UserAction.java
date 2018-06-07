package controller.books.viewer.actions;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;

public abstract class UserAction implements BiConsumer<JTable, Integer> {

    protected TargetUser targetUser;
    protected  String actionName;

    @Override
    public abstract void accept(JTable jTable, Integer row);

    public String getActionName() {
        return this.actionName;
    }

    public abstract void addToFrame(Container container);
}


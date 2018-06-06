package controller.books.viewer.actions;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;

public abstract class UserAction implements BiConsumer<JTable, Integer> {

    protected TargetUser targetUser;

    @Override
    public void accept(JTable jTable, Integer row) {}

    public abstract void addToFrame(Container container);
}


package controller.books.viewer.actions;

import javax.swing.*;
import java.util.function.BiConsumer;

abstract class UserAction implements BiConsumer<JTable, Integer> {

    protected TargetUser targetUser;

    @Override
    public void accept(JTable jTable, Integer row) {}
}


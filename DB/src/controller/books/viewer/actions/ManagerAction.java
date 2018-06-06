package controller.books.viewer.actions;

import javax.swing.*;
import java.awt.*;

public class ManagerAction extends UserAction {

    public ManagerAction() {
        this.targetUser = TargetUser.MANAGER;
    }

    @Override
    public void accept(JTable jTable, Integer row) {

    }

    @Override
    public void addToFrame(Container container) {

    }
}

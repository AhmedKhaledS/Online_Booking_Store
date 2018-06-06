package controller.books.viewer.actions;

import javax.swing.*;
import java.awt.*;

public class ManagerUserAction extends UserAction {

    public ManagerUserAction () {
        this.targetUser = TargetUser.MANAGER;
    }

    @Override
    public void accept(JTable jTable, Integer row) {

    }

    @Override
    public void addToFrame(Container container) {

    }
}

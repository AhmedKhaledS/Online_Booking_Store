package controller.books.viewer.actions;

import javax.swing.*;

public class ManagerUserAction extends UserAction {

    public ManagerUserAction () {
        this.targetUser = TargetUser.MANAGER;
    }

    @Override
    public void accept(JTable jTable, Integer row) {

    }
}

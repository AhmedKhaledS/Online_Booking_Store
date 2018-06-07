package controller.books.viewer.actions;

import javax.swing.*;
import java.awt.*;

public class CustomerRemoveBookAction extends UserAction {

    public CustomerRemoveBookAction () {
        this.actionName = "Remove";
        this.targetUser = TargetUser.CUSTOMER;
    }

    @Override
    public void accept(JTable jTable, Integer row) {

    }

    @Override
    public void addToFrame(Container container) {

    }
}

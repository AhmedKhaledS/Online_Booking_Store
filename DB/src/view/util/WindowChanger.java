package view.util;

import view.util.table.frame.definer.TableFrameDefiner;

import java.awt.event.ActionListener;

public interface  WindowChanger {
    public static void changeWindow () {}

    public static void changeWindow (String actionName, ActionListener action) {


    }
    public static void changeWindow (TableFrameDefiner definer) {

    }
}
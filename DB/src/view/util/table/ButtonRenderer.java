package view.util.table;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.function.BiConsumer;

class ButtonRenderer extends JButton implements TableCellRenderer {

    private BiConsumer action;

    public ButtonRenderer(BiConsumer action) {
        setOpaque(true);
        this.action = action;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
            System.out.println("Renderer : Row #" + row);
            action.accept(table, row);
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}
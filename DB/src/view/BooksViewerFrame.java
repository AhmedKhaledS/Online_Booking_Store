package view;

import controller.DatabaseConnector;
import view.util.GUIConstants;
import view.util.WindowChanger;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class BooksViewerFrame extends JFrame implements ActionListener, WindowChanger{

    Container container = getContentPane();
    // Labels
    JLabel searchKeyLabel = new JLabel("Search By");
    JTextField searchKeyTextField = new JTextField();
    String[] keys = {"Title", "Publisher_Name", "Publication_Year", "Category"};
    JComboBox<String> possibleKeys = new JComboBox<>(keys);
    JButton searchButton = new JButton("Search");
    Vector<String> columnNames;
    DefaultTableModel dm = new DefaultTableModel();
    JTable table = new JTable(dm);
    JScrollPane scroll;

    BooksViewerFrame(String actionName, ActionListener action) {
        initializeColumnNames();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent(action);
    }

    private void initializeColumnNames() {
        columnNames = new Vector<>();
        columnNames.add("Add");
        columnNames.add("ISBN");
        columnNames.add("Title");
        columnNames.add("Publisher Name");
        columnNames.add("Publication Year");
        columnNames.add("Category");
        columnNames.add("Price");
        columnNames.add("No of Copies");
        columnNames.add("Min_Quantity");
    }

    private  void setLayoutManager() {
        container.setLayout(null);
    }

    private  void setLocationAndSize() {
        dm.setDataVector(new Vector<>(),columnNames);
        table.getColumn("Add").setCellRenderer(new ButtonRenderer());
        table.getColumn("Add").setCellEditor(new ButtonEditor(new JCheckBox()));
        table.setVisible(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scroll = new JScrollPane(table);
        scroll.setVisible(true);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.getColumnModel().getColumn(0).setPreferredWidth(100);

        searchKeyLabel.setBounds(GUIConstants.initX, GUIConstants.initY,
                GUIConstants.width, GUIConstants.height);
        searchKeyTextField.setBounds(GUIConstants.initX + GUIConstants.offsetX,
                GUIConstants.initY, GUIConstants.width, GUIConstants.height);
        possibleKeys.setBounds(GUIConstants.initX + GUIConstants.offsetX * 2,
                GUIConstants.initY, GUIConstants.width, GUIConstants.height);
        searchButton.setBounds(GUIConstants.initX + GUIConstants.offsetX * 3,
                GUIConstants.initY, GUIConstants.width, GUIConstants.height);
        scroll.setBounds(GUIConstants.initX, GUIConstants.initY + GUIConstants.offsetY,
                GUIConstants.width * 5, GUIConstants.height * 10);
    }

    private  void addComponentsToContainer() {
        container.add(searchKeyLabel);
        container.add(searchKeyTextField);
        container.add(possibleKeys);
        container.add(searchButton);
        container.add(scroll);
    }

    private  void addActionEvent(ActionListener action) {
        searchButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            try {
                Class.forName("controller.DatabaseConnector");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            ResultSet books = DatabaseConnector.executeQuery
                    ("SELECT isbn, title, name, publication_year, category, price, no_of_copies, min_quantity" +
                            " from Book as B join Publisher as P on B.publisher_id = P.publisher_id "+
                                " where B." + String.valueOf(possibleKeys.getSelectedItem()) + " = \"" +
                                    searchKeyTextField.getText() + "\"");

            Vector<Vector<String>> data = new Vector<>();
            try {
                while (books.next()) {
                    Vector<String> dataRow = new Vector<>();
                    dataRow.add("Add");
                    dataRow.add(String.valueOf(books.getInt(1)));
                    dataRow.add(books.getString(2));
                    dataRow.add(books.getString(3));
                    dataRow.add(String.valueOf(books.getInt(4)));
                    dataRow.add(books.getString(/**/5));
                    dataRow.add(String.valueOf(books.getInt(6)));
                    dataRow.add(String.valueOf(books.getInt(7)));
                    dataRow.add(String.valueOf(books.getInt(8)));
                    data.add(dataRow);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            dm.setDataVector(data, columnNames);
            table.getColumn("Add").setCellRenderer(new ButtonRenderer());
            table.getColumn("Add").setCellEditor(new ButtonEditor(new JCheckBox()));
        }
    }

    public static void changeWindow(String actionName, ActionListener action) {
        BooksViewerFrame frame = new BooksViewerFrame(actionName, action);
        frame.setTitle("Book Viewer Form");
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setBounds(10, 10, 2000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        BooksViewerFrame.changeWindow("", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());

        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            JOptionPane.showMessageDialog(button, label + ": Ouch!");
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}

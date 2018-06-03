package view;

import controller.book_store.BooksQueryUtil;
import controller.book_store.BooksQueryManager;
import controller.books.actions.ManagerAction;
import view.util.GUIConstants;
import view.util.WindowChanger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.function.BiConsumer;

public class BooksViewerFrame extends JFrame implements ActionListener, WindowChanger{

    Container container = getContentPane();
    // Labels
    JLabel searchKeyLabel = new JLabel("Search By");
    JTextField searchKeyTextField = new JTextField();
    String[] keys = {"Title", "Publisher_Name", "Publication_Year", "Category"};
    JComboBox<String> possibleKeys = new JComboBox<>(keys);
    JButton searchButton = new JButton("Search");
    JButton clearButton = new JButton("Clear");
    Vector<String> columnNames;
    DefaultTableModel dm = new DefaultTableModel();
    JTable table = new JTable(dm);
    JScrollPane scroll;
    BiConsumer action;

    BooksViewerFrame(String actionName, BiConsumer action) {
        initializeColumnNames();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        this.action = action;
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
        clearButton.setBounds(GUIConstants.initX + GUIConstants.offsetX * 4,
                GUIConstants.initY, GUIConstants.width, GUIConstants.height);
        scroll.setBounds(GUIConstants.initX, GUIConstants.initY + GUIConstants.offsetY,
                GUIConstants.width * 5, GUIConstants.height * 10);
    }

    private  void addComponentsToContainer() {
        container.add(searchKeyLabel);
        container.add(searchKeyTextField);
        container.add(possibleKeys);
        container.add(searchButton);
        container.add(clearButton);
        container.add(scroll);
    }

    private  void addActionEvent() {
        searchButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Vector<Vector<String>> data = new Vector<>();
        if (e.getSource() == searchButton) {
            try {
                Class.forName("controller.DatabaseConnector");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            data = BooksQueryManager.getBooksList(String.valueOf(possibleKeys.getSelectedItem()), searchKeyTextField.getText(),
                    BooksQueryUtil.Operator.EQUALITY);

            dm.setDataVector(data, columnNames);
            table.getColumn("Add").setCellRenderer(new ButtonRenderer());
            table.getColumn("Add").setCellEditor(new ButtonEditor(new JCheckBox()));
        } else if (e.getSource() == clearButton) {
            dm.setDataVector(data, columnNames);
        }
    }

    public static void changeWindow(String actionName, BiConsumer action) {
        BooksViewerFrame frame = new BooksViewerFrame(actionName, action);
        frame.setTitle("Book Viewer Form");
        frame.setVisible(true);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        frame.setSize(xSize,ySize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    public static void main(String[] args) {
        BooksViewerFrame.changeWindow("", new ManagerAction());
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
            System.out.println("Renderer : Row #" + row);
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
            System.out.println("Editor : Row #" + row);
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

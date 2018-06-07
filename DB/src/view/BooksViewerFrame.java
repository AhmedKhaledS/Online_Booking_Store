package view;

import controller.books.query.BooksQueryUtil;
import controller.books.query.BooksQueryManagerController;
import controller.books.viewer.actions.CustomerAction;
import controller.books.viewer.actions.UserAction;
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

    JLabel searchKeyLabel = new JLabel("Search By");
    JTextField searchKeyTextField = new JTextField();
    String[] keys = {"Title", "Publisher_Name", "Publication_Year", "Category"};
    JComboBox<String> possibleKeys = new JComboBox<>(keys);
    JButton searchButton = new JButton("Search");
    JButton nextPageButton = new JButton("Next");
    JButton prevPageButton = new JButton("Prev");
    JButton clearButton = new JButton("Clear");

    Vector<String> columnNames;
    Vector<Vector<String>> data;
    DefaultTableModel dm = new DefaultTableModel();
    JTable table = new JTable(dm);
    JScrollPane scroll;
    int pageIndex = 0;
    final int MAX_PAGE_LEN = 10;

    UserAction action;
    String actionName;

    BooksViewerFrame(String actionName, UserAction action) {
        this.actionName = actionName;
        this.action = action;
        initializeColumnNames();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        action.addToFrame(container);
        addActionEvent();
    }

    private void initializeColumnNames() {
        columnNames = new Vector<>();
        columnNames.add(actionName);
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
        table.getColumn(actionName).setCellRenderer(new ButtonRenderer(action));
        table.getColumn(actionName).setCellEditor(new ButtonEditor(new JCheckBox()));
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
        nextPageButton.setBounds(GUIConstants.initX + GUIConstants.offsetX * 4,
                GUIConstants.initY, GUIConstants.width, GUIConstants.height);
        prevPageButton.setBounds(GUIConstants.initX + GUIConstants.offsetX * 5,
                GUIConstants.initY, GUIConstants.width, GUIConstants.height);
        clearButton.setBounds(GUIConstants.initX + GUIConstants.offsetX * 6,
                GUIConstants.initY, GUIConstants.width, GUIConstants.height);
        scroll.setBounds(GUIConstants.initX, GUIConstants.initY + GUIConstants.offsetY,
                GUIConstants.width * 5, GUIConstants.height * 10);
    }

    private  void addComponentsToContainer() {
        container.add(searchKeyLabel);
        container.add(searchKeyTextField);
        container.add(possibleKeys);
        container.add(searchButton);
        container.add(nextPageButton);
        container.add(prevPageButton);
        container.add(clearButton);
        container.add(scroll);
    }

    private  void addActionEvent() {
        searchButton.addActionListener(this);
        clearButton.addActionListener(this);
        nextPageButton.addActionListener(this);
        prevPageButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            try {
                Class.forName("controller.DatabaseConnector");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            data = new Vector<>();
            data = BooksQueryManagerController.getBooksList(String.valueOf(possibleKeys.getSelectedItem()), searchKeyTextField.getText(),
                    BooksQueryUtil.Operator.LIKE);
            dm.setDataVector(new Vector<>(data.subList(pageIndex, Math.min(pageIndex + MAX_PAGE_LEN, data.size()))),
                    columnNames);
        } else if (e.getSource() == clearButton) {
            data = new Vector<>();
            dm.setDataVector(data, columnNames);
        } else if (e.getSource() == nextPageButton) {
            if (pageIndex + MAX_PAGE_LEN < data.size()) {
                pageIndex += MAX_PAGE_LEN;
                dm.setDataVector(new Vector<>(data.subList(pageIndex, Math.min(pageIndex + MAX_PAGE_LEN, data.size()))),
                        columnNames);
            }
        } else if (e.getSource() == prevPageButton) {
            if (pageIndex - MAX_PAGE_LEN >= 0) {
                pageIndex -= MAX_PAGE_LEN;
                dm.setDataVector(new Vector<>(data.subList(pageIndex, Math.min(pageIndex + MAX_PAGE_LEN, data.size()))),
                        columnNames);
            }
        }
        table.getColumn(actionName).setCellRenderer(new ButtonRenderer(action));
        table.getColumn(actionName).setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    public static void changeWindow(String actionName, UserAction action) {
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
        BooksViewerFrame.changeWindow("Edit", new CustomerAction());
    }
}



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
            JOptionPane.showMessageDialog(button, label + ": Item Added");
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

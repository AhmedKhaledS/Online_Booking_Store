package view.util.table.frame.definer;

import controller.books.query.BooksQueryManagerController;
import controller.books.query.BooksQueryUtil;
import controller.books.viewer.actions.CustomerAction;
import view.util.GUIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import static view.util.GUIConstants.offsetX;

public class BooksSearcherTableFrameDefiner extends TableFrameDefiner implements ActionListener {

    private JLabel searchKeyLabel;
    private JTextField searchKeyTextField;
    private JComboBox<String> possibleKeys;
    private JButton searchButton;
    private JButton clearButton;
    private JButton viewShoppingCartButton;
    private JLabel quantityLabel;
    private JTextField quantityTextField;

    public BooksSearcherTableFrameDefiner(String actionName) {
        this.action = new CustomerAction();
    }

    @Override
    public void update(Object eventSource) {

    }

    @Override
    public Vector<String> defineTableAttributes() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add(action.getActionName());
        columnNames.add("ISBN");
        columnNames.add("Title");
        columnNames.add("Publisher Name");
        columnNames.add("Publication Year");
        columnNames.add("Category");
        columnNames.add("Price");
        columnNames.add("No of Copies");
        columnNames.add("Min_Quantity");
        return columnNames;
    }

    @Override
    public void modifyFrame(Container container) {
        quantityTextField = new JTextField();
        quantityLabel = new JLabel("Book Quantity");
        searchKeyLabel = new JLabel("Search By");
        searchKeyTextField = new JTextField();
        String[] keys = {"Title", "Publisher_Name", "Publication_Year", "Category"};
        possibleKeys = new JComboBox<>(keys);
        searchButton = new JButton("Search");
        clearButton = new JButton("Clear");
        viewShoppingCartButton = new JButton("View Shopping Cart");
        searchKeyLabel.setBounds(GUIConstants.initX, GUIConstants.initY,
                GUIConstants.width, GUIConstants.height);
        searchKeyTextField.setBounds(GUIConstants.initX + offsetX,
                GUIConstants.initY, GUIConstants.width, GUIConstants.height);
        possibleKeys.setBounds(GUIConstants.initX + offsetX * 2,
                GUIConstants.initY, GUIConstants.width, GUIConstants.height);
        searchButton.setBounds(GUIConstants.initX + offsetX * 3,
                GUIConstants.initY, GUIConstants.width, GUIConstants.height);
        clearButton.setBounds(GUIConstants.initX + offsetX * 6,
                GUIConstants.initY, GUIConstants.width, GUIConstants.height);
        viewShoppingCartButton.setBounds(GUIConstants.initX, GUIConstants.initY + GUIConstants.offsetY * 8,
                GUIConstants.width, GUIConstants.height);
        quantityLabel.setBounds(GUIConstants.initX + offsetX, GUIConstants.initY + GUIConstants.offsetY * 8,
                GUIConstants.width, GUIConstants.height);
        quantityTextField.setBounds(GUIConstants.initX + offsetX * 2, GUIConstants.initY + GUIConstants.offsetY * 8,
                GUIConstants.width, GUIConstants.height);
        setActionListeners();
        addToContainer(container);
    }

    private void setActionListeners () {
        searchButton.addActionListener(this);
    }

    private void addToContainer(Container container) {
        container.add (searchKeyLabel);
        container.add (searchKeyTextField);
        container.add (possibleKeys);
        container.add (searchButton);
        container.add (clearButton);
        container.add (viewShoppingCartButton);
        container.add (quantityLabel);
        container.add (quantityTextField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            try {
                Class.forName("controller.DatabaseConnector");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            Vector<Vector<String>> data = new Vector<>();
            data = BooksQueryManagerController.getBooksList(String.valueOf(possibleKeys.getSelectedItem()), searchKeyTextField.getText(),
                    BooksQueryUtil.Operator.LIKE);
            this.definableTableFrame.setData(data);
        } else if (e.getSource() == clearButton) {
            searchKeyTextField.setText("");
        } else if (e.getSource() == viewShoppingCartButton) {

        }
    }
}

package view.util.table.frame.definer;

import controller.books.query.BookOrdersManagerController;
import controller.books.query.BooksQueryUtil;
import controller.books.viewer.actions.CustomerAddBookAction;
import controller.books.viewer.actions.CustomerRemoveBookAction;
import view.TableFrame;
import view.util.GUIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import static view.util.GUIConstants.offsetX;

public class BooksOrderTableFrameDefiner extends TableFrameDefiner implements ActionListener {

    private JLabel searchKeyLabel;
    private JTextField searchKeyTextField;
    private JComboBox<String> possibleKeys;
    private JButton searchButton;
    private JButton clearButton;
    private JButton viewShoppingCartButton;

    public BooksOrderTableFrameDefiner() {
        this.action = new CustomerAddBookAction();
    }

    @Override
    public void update(Object eventSource) {

    }

    @Override
    public Vector<String> defineTableAttributes() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add(action.getActionName());
        columnNames.addAll(Constants.getBookTableColumnNames());
        columnNames.add("Quantity");
        this.definableTableFrame.setEditableColumn(columnNames.size() - 1);
        return columnNames;
    }

    @Override
    public void modifyFrame(Container container) {
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
        setActionListeners();
        addToContainer(container);
    }

    private void setActionListeners () {
        searchButton.addActionListener(this);
        viewShoppingCartButton.addActionListener(this);
    }

    private void addToContainer(Container container) {
        container.add (searchKeyLabel);
        container.add (searchKeyTextField);
        container.add (possibleKeys);
        container.add (searchButton);
        container.add (clearButton);
        container.add (viewShoppingCartButton);
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
            data = BookOrdersManagerController.getBooksList(String.valueOf(possibleKeys.getSelectedItem()), searchKeyTextField.getText(),
                    BooksQueryUtil.Operator.EQUALITY);
            this.definableTableFrame.setData(data);
        } else if (e.getSource() == clearButton) {
            searchKeyTextField.setText("");
        } else if (e.getSource() == viewShoppingCartButton) {
            if (!isValidOrder()) {
                this.action.setData(new Vector<>());
                System.out.println("Invalid");
                return;
            }
            ShoppingCartTableFrameDefiner spCartDefiner = new ShoppingCartTableFrameDefiner(this.action.getData());
            TableFrame.changeWindow(spCartDefiner);
        }
        return;
    }

    private boolean isValidOrder () {
        for (Vector<String> dataRow : this.action.getData()) {
            int noOfCopies = Integer.parseInt(dataRow.get(dataRow.size() - 3));
            int requiredQuantity = Integer.parseInt(dataRow.get(dataRow.size() - 1));
            if (noOfCopies <= requiredQuantity) {
                return false;
            }
        }
        return true;
    }
}

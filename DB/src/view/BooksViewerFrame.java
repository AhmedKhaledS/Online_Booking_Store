package view;

import view.util.GUIConstants;
import view.util.WindowChanger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BooksViewerFrame extends JFrame implements ActionListener, WindowChanger{

    Container container = getContentPane();
    // Labels
    JLabel searchKeyLabel = new JLabel("Search By");
    String[] keys = {"Title", "Publisher Name", "Publication Year", "Category"};
    JComboBox<String> possibleKeys = new JComboBox<>(keys);
    JButton searchButton = new JButton("Search");
    String[] columnNames = {"ISBN, Title", "Publisher Name", "Publication Year",
            "Category", "Price", "No of Copies", "Min_Quantity"};
    Object[][] data;
    JTable table = new JTable(data, columnNames);

    BooksViewerFrame(String actionName, ActionListener action) {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent(action);
    }

    private  void setLayoutManager() {
        container.setLayout(null);
    }

    private  void setLocationAndSize() {
        searchKeyLabel.setBounds(GUIConstants.initX, GUIConstants.initY,
                GUIConstants.width, GUIConstants.height);
        possibleKeys.setBounds(GUIConstants.initX + GUIConstants.offsetX,
                GUIConstants.initY, GUIConstants.width, GUIConstants.height);
        searchButton.setBounds(GUIConstants.initX + GUIConstants.offsetX * 2,
                GUIConstants.initY, GUIConstants.width, GUIConstants.height);
        table.setBounds(GUIConstants.initX, GUIConstants.initY + GUIConstants.offsetY,
                GUIConstants.width, GUIConstants.height);
    }

    private  void addComponentsToContainer() {
        container.add(searchKeyLabel);
        container.add(possibleKeys);
        container.add(searchButton);
        container.add(table);
    }

    private  void addActionEvent(ActionListener action) {
        searchButton.addActionListener(action);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void changeWindow(String actionName, ActionListener action) {
        BooksViewerFrame frame = new BooksViewerFrame(actionName, action);
        frame.setTitle("Book Viewer Form");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}

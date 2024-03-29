package view.util.table.frame.definer;

import java.util.Vector;

public class Constants {

    public static Vector<String> getBookTableColumnNames() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("ISBN");  //1
        columnNames.add("Title");
        columnNames.add("Publisher Name");
        columnNames.add("Publication Year"); // 4
        columnNames.add("Category");
        columnNames.add("Price");
        columnNames.add("No of Copies"); // 7
        columnNames.add("Min_Quantity");
        return columnNames;
    }
}

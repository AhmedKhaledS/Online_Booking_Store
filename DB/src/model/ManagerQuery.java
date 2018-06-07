package model;

import controller.DatabaseConnector;
import controller.Utils;
import controller.books.query.BooksQueryUtil;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ManagerQuery extends UserQuery {

    private static ManagerQuery instance;

    private ManagerQuery() {}

    public static ManagerQuery getInstance() {
        if (instance == null) {
            instance = new ManagerQuery();
        }
        return instance;
    }

    public Vector<Vector<String>> getBooksList(String key, String value,
                                                      BooksQueryUtil.Operator operator) {
        if (key == "Publisher_Name") {
            key = "Name";
        }
        ResultSet books = DatabaseConnector
                .executeQuery("SELECT isbn, title, name, publication_year, category, price, no_of_copies, min_quantity"
                        + " from BOOK as B join PUBLISHER as P on B.publisher_id = P.publisher_id " + " where " + key
                        + getInstance().getOperatorString(operator)
                        + (operator == BooksQueryUtil.Operator.LIKE ? Utils.encloseInLikeFormat(value)
                        : Utils.encloseInQuotes(value)));

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
        return data;

    }

        public void addBook(String[] originalData) {
        boolean error = false;

        String data[] = new String[8];
        String authors[] = originalData[8].split(",");

        for (int i = 0; i < data.length; i++) {
            data[i] = originalData[i];
        }

        String add_book_sql = "INSERT INTO BOOK VALUES (" + data[0] + "," + "'" + data[1] + "'" + "," + data[2] + ","
                + "'" + data[3] + "'" + "," + "'" + data[4] + "'" + "," + data[5] + "," + data[6] + "," + data[7]
                + ");";

        if (DatabaseConnector.executeModify(add_book_sql)) {

            String add_author_sql;

            for (int i = 0; i < authors.length && !error; i++) {

                add_author_sql = "INSERT INTO BOOK_AUTHORS VALUES (" + data[0] + "," + "'" + authors[i] + "'" + ");";

                if (!DatabaseConnector.executeModify(add_author_sql)) {
                    error = true;
                    DatabaseConnector.rollDB();
                    JOptionPane.showMessageDialog(null, "Error! Please Try Again");
                }
            }

            if (!error) {
                DatabaseConnector.commitDB();
                JOptionPane.showMessageDialog(null, "Added Successfully");
            }

        } else {
            DatabaseConnector.rollDB();
            JOptionPane.showMessageDialog(null, "Error! Please Try Again");
        }

        DatabaseConnector.setCommitLevel(true);
    }

    public void deleteBook(String ISBN) {

        System.out.println(ISBN);
        String sql_author_data = "DELETE FROM BOOK_AUTHORS WHERE ISBN=" + ISBN + ";";
        String sql_basic_data = "DELETE FROM BOOK WHERE ISBN=" + ISBN + ";";
        DatabaseConnector.executeModify(sql_author_data);
        DatabaseConnector.executeModify(sql_basic_data);
    }

    private String getOperatorString(BooksQueryUtil.Operator operator) {
        if (operator == BooksQueryUtil.Operator.EQUALITY) {
            return " = ";
        } else if (operator == BooksQueryUtil.Operator.GREATER) {
            return " > ";
        } else if (operator == BooksQueryUtil.Operator.GREATER_EQUAL) {
            return " >= ";
        } else if (operator == BooksQueryUtil.Operator.LESS) {
            return " < ";
        } else if (operator == BooksQueryUtil.Operator.LESS_EQUAL) {
            return " <= ";
        } else if (operator == BooksQueryUtil.Operator.NOT_EQUAL) {
            return " != ";
        } else {
            return " like ";
        }
    }
}

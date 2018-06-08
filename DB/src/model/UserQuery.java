package model;

import controller.DatabaseConnector;
import controller.Utils;
import controller.books.query.BooksQueryUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public abstract class UserQuery {


    public Vector<Vector<String>> getBooksList(String key, String value,
                                               BooksQueryUtil.Operator operator) {
        if (key == "Publisher_Name") {
            key = "Name";
        }
        ResultSet books = DatabaseConnector
                .executeQuery("SELECT isbn, title, name, publication_year, category, price, no_of_copies, min_quantity"
                        + " FROM BOOK AS B JOIN PUBLISHER AS P ON B.publisher_id = P.publisher_id " + " WHERE " + key
                        + this.getOperatorString(operator)
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

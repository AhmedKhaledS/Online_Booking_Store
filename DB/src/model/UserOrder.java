package model;

import controller.DatabaseConnector;
import controller.Utils;
import controller.books.query.BooksQueryUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public abstract class UserOrder {

    private final String NULLSTRING = "NULL";

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

    public Vector<Vector<String>> getAllBooks() {
        ResultSet books = DatabaseConnector
                .executeQuery("SELECT isbn, title, name, publication_year, category, price, no_of_copies, min_quantity"
                        + " FROM BOOK AS B JOIN PUBLISHER AS P ON B.publisher_id = P.publisher_id ");

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

    public boolean insertOrder(UserOrderDataModel order) {
        UserOrderDataModel compatibleAttributes = getCompatibleAttributes(order);
    	DatabaseConnector.setCommitLevel(false);
        String addOrderStmt = "INSERT INTO `ORDER`(`E_mail`, `ISBN`, `Quantity`, `State`, `Date`)" +
                              " VALUES (" + compatibleAttributes.getEmail() + "," + compatibleAttributes.getIsbn() +
                              "," + compatibleAttributes.getQuantity() + "," + compatibleAttributes.getState() +
                              "," + compatibleAttributes.getDate() + ");";

        //System.out.println(addOrderStmt);
        
        if (DatabaseConnector.executeModify(addOrderStmt)) {
            DatabaseConnector.commitDB();
            DatabaseConnector.setCommitLevel(true);
            return true;
        }
        DatabaseConnector.rollDB();
        DatabaseConnector.setCommitLevel(true);
        return false;
    }

    public boolean confirmOrders() {
        String confirmOrderStmt = "UPDATE `ORDER` SET `State` = 'COMPLETED' WHERE `State` = 'IN_PROGRESS'";
        if (DatabaseConnector.executeModify(confirmOrderStmt)) {
            return true;
        }
        return false;
    }

    public void deleteOrders() {
        String confirmOrderStmt = "DELETE FROM `ORDER` WHERE `State` = 'IN_PROGRESS'";
        DatabaseConnector.executeModify(confirmOrderStmt);
    }

    private UserOrderDataModel getCompatibleAttributes(UserOrderDataModel order) {
        UserOrderDataModel compatibleOrder = new UserOrderDataModel(
                                            order.getEmail().isEmpty() ? NULLSTRING : "'" + order.getEmail() + "'"
                                          , order.getIsbn().isEmpty() ? NULLSTRING : order.getIsbn()
                                          , order.getQuantity().isEmpty() ? NULLSTRING : order.getQuantity()
                                          , order.getState().isEmpty() ? NULLSTRING : "'" + order.getState() + "'"
                                          , order.getDate().isEmpty() ? NULLSTRING : "'" + order.getDate() + "'");
        return compatibleOrder;
    }

    public Vector<Vector<String>> getSomeTuples(final int limit, final int offset) {
        ResultSet books = DatabaseConnector
                .executeQuery("SELECT isbn, title, name, publication_year, category, price, no_of_copies, min_quantity"
                        + " FROM BOOK AS B JOIN PUBLISHER AS P ON B.publisher_id = P.publisher_id LIMIT " + limit
                        + " OFFSET " + offset + " ;");
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

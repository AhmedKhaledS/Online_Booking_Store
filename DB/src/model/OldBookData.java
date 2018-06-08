package model;

import controller.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OldBookData {

    public static void loadOldBookData(String ISBN, String data[]) {
        String sql_basic_data = "SELECT * FROM BOOK WHERE ISBN=" + ISBN + ";";
        String sql_author_data = "SELECT * FROM BOOK_AUTHORS WHERE ISBN=" + ISBN + ";";

        ResultSet basic_data = DatabaseConnector.executeQuery(sql_basic_data);
        ResultSet author_data = DatabaseConnector.executeQuery(sql_author_data);

        try {
            basic_data.next();
            for (int i = 0; i < 8; i++) {
                data[i] = basic_data.getString(i + 1);
            }
            data[8] = "";
            while (author_data.next()) {
                data[8] += author_data.getString("Author_name") + ",";
            }
            if(data[8].length() > 1)
                data[8] = data[8].substring(0, data[8].length() - 1);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}

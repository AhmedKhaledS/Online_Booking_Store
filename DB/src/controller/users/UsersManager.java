package controller.users;

import controller.DatabaseConnector;
import controller.Utils;

import java.sql.ResultSet;

public class UsersManager {

    public static boolean isValidUser (String username, String password) {
        ResultSet users = DatabaseConnector.executeQuery
                ("select * from user where " + "e-mail" + " = " + Utils.encloseInQuotes(username) + "and " +
                        "password" + " = " + Utils.encloseInQuotes(password));

        return false;
    }
}


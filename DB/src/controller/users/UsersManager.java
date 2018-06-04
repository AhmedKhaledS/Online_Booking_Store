package controller.users;

import controller.DatabaseConnector;
import controller.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersManager {

    public static UsersUtil.UserType getUserType (String username, String password) {
        ResultSet users = DatabaseConnector.executeQuery
                ("select user_type from USER where " + "e-mail" + " = " + Utils.encloseInQuotes(username) + "and " +
                        "password" + " = " + Utils.encloseInQuotes(password));
        try {
            if (users.next())
                return getUserType(users.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return UsersUtil.UserType.INVALID_USER;
    }

    private static UsersUtil.UserType getUserType (String userType) {
        if (userType == "Customer") return  UsersUtil.UserType.CUSTOMER;
        else if (userType == "Manager") return UsersUtil.UserType.MANAGER;
        else return UsersUtil.UserType.INVALID_USER;
    }
}


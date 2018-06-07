package controller.users;

import controller.DatabaseConnector;
import controller.Utils;
import model.UserModel;
import model.UserProfile;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfileController {
    private static UserProfileController instance = new UserProfileController();
    private UserProfile currentLoggedInUser;


    public UserProfile getCurrentLoggedInUser() {
        return currentLoggedInUser;
    }

    private UserProfileController() {}
    public static UserProfileController getInstance() {
        return instance;
    }
    public void registerUser(UserProfile user) {
        if (UserModel.getInstance().registerUser(user)) {
            currentLoggedInUser = user;
        }
    }

    public void updateUser(UserProfile user) {

    }

    public void login(UserProfile user) {
        UserModel.getInstance().login(user);
        if (user.getType() == UsersUtil.UserType.INVALID_USER) {
            JOptionPane.showMessageDialog(null, "Error:" +
                    " E-mail or Password is incorrect!");
            return;
        }
        // Do not need to update all its profile from DB as we only need user's E-mail.
        currentLoggedInUser = user;
        JOptionPane.showMessageDialog(null, "You are logged in successfully.");
    }

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

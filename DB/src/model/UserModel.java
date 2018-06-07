package model;

import com.sun.corba.se.impl.orb.DataCollectorBase;
import controller.DatabaseConnector;
import controller.Utils;
import controller.users.UsersUtil;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

    private static UserModel ourInstance = new UserModel();

    public static UserModel getInstance() {
        return ourInstance;
    }

    private UserModel() {
    }

    public boolean isRegisteredUser(String email) {
        String getUserStmt = "SELECT * FROM USER WHERE `E-mail`=" + "'" + email + "';";
        ResultSet rs = DatabaseConnector.executeQuery(getUserStmt);
        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registerUser(UserProfile user) {
        if (isRegisteredUser(user.getEmail())) {
            System.out.println("Error: You are already registered!");
            JOptionPane.showMessageDialog(null,
                    "Error: You are already registered!");
        }
        String registerStmt = "INSERT INTO USER VALUES ("  + "'" + user.getEmail()
                + "'" + "," + "'" + user.getUsername() + "'" + "," +
                "'" + user.getPassword() + "'" + "," + "'" + user.getLastName() +
                "'" + "," + "'" + user.getFirstName() + "'" + "," +
                "'" + user.getPhoneNum() + "'" + "," + "'" + user.getShoppingAddress()
                + "'" + "," + "'" + getStringType(user.getType()) + "');";
        if (DatabaseConnector.executeModify(registerStmt)) {
            JOptionPane.showMessageDialog(null,
                    "You are registered successfully.");
            return true;
        } else {
            DatabaseConnector.rollDB();
            JOptionPane.showMessageDialog(null,
                    "Error while registering this user! Please Try Again");
        }
        return false;
    }

    private String getStringType(UsersUtil.UserType type) {
        if (type == UsersUtil.UserType.CUSTOMER)
            return "Customer";
        return "Manager";
    }

    public boolean updateEmail(final String email, final String newEmail) {
        String queryStmt = "UPDATE USER SET `E-mail`=" + "'" + newEmail + "'" +
                " WHERE `E-mail`=" + "'" + email + "';";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating E-mail!");
            return false;
        }
        return true;
    }

    public boolean updateUserName(final String email, final String newUserName) {
        String queryStmt = "UPDATE USER SET `Username`=" + "'" + newUserName + "'" +
                " WHERE `E-mail`=" + "'" + email + "';";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating Username!");
            return false;
        }
        return true;
    }

    public boolean updatePassword(final String email, final String newPassword) {
        String queryStmt = "UPDATE USER SET `Password`=" + "'" + newPassword + "'" +
                " WHERE `E-mail`=" + "'" + email + "';";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating Password!");
            return false;
        }
        return true;
    }

    public boolean updateLastName(final String email, final String newLastName) {
        String queryStmt = "UPDATE USER SET `Lastname`=" + "'" + newLastName + "'" +
                " WHERE `E-mail`=" + "'" + email + "';";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating Last name!");
            return false;
        }
        return true;
    }

    public boolean updateFirstName(final String email, final String newFirstName) {
        String queryStmt = "UPDATE USER SET `Firstname`=" + "'" + newFirstName + "'" +
                " WHERE `E-mail`=" + "'" + email + "';";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating First name!");
            return false;
        }
        return true;
    }

    public boolean updatePhoneNum(final String email, final String newPhoneNum) {
        String queryStmt = "UPDATE USER SET `Phone_number`=" + "'" + newPhoneNum + "'" +
                " WHERE `E-mail`=" + "'" + email + "';";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating Phone number!");
            return false;
        }
        return true;
    }

    public boolean upadteShoppingAddress(final String email, final String newShoppingAddress) {
        String queryStmt = "UPDATE USER SET `Shopping_address`=" + "'" + newShoppingAddress + "'" +
                " WHERE `E-mail`=" + "'" + email + "';";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating Shopping number!");
            return false;
        }
        return true;
    }

    public boolean updateUserType(final String email, final String newUserType) {
        String queryStmt = "UPDATE USER SET `User_type`=" + "'" + newUserType + "'" +
                " WHERE `E-mail`=" + "'" + email + "';";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating UserType!");
            return false;
        }
        return true;
    }


    public void login(UserProfile user) {
        // It's sufficient to check whether the return type of user is valid or not.
        UsersUtil.UserType userType = getUserType(user.getEmail(), user.getPassword());
        if (userType == UsersUtil.UserType.INVALID_USER)
            user.setType(UsersUtil.UserType.INVALID_USER);
        else if (userType == UsersUtil.UserType.CUSTOMER)
            user.setType(UsersUtil.UserType.CUSTOMER);
        else
            user.setType(UsersUtil.UserType.MANAGER);
    }

    public UsersUtil.UserType getUserType (String username, String password) {
        ResultSet users = DatabaseConnector.executeQuery
                ("select `User_type` from USER where " + "`E-mail`" + " = " + Utils.encloseInQuotes(username) + "and " +
                        "`Password`" + " = " + Utils.encloseInQuotes(password));
        try {
            if (users.next())
                return getUserType(users.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return UsersUtil.UserType.INVALID_USER;
    }

    private UsersUtil.UserType getUserType (String userType) {
        if (userType .equals("Customer")) return  UsersUtil.UserType.CUSTOMER;
        else if (userType .equals("Manager")) return UsersUtil.UserType.MANAGER;
        else return UsersUtil.UserType.INVALID_USER;
    }
}

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

    private final String NULLVALUE = "NULL";
    private static UserModel ourInstance = new UserModel();

    public static UserModel getInstance() {
        return ourInstance;
    }

    private UserModel() {
    }

    public boolean isRegisteredUser(String email) {
        String getUserStmt = "SELECT * FROM `USER` WHERE `E-mail`=" + "'" + email + "';";
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
        String registerStmt = "INSERT INTO `USER` VALUES ("
                + "'" + (user.getEmail().isEmpty() ? NULLVALUE : user.getEmail() )
                + "'" + "," + "'" + (user.getUsername().isEmpty() ? NULLVALUE : user.getUsername())
                + "'" + "," + "'" + (user.getPassword().isEmpty() ? NULLVALUE : user.getPassword())
                + "'" + "," + "'" + (user.getLastName().isEmpty() ? NULLVALUE : user.getLastName())
                + "'" + "," + "'" + (user.getFirstName().isEmpty() ? NULLVALUE : user.getFirstName())
                + "'" + "," + "'" + (user.getPhoneNum().isEmpty() ? NULLVALUE : user.getPhoneNum())
                + "'" + "," + "'" + (user.getShoppingAddress().isEmpty() ? NULLVALUE : user.getShoppingAddress())
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

    public boolean updateEmail(final String email, String newEmail) {
        if (newEmail.isEmpty()) {
            newEmail = NULLVALUE;
        }
        String queryStmt = "UPDATE USER SET `E_mail`=" + "'" + newEmail + "'" +
                " WHERE `E_mail`=" + "'" + email + "';";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating E-mail!");
            return false;
        }
        return true;
    }

    public boolean updateUserName(final String email, String newUserName) {
        if (newUserName.isEmpty()) {
            newUserName = NULLVALUE;
        }
        String queryStmt = "UPDATE USER SET `Username`=" + "'" + newUserName + "'" +
                " WHERE `E_mail`=" + "'" + email + "';";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating Username!");
            return false;
        }
        return true;
    }

    public boolean updatePassword(final String email, String newPassword) {
        if (newPassword.isEmpty()) {
            newPassword = NULLVALUE;
        }
        String queryStmt = "UPDATE USER SET `Password`=" + "'" + newPassword + "'" +
                " WHERE `E_mail`=" + "'" + email + "';";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating Password!");
            return false;
        }
        return true;
    }

    public boolean updateLastName(final String email, String newLastName) {
        if (newLastName.isEmpty()) {
            newLastName = NULLVALUE;
        }
        String queryStmt = "UPDATE USER SET `Lastname`=" + "'" + newLastName + "'" +
                " WHERE `E_mail`=" + "'" + email + "';";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating Last name!");
            return false;
        }
        return true;
    }

    public boolean updateFirstName(final String email, String newFirstName) {
        if (newFirstName.isEmpty()) {
            newFirstName = NULLVALUE;
        }
        String queryStmt = "UPDATE USER SET `Firstname`=" + "'" + newFirstName + "'" +
                " WHERE `E_mail`=" + "'" + email + "';";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating First name!");
            return false;
        }
        return true;
    }

    public boolean updatePhoneNum(final String email, String newPhoneNum) {
        if (newPhoneNum.isEmpty()) {
            newPhoneNum = NULLVALUE;
        }
        String queryStmt = "UPDATE USER SET `Phone_number`=" + "'" + newPhoneNum + "'" +
                " WHERE `E_mail`=" + "'" + email + "';";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating Phone number!");
            return false;
        }
        return true;
    }

    public boolean upadteShoppingAddress(final String email, String newShoppingAddress) {
        if (newShoppingAddress.isEmpty()) {
            newShoppingAddress = NULLVALUE;
        }
        String queryStmt = "UPDATE USER SET `Shopping_address`=" + "'" + newShoppingAddress + "'" +
                " WHERE `E_mail`=" + "'" + email + "';";
        if (!DatabaseConnector.executeModify(queryStmt)) {
            System.out.println("Error occurred while updating Shopping number!");
            return false;
        }
        return true;
    }

    public boolean updateUserType(final String email, final String newUserType) {
        String queryStmt = "UPDATE USER SET `User_type`=" + "'" + newUserType + "'" +
                " WHERE `E_mail`=" + "'" + email + "';";
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
                ("SELECT `User_type` FROM `USER` WHERE " + "`E_mail` = " + Utils.encloseInQuotes(username) + " AND " +
                        "`Password` = " + Utils.encloseInQuotes(password));
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

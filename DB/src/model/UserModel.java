package model;

import controller.DatabaseConnector;
import controller.users.UsersUtil;

import javax.swing.*;
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

    public void registerUser(UserProfile user) {
        if (isRegisteredUser(user.getEmail())) {
            System.out.println("Error: You are already registered!");
            JOptionPane.showMessageDialog(null,
                    "Error: You are already registered!");
            return;
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
        } else {
            DatabaseConnector.rollDB();
            JOptionPane.showMessageDialog(null,
                    "Error while registering this user! Please Try Again");
        }
    }

    private String getStringType(UsersUtil.UserType type) {
        if (type == UsersUtil.UserType.CUSTOMER)
            return "Customer";
        return "Manager";
    }

    public void updateUserName(final String newUserName) {

    }

    public void updatePassword(final String newPassword) {

    }

    public void updateLastName(final String newLastName) {

    }

    public void updateFirstName(final String newFirstName) {

    }

    public void updatePhoneNum(final String newPhoneNum) {

    }

    public void upadteShoppingAddress(final String newShoppingAddress) {

    }

    public void updateUserType(final String newUserType) {

    }

}

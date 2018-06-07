package controller.users;

import controller.DatabaseConnector;
import controller.Utils;
import model.UserModel;
import model.UserProfile;

import javax.swing.*;


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
        String currentEmailUser = currentLoggedInUser.getEmail();
        boolean successfullyUpdated = UserModel.getInstance().updatePassword(currentEmailUser, user.getPassword())
                | UserModel.getInstance().updateFirstName(currentEmailUser, user.getFirstName())
                | UserModel.getInstance().updateLastName(currentEmailUser, user.getLastName())
                | UserModel.getInstance().updatePhoneNum(currentEmailUser, user.getPhoneNum())
                | UserModel.getInstance().upadteShoppingAddress(currentEmailUser, user.getShoppingAddress())
                | UserModel.getInstance().updateUserName(currentEmailUser, user.getUsername())
                | UserModel.getInstance().updateUserType(currentEmailUser, user.getType() == UsersUtil.UserType.MANAGER ?
                "Manager" : "Customer")
                | UserModel.getInstance().updateEmail(currentEmailUser, user.getEmail());
        if (successfullyUpdated) {
            currentLoggedInUser = user;
            JOptionPane.showMessageDialog(null, "Your is updated successfully.");
        } else {
        JOptionPane.showMessageDialog(null, "Error occurred while updating profile!");
        }
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

}

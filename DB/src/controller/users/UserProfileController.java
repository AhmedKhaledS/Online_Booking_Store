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

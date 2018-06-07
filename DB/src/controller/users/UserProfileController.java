package controller.users;

import model.UserModel;
import model.UserProfile;

public class UserProfileController {
    private static UserProfileController instance = new UserProfileController();

    private UserProfileController() {}
    public static UserProfileController getInstance() {
        return instance;
    }
    public void registerUser(UserProfile user) {
        UserModel.getInstance().registerUser(user);
    }
}

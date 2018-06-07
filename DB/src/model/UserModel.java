package model;

public class UserModel {
    private static UserModel ourInstance = new UserModel();

    public static UserModel getInstance() {
        return ourInstance;
    }

    private UserModel() {
    }

    boolean isRegisterdUser(String email) {

        return false;
    }

    void registerUser(UserProfile user) {

    }
}

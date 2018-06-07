package model;

import controller.users.UsersUtil;

public class UserProfile {

    private String email, username, password,
            lastName, firstName, phoneNum, shoppingAddress;

    private UsersUtil.UserType type;

    public UserProfile (String email, String username, String password,
                        String lastName, String firstName, String phoneNum,
                        String shoppingAddress, UsersUtil.UserType type) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNum = phoneNum;
        this.shoppingAddress = shoppingAddress;
        this.type = type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setShoppingAddress(String shoppingAddress) {
        this.shoppingAddress = shoppingAddress;
    }
    public void setType(UsersUtil.UserType type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getShoppingAddress() {
        return shoppingAddress;
    }

    public UsersUtil.UserType getType() {
        return type;
    }

}
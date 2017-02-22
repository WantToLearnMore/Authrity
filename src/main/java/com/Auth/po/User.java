package com.Auth.po;

/**
 * Created by Jiangtenglong on 2016/7/23.
 */
public class User {
    private int userId;
    private String userName;
    private String password;
    private String userAccount;

    public User() {
        super();
    }

    public User(int userId, String userName, String password, String userAccount) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userAccount = userAccount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}

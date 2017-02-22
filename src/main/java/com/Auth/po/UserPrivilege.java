package com.Auth.po;

/**
 * Created by ASUS! on 2017/2/20.
 */
public class UserPrivilege {
    private int userId;
    private String userName;
    private int userPrivilege;

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

    public int getUserPrivilege() {
        return userPrivilege;
    }

    public void setUserPrivilege(int userPrivilege) {
        this.userPrivilege = userPrivilege;
    }
}

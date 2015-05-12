package com.mypersonalapp.dnapatternsecure;

import android.content.Context;

import java.util.List;

/**
 * UserList Class
 * Contain all users' attributes
 */
public class UserList {
    private final static int SUPER_USER = 0;
    private final static int STANDARD_USER = 1;
    private int userID;
    private String userName;
    private String userPrivileges;
    private String userEmailAddress;
    private List<String> userHistory;
    public UserList() {

    }

    public UserList(String userName, String userEmailAddress) {
        this.userName = userName;
        this.userEmailAddress = userEmailAddress;
    }

    public UserList(int userID, String userName, String userEmailAddress) {
        this.userID = userID;
        this.userName = userName;
        this.userEmailAddress = userEmailAddress;
    }

    public UserList(int userID, String userName, String userPrivileges, List<String> userHistory) {
        this.userID = userID;
        this.userName = userName;
        this.userPrivileges = userPrivileges;
        this.userHistory = userHistory;
    }

    /**
     * Read email address or account from the mobile phone settings, compare the user name
     */
    public void setSuperUser() {
        if (this.getUserPrivileges().contains("d")) {
            //super user
            //
        }
    }

    public void setStandardUser() {
        if (!this.userPrivileges.contains("d")) {
            //standard user
            //cannot access history, delete records
        }
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmailAddress() {
        return this.userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress){
        this.userEmailAddress = userEmailAddress;
    }

    public String getUserPrivileges() {
        return userPrivileges;
    }

    public void setUserPrivileges(String userPrivileges) {
        this.userPrivileges = userPrivileges;
    }

    public List<String> getUserHistory(List<String> userHistory) {
        return userHistory;
    }

    public void setUserHistory(List<String> userHistory, int index) {
        this.userHistory.add(userHistory.get(index));
    }
}

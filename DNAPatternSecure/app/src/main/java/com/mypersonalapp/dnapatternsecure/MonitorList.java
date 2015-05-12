package com.mypersonalapp.dnapatternsecure;


import android.app.AlertDialog;
import android.content.Context;

import java.util.List;

/**
 * MonitorList
 * Contains BlackList, WhiteList, ApplicationList
 */
public class MonitorList {
    private Context context;
    private List<String> WhiteList;
    private List<String> BlackList;
    private List<String> ApplicationList;
    private int userID;
    private boolean upgrade = false;
    public MonitorList(Context context) {
        this.context = context.getApplicationContext();
    }
    public MonitorList(List<String> WhiteList) {

    }
    public MonitorList(List<String> BlackList, int userID) {

    }
    public MonitorList(List<String> WhiteList, List<String> ApplicationList) {

    }
    public MonitorList(List<String> BlackList, List<String> ApplicationList, int userID) {

    }
    public MonitorList(List<String> BlackList, List<String> WhiteList, List<String> ApplicationList) {

    }

    public void createWhiteList(String applicationName) {
        this.WhiteList.add(applicationName);
    }

    public List<String> getWhiteList() {
        return this.WhiteList;
    }

    public void createBlackList(String applicationName, int userID) {
        if (userID == 0) {
            this.BlackList.add(applicationName);
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("You are not SUPER USER");
            dialog.create().show();
        }
    }

    public List<String> getBlackList() {
        return this.BlackList;
    }

    public void createApplicationList(String applicationName) {
        this.ApplicationList.add(applicationName);
    }

    public List<String> getApplicationList() {
        return this.ApplicationList;
    }

}

package com.mypersonalapp.dnapatternsecure;

import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;


public class Directory extends ActionBarActivity implements OnClickListener,
        OnCheckedChangeListener{

    private Switch mSwitchSMS;
    private Switch mSwitchCall;
    private Switch mSwitchMalWare;
    private Switch mSwitchWIFI;
    private Switch mSwitchMonitor;
    private Switch mSwitchDNAPattern;
    private Button mButtonGetRealTimeInfo;

    private final static int PICK_ACCOUNT_INFO = 100;
    private String accountName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Category");
        toolbar.inflateMenu(R.menu.menu_directory);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String message = "";
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        message += "Click Settings";
                        break;
                    //reset the super user
                    //export the history file
                    //exit
                    case R.id.action_login:
                        message += "Click Login";
                        loginPrompt();
                        break;
                    //check whether the username and email address is stored int the database
                    case R.id.action_share:
                        message += "Click Share";
                        break;
                }
                if (!message.equals("")) {
                    Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mSwitchSMS = (Switch) findViewById(R.id.switch_SMSBlock);
        mSwitchSMS.setChecked(true);
        mSwitchSMS.setOnCheckedChangeListener(this);
        mSwitchCall = (Switch) findViewById(R.id.switch_callBlock);
        mSwitchCall.setChecked(true);
        mSwitchCall.setOnCheckedChangeListener(this);
        mSwitchMalWare = (Switch) findViewById(R.id.switch_malWareDetector);
        mSwitchMalWare.setChecked(true);
        mSwitchMalWare.setOnCheckedChangeListener(this);
        mSwitchWIFI = (Switch) findViewById(R.id.switch_WIFISecurity);
        mSwitchWIFI.setChecked(true);
        mSwitchWIFI.setOnCheckedChangeListener(this);
        mSwitchMonitor = (Switch) findViewById(R.id.switch_appMonitor);
        mSwitchMonitor.setChecked(true);
        mSwitchMonitor.setOnCheckedChangeListener(this);
        mSwitchDNAPattern = (Switch) findViewById(R.id.switch_pattern);
        mSwitchDNAPattern.setChecked(true);
        mSwitchDNAPattern.setOnCheckedChangeListener(this);

        mButtonGetRealTimeInfo = (Button) findViewById(R.id.button_getRealTimeInfo);
        mButtonGetRealTimeInfo.setOnClickListener(this);

        pickUserAccountInformation();
        DNADatabase dnaDatabase = new DNADatabase(this);
        createDatabase(dnaDatabase, accountName);
    }
    public void pickUserAccountInformation() {
        Intent googlePicker = AccountPicker.newChooseAccountIntent(null, null ,
                new String[]{GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE}, true, null, null, null, null);
        startActivityForResult(googlePicker, PICK_ACCOUNT_INFO);
    }

    public void createDatabase(DNADatabase dnaDatabase, String accountName) {
        //add new elements to the dnaDatabase, create the USER_TABLE
        dnaDatabase.deleteTable();
        Log.d("DatabaseInfo: ", "Adding....");
        dnaDatabase.addUser(new UserList("CAN ZENG", accountName));
        Log.d("DatabaseInfo: ", "Reading....");
        List<UserList> userLists = dnaDatabase.getAllUsers();
        for (UserList userList : userLists) {
            String userInfo = "Id: " + userList.getUserID() + "Name: " + userList.getUserName() +
                    "Email Address: " + userList.getUserEmailAddress();
            Log.d("UserInfo: ", userInfo);
        }
    }

    public boolean isSuperUser(String userName) {
        //CHECK THE NAME, IF MATCH SUPER USER NAME
        //GIVE FULL PRIVILEDGES TO THE USER
        //ELSE GIVE ONLY READ Permission access to its own records
        // return true;
        return true;
    }

    @Override
    protected  void onActivityResult(int requesCode, int resultCode, Intent data) {
        if (requesCode == PICK_ACCOUNT_INFO && resultCode == RESULT_OK) {
            accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        }
    }

    @Override
    public void onClick(View arg0) {
        if (arg0 == mButtonGetRealTimeInfo) {
            //get recent real time information, save to the local storage
            String realTimeContents = "";
            String userName = "";
            int numberOfTasks = 1;
            ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            ActivityManager.RunningTaskInfo runningTaskInfo = activityManager.getRunningTasks(
                    numberOfTasks).get(0);
            realTimeContents = "<" + userName + ">" + "The Recent Task executed is: " + runningTaskInfo.id +
                    "The PID is: " + runningTaskInfo.baseActivity.toShortString();
            ConstantFileHelper constantFileHelper = new ConstantFileHelper();
            String outputFilePath = constantFileHelper.getFilePath();
            constantFileHelper.saveToFile(outputFilePath,realTimeContents);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            if (compoundButton == mSwitchSMS) {
                //SMS Block Class
                SMSBlock smsBlock = new SMSBlock();

            }
            if (compoundButton == mSwitchCall){
                //Call Block Class
                CallBlock callBlock = new CallBlock();

            }
            if (compoundButton == mSwitchMalWare) {

            }
            if (compoundButton == mSwitchWIFI) {
                WIFISecurity wifiSecurity = new WIFISecurity(getBaseContext());
                //if return 0, turns off the WIFI spot
            }
            if (compoundButton == mSwitchMonitor) {

            }
            if (compoundButton == mSwitchDNAPattern) {

            }
        } else {
            if (compoundButton == mSwitchSMS) {
                //SMS Block Class
            }
            if (compoundButton == mSwitchCall) {
                //Call Block Class
            }
            if (compoundButton == mSwitchMalWare) {

            }
            if (compoundButton == mSwitchWIFI) {

            }
            if (compoundButton == mSwitchMonitor) {

            }
            if (compoundButton == mSwitchDNAPattern) {

            }
        }
    }

    public void loginPrompt() {
        //compare the email address and username of the superuser name stored in the database
        //if super user, grant super privileges
        //else, reject rights
    }

    class ConstantFileHelper{
        public ConstantFileHelper(){

        }
        public String getFilePath() {
            String filePath = "";
            filePath = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM).getAbsolutePath();
            if (!(filePath.equals("") || filePath.isEmpty())) {
                filePath += File.separator + "RealTime Records.txt";
            }
            return filePath;
        }
        public void saveToFile(String outputFilePath, String contents) {
            File outputFile = new File(outputFilePath);
            try {
                FileWriter fileWriter = new FileWriter(outputFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(contents + "\n");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

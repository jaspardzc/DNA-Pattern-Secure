package com.mypersonalapp.dnapatternsecure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaspe_000 on 4/29/2015.
 */
public class DNADatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DNA_Pattern";
    private static final String TABLE_USER = "DNA_USER_LIST";
    private static final String USER_ID = "User_ID";
    private static final String USER_NAME = "User_Name";
    private static final String USER_EMAILADDRESS = "User_EmailAddress";
    private static final String USER_PRIVILEGES = "User_Privileges";
    private static final String USER_HISTORY = "User_History";
    public DNADatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_CREATE = "CREATE TABLE " +
                TABLE_USER + "(" + USER_ID + " INTEGER PRIMARY KEY," + USER_NAME + " TEXT," +
                USER_EMAILADDRESS + " TEXT" + ")";
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        //CREATE TABLE AGAIN
        onCreate(sqLiteDatabase);
    }

    /**
     * All Operations: Create, Read, Update, Delete, Send, Retrieve - (CRUDSR)
     */
    public void addUser(UserList userList) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, userList.getUserName());
        contentValues.put(USER_EMAILADDRESS, userList.getUserEmailAddress());
        //inset
        sqLiteDatabase.insert(TABLE_USER,null,contentValues);
        sqLiteDatabase.close();
    }

    public UserList getUser(int userID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_USER,
                new String[] {USER_ID, USER_NAME, USER_EMAILADDRESS}, USER_ID + "=?",
                new String[] {String.valueOf(userID)}, null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        UserList userList = new UserList(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return userList;
    }

    public List<UserList> getAllUsers() {
        List<UserList> userLists = new ArrayList<UserList>();
        String selectQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                UserList userList = new UserList();
                userList.setUserID(Integer.parseInt(cursor.getString(0)));
                userList.setUserName(cursor.getString(1));
                userList.setUserEmailAddress(cursor.getString(2));
                userLists.add(userList);
            }
        }
        return userLists;
    }

    public int getUserNumber() {
        String selectQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        //cursor.close();
        return cursor.getCount();
    }

    public int updateUserList(UserList userList) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, userList.getUserName());
        contentValues.put(USER_EMAILADDRESS, userList.getUserEmailAddress());
        return sqLiteDatabase.update(TABLE_USER, contentValues, USER_ID + " = ?",
                new String[]{String.valueOf(userList.getUserID())});
    }

    public void deleteUser(UserList userList) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_USER, USER_ID + " = ?",
                new String[]{String.valueOf(userList.getUserID())});
        sqLiteDatabase.close();
    }

    public void deleteTable() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_USER, null, null);
    }
}

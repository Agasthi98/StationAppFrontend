package com.example.fuelapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    //Database creation
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "userInfo.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    //Database schema
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserInfo.Users.TABLE_NAME + " (" +
                    UserInfo.Users._ID + " INTEGER PRIMARY KEY," +
                    UserInfo.Users.COLUMN_1 + " TEXT," +
                    UserInfo.Users.COLUMN_2 + " TEXT," +
                    UserInfo.Users.COLUMN_3 + " TEXT,"+
                    UserInfo.Users.COLUMN_4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserInfo.Users.TABLE_NAME;


    //User registration
    public Boolean addInfo(String userName,String phoneNo, String role, String password){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        //Registration schema
// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserInfo.Users.COLUMN_1, userName);
        values.put(UserInfo.Users.COLUMN_2, phoneNo);
        values.put(UserInfo.Users.COLUMN_3, role);
        values.put(UserInfo.Users.COLUMN_4, password);

        //Insert user details
// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserInfo.Users.TABLE_NAME, null, values);
        if(newRowId == -1){
            return false;
        }else
            return true;
    }

    //Check existing user
    public Boolean checkusername(String phoneNo){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from userInfo where phoneNo = ?", new String[] {phoneNo});
        if(cursor.getCount() > 0){
            return true;
        }else
            return false;
    }

    //Normal user login
    public ArrayList getUserInfo(String phoneNo, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from userInfo where phoneNo = ? and password = ? and role = 'User'", new String[] {phoneNo,password});

        ArrayList arrayList = new ArrayList();
        if(cursor.getCount()>=1){
            while(cursor.moveToNext()){
                arrayList.add(cursor.getInt(0));
                arrayList.add(cursor.getString(1));
                arrayList.add(cursor.getString(2));
                arrayList.add(cursor.getString(3));
                arrayList.add(cursor.getString(4));
            }
            cursor.close();
        }
        return arrayList;
    }
    //Shed owner login
//    public ArrayList getInfo(String phoneNo, String password){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from userInfo where phoneNo = ? and password = ? and role = 'ShedOwner'", new String[] {phoneNo,password});
//
//        ArrayList arrayList = new ArrayList();
//        if(cursor.getCount()>=1){
//            while(cursor.moveToNext()){
//                arrayList.add(cursor.getInt(0));
//                arrayList.add(cursor.getString(1));
//                arrayList.add(cursor.getString(2));
//                arrayList.add(cursor.getString(3));
//            }
//            cursor.close();
//        }
//        return arrayList;
//    }

}
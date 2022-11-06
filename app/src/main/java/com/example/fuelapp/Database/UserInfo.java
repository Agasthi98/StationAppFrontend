package com.example.fuelapp.Database;

import android.provider.BaseColumns;

public final class UserInfo {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UserInfo() {}

    /* Inner class that defines the table contents */
    /*
    Database table name & columns
     */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "userInfo";
        public static final String COLUMN_1 = "username";
        public static final String COLUMN_2 = "phoneNo";
        public static final String COLUMN_3 = "role";
        public static final String COLUMN_4 = "vehicle";
        public static final String COLUMN_5 = "password";
    }
}
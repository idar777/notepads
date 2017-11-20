package com.example.aydar.listnotepades.data.dao;
import android.provider.BaseColumns;

/**
 * Created by aydar on 29.08.17.
 */

public class Users {
    public final static String TABLE_NAME = "users";
    public final static String _ID = BaseColumns._ID;
    public final static String COLUMN_LOGIN = "login";
    public final static String COLUMN_PASSWORD = "password";
    public final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                             + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                             + COLUMN_LOGIN + " TEXT NOT NULL, "
                             + COLUMN_PASSWORD + " TEXT NOT NULL);";
}

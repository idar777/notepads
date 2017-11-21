package com.example.aydar.listnotepades.data.db.dao;
import android.content.ContentValues;
import android.provider.BaseColumns;

import com.example.aydar.listnotepades.data.db.dto.User;

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

    public static ContentValues getContentValues(User user){
        ContentValues values = new ContentValues();
        values.put(Users.COLUMN_LOGIN, user.getLogin());
        values.put(Users.COLUMN_PASSWORD, user.getPassword());
        return values;
    }
}

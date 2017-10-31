package com.example.aydar.listnotepades.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by aydar on 29.08.17.
 */

public class Users {
    public final static String TABLE_NAME = "users";
    public final static String _ID = BaseColumns._ID;
    public final static String COLUMN_LOGIN = "login";
    public final static String COLUMN_PASSWORD = "password";

    public static boolean checkUserExists(Context context, String login) throws NoSuchAlgorithmException { //Проверка на существование при регистрации
        NotePadesDBHelper dbHelper;
        login = Utils.changeToMD5(login);
        dbHelper = new NotePadesDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String mQuery = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + COLUMN_LOGIN + " = \"" + login + "\"";

        Cursor cursor = db.rawQuery(mQuery, null);

        if (cursor.getCount() >= 1) {
            return true;
        };
        return false;
    };



    public static Integer checkUser(Context context, String login, String password) throws NoSuchAlgorithmException  { //Проверка доступа при входе
        NotePadesDBHelper dbHelper;
        login = Utils.changeToMD5(login);
        password = Utils.changeToMD5(password);
        dbHelper = new NotePadesDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String mQuery = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + COLUMN_LOGIN + " = \"" + login + "\" AND "
                + COLUMN_PASSWORD + " = \"" + password + "\"";

        Cursor cursor = db.rawQuery(mQuery, null);

        if (cursor.getCount() == 1) {
            try {
                int idUserIndex = cursor.getColumnIndex(_ID);
                while (cursor.moveToNext()) {
                    return cursor.getInt(idUserIndex);
                }
            } finally {
                cursor.close();
                db.close();
            }
        };
        return 0;
    };

    public static long insertUser(Context context,String login, String password) throws NoSuchAlgorithmException{
        NotePadesDBHelper dbHelper;
        login = Utils.changeToMD5(login);
        password = Utils.changeToMD5(password);

        dbHelper = new NotePadesDBHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LOGIN, login);
        values.put(COLUMN_PASSWORD, password);

        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();

        return newRowId;
    }


}

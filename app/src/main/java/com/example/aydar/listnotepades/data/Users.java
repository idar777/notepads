package com.example.aydar.listnotepades.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by aydar on 29.08.17.
 */

public class Users {
    //
    private NotePadesDBHelper dbHelper;

    public boolean checkUserExists(Context context, String mLogin) throws NoSuchAlgorithmException { //Проверка на существование
        dbHelper = new NotePadesDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String mQuery = "SELECT * FROM " + DataBase.Users.TABLE_NAME + " WHERE "
                + DataBase.Users.COLUMN_LOGIN + " = \"" + mLogin + "\"";

        Cursor cursor = db.rawQuery(mQuery, null);

        if (cursor.getCount() >= 1) {
            return true;
        };
        return false;
    };



    public Integer checkUser(Context context, String mLogin, String mPassword){
        dbHelper = new NotePadesDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String mQuery = "SELECT * FROM " + DataBase.Users.TABLE_NAME + " WHERE "
                + DataBase.Users.COLUMN_LOGIN + " = \"" + mLogin + "\" AND "
                + DataBase.Users.COLUMN_PASSWORD + " = \"" + mPassword + "\"";

        Cursor cursor = db.rawQuery(mQuery, null);

        if (cursor.getCount() == 1) {
            try {
                int idUserIndex = cursor.getColumnIndex(DataBase.Users._ID);
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


}

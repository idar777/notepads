package com.example.aydar.listnotepades.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import com.example.aydar.listnotepades.Data.NotePadesDBHelper;
import com.example.aydar.listnotepades.MainActivity;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by aydar on 29.08.17.
 */

public class Users {
    //
    private NotePadesDBHelper mDBHelper;

    public boolean checkUserExists(Context context, String mLogin) throws NoSuchAlgorithmException { //Проверка на существование
        mDBHelper = new NotePadesDBHelper(context);
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        String mQuery = "SELECT * FROM " + DataBase.Users.TABLE_NAME + " WHERE "
                + DataBase.Users.COLUMN_LOGIN + " = \"" + Users.changeToMD5(mLogin) + "\"";

        Cursor cursor = db.rawQuery(mQuery, null);

        if (cursor.getCount() >= 1) {
            return true;
        };
        return false;
    };

    public boolean checkUserName(String mLogin){
        return mLogin.matches("\\w+\\@\\w+\\.\\w+");
    }

    public Integer checkUser(Context context, String mLogin, String mPassword){
        mDBHelper = new NotePadesDBHelper(context);
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

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

    public static String changeToMD5(String initial) throws NoSuchAlgorithmException {
        MessageDigest secure = MessageDigest.getInstance("MD5");
        byte[] md5 = secure.digest(initial.getBytes());
        return String.format("%032X", new BigInteger(1, md5));
    }
}

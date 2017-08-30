package com.example.aydar.listnotepades.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import com.example.aydar.listnotepades.Data.NotePadesDBHelper;
import com.example.aydar.listnotepades.MainActivity;

/**
 * Created by aydar on 29.08.17.
 */

public class Users {
    //
    private NotePadesDBHelper mDBHelper;

    public boolean checkUserExists(String mLogin){ //Проверка вводимых данных и на существование
        return false;
    };

    public boolean checkUserName(String mLogin){
        return true; //Проверка ввода логина;
    }

    public Integer checkUser(Context context, String mLogin, String mPassword){
        mDBHelper = new NotePadesDBHelper(context);
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        String mQuery = "SELECT * FROM " + DataBase.Users.TABLE_NAME + " WHERE "
                + DataBase.Users.COLUMN_LOGIN + " = " + mLogin + " AND "
                + DataBase.Users.COLUMN_PASSWORD + " = " + mPassword;

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

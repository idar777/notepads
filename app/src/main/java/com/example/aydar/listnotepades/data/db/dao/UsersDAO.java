package com.example.aydar.listnotepades.data.db.dao;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.aydar.listnotepades.data.db.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.db.dto.User;

import java.util.List;

/**
 * Created by aydar on 08.11.17.
 */

public class UsersDAO implements IDao<User> {

    private static final String TAG = UsersDAO.class.getSimpleName();

    private NotePadesDBHelper dbHelper;

    public UsersDAO(NotePadesDBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        try {
            db.execSQL(Users.CREATE_TABLE);
            int i = 5;
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public void dropTable(SQLiteDatabase db) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + Users.TABLE_NAME);
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public List<User> select() {
        return null;
    }

    @Override
    public long insert(User item) {
        long newRowId = -1;
        SQLiteDatabase db = null;

        try {
            db = this.dbHelper.getWritableDatabase();
            newRowId = db.insert(Users.TABLE_NAME, null, Users.getContentValues(item));
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return newRowId;
    }

    @Override
    public void update(User item) {
    }

    @Override
    public void remove(User item) {
    }

    public int checkUser(User item, boolean isEntrance) {

        String entranceData = "";
        if (isEntrance) {
            entranceData = " AND " + Users.COLUMN_PASSWORD + " = \"" + item.getPassword() + "\"";
        }
        String mQuery = "SELECT * FROM " + Users.TABLE_NAME + " WHERE "
                + Users.COLUMN_LOGIN + " = \"" + item.getLogin() + "\"" + entranceData;
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = this.dbHelper.getWritableDatabase();
            cursor = db.rawQuery(mQuery, null);
            if (cursor != null && cursor.moveToFirst()) {
                int idUserIndex = cursor.getColumnIndex(Users._ID);
                while (cursor.moveToNext()) {
                    return cursor.getInt(idUserIndex);
                }
            }
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return 0;
    }

    @Override
    public String getNameOfTable() {
        return Users.TABLE_NAME;
    }
}

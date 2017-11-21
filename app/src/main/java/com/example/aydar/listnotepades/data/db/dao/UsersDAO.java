package com.example.aydar.listnotepades.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.domain.Utils;
import com.example.aydar.listnotepades.data.dto.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by aydar on 08.11.17.
 */

public class UsersDAO implements IDao<User> {

    private NotePadesDBHelper dbHelper;

    public UsersDAO(NotePadesDBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void createTable() {
        SQLiteDatabase db = null;
        try {
            db = this.dbHelper.getWritableDatabase();
            db.execSQL(Users.CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    @Override
    public void dropTable() {
        SQLiteDatabase db = null;
        try {
            db = this.dbHelper.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + Users.TABLE_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    @Override
    public List<User> select() {
        return null;
    }

    @Override
    public long insert(User item) {
        long newRowId = 0;
        SQLiteDatabase db =null;

        ContentValues values = new ContentValues();
        values.put(Users.COLUMN_LOGIN, item.getLogin());
        values.put(Users.COLUMN_PASSWORD, item.getPassword());

        try{
            db = this.dbHelper.getWritableDatabase();
            newRowId = db.insert(Users.TABLE_NAME, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
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
        try {
            item.setLogin(Utils.changeToMD5(item.getLogin()));
            item.setPassword(Utils.changeToMD5(item.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

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
            e.printStackTrace();
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

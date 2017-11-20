package com.example.aydar.listnotepades.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.Utils;
import com.example.aydar.listnotepades.data.dto.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by aydar on 08.11.17.
 */

public class UsersDAO implements Idao<User> {

    private SQLiteDatabase db;
    private NotePadesDBHelper dbHelper;

    public UsersDAO(NotePadesDBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void createTable() {
        db = this.dbHelper.getWritableDatabase();
        db.execSQL(Users.CREATE_TABLE);
        db.close();
    }

    @Override
    public void dropTable() {
        db = this.dbHelper.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + Users.TABLE_NAME);
        db.close();
    }

    @Override
    public List<User> select() {
        return null;
    }

    @Override
    public long insert(User item) throws NoSuchAlgorithmException {
        db = this.dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Users.COLUMN_LOGIN, item.getLogin());
        values.put(Users.COLUMN_PASSWORD, item.getPassword());

        long newRowId = db.insert(Users.TABLE_NAME, null, values);
        db.close();

        return newRowId;
    }

    @Override
    public void update(User item) {
    }

    @Override
    public void remove(User item) {
    }

    public int checkUser(User item, Boolean entrance) throws NoSuchAlgorithmException {
        db = this.dbHelper.getWritableDatabase();
        item.setLogin(Utils.changeToMD5(item.getLogin()));
        item.setPassword(Utils.changeToMD5(item.getPassword()));

        String entranceData = "";
        if (entrance){
            entranceData =  " AND " + Users.COLUMN_PASSWORD + " = \"" + item.getPassword() + "\"";
        }

        String mQuery = "SELECT * FROM " + Users.TABLE_NAME + " WHERE "
                + Users.COLUMN_LOGIN + " = \"" + item.getLogin() + "\"" + entranceData;

        Cursor cursor = db.rawQuery(mQuery, null);

        if (cursor.getCount() == 1) {
            try {
                int idUserIndex = cursor.getColumnIndex(Users._ID);
                while (cursor.moveToNext()) {
                    return cursor.getInt(idUserIndex);
                }
            } finally {
                cursor.close();
                db.close();
            }
        };
        return 0;
    }

    @Override
    public String getNameOfTable() {
        return Users.TABLE_NAME;
    }
}

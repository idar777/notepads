package com.example.aydar.listnotepades.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aydar on 22.08.17.
 */

public class NotePadesDBHelper extends SQLiteOpenHelper{


    private static final String DATABASE_NAME = "notepads.db";

    private static final int DATABASE_VERSION = 4;

    public NotePadesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS " + DataBase.Users.TABLE_NAME + " ("
                + DataBase.Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DataBase.Users.COLUMN_LOGIN + " TEXT NOT NULL, "
                + DataBase.Users.COLUMN_PASSWORD + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_USERS_TABLE);

        String SQL_CREATE_NOTES_TABLE = "CREATE TABLE IF NOT EXISTS " + DataBase.Notes.TABLE_NAME + " ("
                + DataBase.Notes._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DataBase.Notes.USER_ID + " INTEGER, "
                + DataBase.Notes.NOTE_ID + " INTEGER, "
                + DataBase.Notes.COLUMN_NAME + " TEXT NOT NULL, "
                + DataBase.Notes.COLUMN_TEXT + " TEXT NOT NULL, "
                + DataBase.Notes.COLUMN_DATE + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBase.Users.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataBase.Notes.TABLE_NAME);

        // Создаём новую таблицу
        onCreate(db);

    }
}

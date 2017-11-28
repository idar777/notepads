package com.example.aydar.listnotepades.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aydar.listnotepades.data.db.dao.Notes;
import com.example.aydar.listnotepades.data.db.dao.NotesDAO;
import com.example.aydar.listnotepades.data.db.dao.Users;
import com.example.aydar.listnotepades.data.db.dao.UsersDAO;

/**
 * Created by aydar on 22.08.17.
 */

public class NotePadesDBHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "notepads.db";
    private static final int DATABASE_VERSION = 7;
    private DatabaseDAOFactory factory;

    public NotePadesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        factory = new DatabaseDAOFactory(this);
        factory.getDaoInstance(UsersDAO.class).createTable(db);
        factory.getDaoInstance(NotesDAO.class).createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        factory = new DatabaseDAOFactory(this);
        factory.getDaoInstance(UsersDAO.class).dropTable(db);
        factory.getDaoInstance(NotesDAO.class).dropTable(db);
        // Создаём новую таблицу
        onCreate(db);
    }
}

package com.example.aydar.listnotepades.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.aydar.listnotepades.data.db.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.db.dto.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aydar on 09.11.17.
 */

public class NotesDAO implements IDao<Note> {

    private NotePadesDBHelper dbHelper;

    public NotesDAO(NotePadesDBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        try {
            db.execSQL(Notes.CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable(SQLiteDatabase db) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + Users.TABLE_NAME);
        } catch (SQLException e) {
        }
    }

    @Override
    public List<Note> select() {
        return null;
    }

    @Override
    public long insert(Note item) {
        SQLiteDatabase db = null;
        long newRowId = -1;

        try {
            db = this.dbHelper.getWritableDatabase();
            newRowId = db.insert(Notes.TABLE_NAME, null, Notes.getContentValues(item));
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
    public void update(Note item) {
        SQLiteDatabase db = null;

        try {
            db = this.dbHelper.getWritableDatabase();
            String mQuery = "UPDATE " + Notes.TABLE_NAME + " SET " + Notes.COLUMN_NAME + " = \"" +
                    item.getName() + "\", " + Notes.COLUMN_TEXT + " = \"" + item.getText() + "\" WHERE " + Notes._ID + " = " +
                    item.getId();
            db.execSQL(mQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    @Override
    public void remove(Note item) {
        SQLiteDatabase db = null;

        try {
            db = this.dbHelper.getWritableDatabase();
            String mQuery = "DELETE FROM " + Notes.TABLE_NAME + " WHERE " + Notes._ID + " = " + item.getId();
            db.execSQL(mQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    @Override
    public String getNameOfTable() {
        return Notes.TABLE_NAME;
    }

    public Note getNote(long idNote) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        Note note = new Note(idNote);
        String mQuery = "SELECT * FROM " + Notes.TABLE_NAME + " WHERE "
                + Notes._ID + " = " + String.valueOf(idNote);

        try {
            db = this.dbHelper.getReadableDatabase();

            cursor = db.rawQuery(mQuery, null);

            int nameColumnIndex = cursor.getColumnIndex(Notes.COLUMN_NAME);
            int textColumnIndex = cursor.getColumnIndex(Notes.COLUMN_TEXT);

            while (cursor.moveToNext()) {
                note.setName(cursor.getString(nameColumnIndex));
                note.setText(cursor.getString(textColumnIndex));
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
        return note;
    }

    public List<Note> getNotesList(long idUser) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<Note> noteList = new ArrayList();

        String mQuery = "SELECT * FROM " + Notes.TABLE_NAME + " WHERE "
                + Notes.USER_ID + " = " + idUser;

        try {
            db = this.dbHelper.getReadableDatabase();
            cursor = db.rawQuery(mQuery, null);

            if (cursor.getCount() != 0) {
                int idColumnIndex = cursor.getColumnIndex(Notes._ID);
                int nameColumnIndex = cursor.getColumnIndex(Notes.COLUMN_NAME);
                int textColumnIndex = cursor.getColumnIndex(Notes.COLUMN_TEXT);

                while (cursor.moveToNext()) {
                    Note noteItem = new Note(cursor.getLong(idColumnIndex));
                    noteItem.setName(cursor.getString(nameColumnIndex));
                    noteItem.setText(cursor.getString(textColumnIndex));
                    noteList.add(noteItem);
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
        };
        return noteList;
    }
}

package com.example.aydar.listnotepades.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.dto.Note;

import java.security.NoSuchAlgorithmException;
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
    public void createTable() {
        SQLiteDatabase db = null;

        try {
            db = this.dbHelper.getWritableDatabase();
            db.execSQL(Notes.CREATE_TABLE);
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
    }

    @Override
    public List<Note> select() {
        return null;
    }

    @Override
    public long insert(Note item) {
        SQLiteDatabase db = null;
        long newRowId = 0;
        ContentValues values = new ContentValues();

        values.put(Notes.NOTE_ID, 0);
        values.put(Notes.USER_ID, item.getUserID());
        values.put(Notes.COLUMN_NAME, item.getName());
        values.put(Notes.COLUMN_TEXT, item.getText());
        values.put(Notes.COLUMN_DATE, item.getDate());

        try {
            db = this.dbHelper.getWritableDatabase();
            newRowId = db.insert(Notes.TABLE_NAME, null, values);
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

    public ArrayList<Note> getNotesList(long idUser ) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        ArrayList<Note> noteList = new ArrayList();

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

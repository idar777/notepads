package com.example.aydar.listnotepades.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.dto.Note;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aydar on 09.11.17.
 */

public class NotesDAO implements Idao<Note> {

    private SQLiteDatabase db;
    private NotePadesDBHelper dbHelper;

    public NotesDAO(NotePadesDBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void createTable() {
        db = this.dbHelper.getWritableDatabase();
        db.execSQL(Notes.CREATE_TABLE);
        db.close();
    }

    @Override
    public void dropTable() {
    }

    @Override
    public List<Note> select() {
        return null;
    }

    @Override
    public long insert(Note item) throws NoSuchAlgorithmException {
        db = this.dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Notes.NOTE_ID, 0);
        values.put(Notes.USER_ID, item.getUserID());
        values.put(Notes.COLUMN_NAME, item.getName());
        values.put(Notes.COLUMN_TEXT, item.getText());
        values.put(Notes.COLUMN_DATE, "Today");

        long newRowId = db.insert(Notes.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    @Override
    public void update(Note item) {
        db = this.dbHelper.getWritableDatabase();
        String mQuery = "UPDATE " + Notes.TABLE_NAME + " SET " + Notes.COLUMN_NAME + " = \"" +
                item.getName() + "\", " + Notes.COLUMN_TEXT + " = \"" + item.getText() + "\" WHERE " + Notes._ID + " = " +
                item.getId();
        db.execSQL(mQuery);
        db.close();
    }

    @Override
    public void remove(Note item) {
        db = this.dbHelper.getWritableDatabase();
        String mQuery = "DELETE FROM " + Notes.TABLE_NAME + " WHERE " + Notes._ID + " = " + item.getId();
        db.execSQL(mQuery);
        db.close();
    }

    @Override
    public String getNameOfTable() {
        return Notes.TABLE_NAME;
    }

    public Note getNote(long idNote) {
        db = this.dbHelper.getReadableDatabase();
        Note note = new Note(idNote);

        String mQuery = "SELECT * FROM " + Notes.TABLE_NAME + " WHERE "
                + Notes._ID + " = " + String.valueOf(idNote);
        Cursor cursor = db.rawQuery(mQuery, null);

        try {
            int nameColumnIndex = cursor.getColumnIndex(Notes.COLUMN_NAME);
            int textColumnIndex = cursor.getColumnIndex(Notes.COLUMN_TEXT);

            while (cursor.moveToNext()) {
                note.setName(cursor.getString(nameColumnIndex));
                note.setText(cursor.getString(textColumnIndex));
            }
        } finally {
            cursor.close();
            db.close();
        }
        return note;
    }

    public ArrayList<Note> getNotesList(long idUser ) {
        db = this.dbHelper.getReadableDatabase();
        ArrayList<Note> noteList = new ArrayList();

        String mQuery = "SELECT * FROM " + Notes.TABLE_NAME + " WHERE "
                + Notes.USER_ID + " = " + idUser;
        Cursor cursor = db.rawQuery(mQuery, null);

        try {
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
            if (cursor != null) {
                cursor.close();
            }
        } finally {
            db.close();
        };
        return noteList;
    }
}

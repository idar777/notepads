package com.example.aydar.listnotepades.data.db.dao;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.example.aydar.listnotepades.data.db.dto.Note;
import com.example.aydar.listnotepades.data.db.dto.User;

/**
 * Created by aydar on 31.08.17.
 */

public class Notes {
    public final static String TABLE_NAME = "notes";
    public final static String _ID = BaseColumns._ID;
    public final static String USER_ID = "user_id";
    public final static String NOTE_ID = "note_id";
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_TEXT = "text";
    public final static String COLUMN_DATE = "date";
    public final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + Notes.TABLE_NAME + " ("
            + Notes._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Notes.USER_ID + " INTEGER, "
            + Notes.NOTE_ID + " INTEGER, "
            + Notes.COLUMN_NAME + " TEXT NOT NULL, "
            + Notes.COLUMN_TEXT + " TEXT NOT NULL, "
            + Notes.COLUMN_DATE + " TEXT NOT NULL);";

    public static ContentValues getContentValues(Note note){
        ContentValues values = new ContentValues();
        values.put(Notes.NOTE_ID, 0);
        values.put(Notes.USER_ID, note.getUserID());
        values.put(Notes.COLUMN_NAME, note.getName());
        values.put(Notes.COLUMN_TEXT, note.getText());
        values.put(Notes.COLUMN_DATE, note.getDate());
        return values;
    }
}

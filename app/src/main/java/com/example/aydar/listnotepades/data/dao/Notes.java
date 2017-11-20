package com.example.aydar.listnotepades.data.dao;

import android.content.Context;
import android.provider.BaseColumns;

import com.example.aydar.listnotepades.data.NotePadesDBHelper;

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
}

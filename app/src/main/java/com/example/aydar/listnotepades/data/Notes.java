package com.example.aydar.listnotepades.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by aydar on 31.08.17.
 */

public class Notes {

    NotePadesDBHelper dbHelper;

    public long addNote(Context context, String idUser,String mName, String mText){
        dbHelper = new NotePadesDBHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DataBase.Notes.NOTE_ID, 0);
        values.put(DataBase.Notes.USER_ID, idUser);
        values.put(DataBase.Notes.COLUMN_NAME, mName);
        values.put(DataBase.Notes.COLUMN_TEXT, mText);
        values.put(DataBase.Notes.COLUMN_DATE, "Today");

        long newRowId = db.insert(DataBase.Notes.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public void changeNote(Context context, long idNote, String mName, String mText){
        dbHelper = new NotePadesDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String mQuery = "UPDATE " + DataBase.Notes.TABLE_NAME + " SET " + DataBase.Notes.COLUMN_NAME + " = \"" +
                mName + "\", " + DataBase.Notes.COLUMN_TEXT + " = \"" + mText + "\" WHERE " + DataBase.Notes._ID + " = " +
                idNote;

        db.execSQL(mQuery);

    }

    public void deleteNote(Context context, long idNote){
        dbHelper = new NotePadesDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String mQuery = "DELETE FROM " + DataBase.Notes.TABLE_NAME + " WHERE " + DataBase.Notes._ID + " = " + idNote;
        db.execSQL(mQuery);
    }
}

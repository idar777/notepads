package com.example.aydar.listnotepades.data;

import android.content.Context;
import android.provider.BaseColumns;

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

    public String name;
    public String text;

    public Notes(String name, String text){
        this.name = name;
        this.text =text;
    }

    public long addNote(Context context, String idUser,String mName, String mText){
        return NotePadesDBHelper.addNoteDB(context, idUser, mName, mText);
    }

    public void changeNote(Context context, long idNote, String mName, String mText){
        NotePadesDBHelper.changeNoteDB(context, idNote, mName, mText);
    }

    public void deleteNote(Context context, long idNote) {
        NotePadesDBHelper.deleteNoteDB(context, idNote);
    }
}

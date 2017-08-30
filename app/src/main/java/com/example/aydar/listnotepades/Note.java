package com.example.aydar.listnotepades;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aydar.listnotepades.Data.DataBase;
import com.example.aydar.listnotepades.Data.NotePadesDBHelper;

public class Note extends AppCompatActivity {

    private NotePadesDBHelper mDBHelper;

    String type = new String();
    String idUser = new String();
    String idNote = new String();
    String mNameString = new String();
    String mTextString = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        type = getIntent().getStringExtra("type");
        idUser = getIntent().getStringExtra("id_user");
        idNote = getIntent().getStringExtra("id_note");

        if (type == "edit") {
            loadData();
        }
    }

    private void loadData() {
        mDBHelper = new NotePadesDBHelper(this);

        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        EditText mName = (EditText)findViewById(R.id.editName);
        EditText mText = (EditText)findViewById(R.id.editText4);

        String mQuery = "SELECT * FROM " + DataBase.Notes.TABLE_NAME + " WHERE "
                + DataBase.Notes.USER_ID + " = " + idUser + " AND "
                + DataBase.Notes.NOTE_ID + " = " + idNote;

        Cursor cursor = db.rawQuery(mQuery, null);

        try {
            int textColumnIndex = cursor.getColumnIndex(DataBase.Notes.COLUMN_TEXT);

            while (cursor.moveToNext()) {
                String currentText = cursor.getString(textColumnIndex);
                mText.setText(currentText);
            }
        } finally {
            cursor.close();
        }
    }

    private void newNote() {
        mDBHelper = new NotePadesDBHelper(this);

        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        EditText mName = (EditText)findViewById(R.id.editName);
        EditText mText = (EditText)findViewById(R.id.editText4);

        mNameString = mName.getText().toString().trim();
        mTextString = mText.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(DataBase.Notepads.COLUMN_NAME, mNameString);
        values.put(DataBase.Notepads.USER_ID, idUser);
        values.put(DataBase.Notepads.COLUMN_DATE, "toDay");

        long newRowId = db.insert(DataBase.Notepads.TABLE_NAME, null, values);

        ContentValues values2 = new ContentValues();
        values2.put(DataBase.Notes.NOTE_ID, newRowId);
        values2.put(DataBase.Notes.USER_ID, idUser);
        values2.put(DataBase.Notes.COLUMN_TEXT, mTextString);
        values2.put(DataBase.Notes.COLUMN_DATE, "Today");

        long newRowId2 = db.insert(DataBase.Notes.TABLE_NAME, null, values2);
        if (newRowId2 == -1) {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Создан под номером" + newRowId2, Toast.LENGTH_SHORT).show();
        }

    }


    public void saveContentNote(View view) {
        newNote();
    }
}

package com.example.aydar.listnotepades;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aydar.listnotepades.Data.DataBase;
import com.example.aydar.listnotepades.Data.NotePadesDBHelper;
import com.example.aydar.listnotepades.Data.Notes;

public class Note extends AppCompatActivity {

    private NotePadesDBHelper mDBHelper;

    String type = new String();
    String idUser;
    Integer idNote;
    String mNameString = new String();
    String mTextString = new String();
    Notes mNote = new Notes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Button bDelete = (Button)findViewById(R.id.button5);

        type = getIntent().getStringExtra("type");
        idUser = getIntent().getStringExtra("id_user");


        if (type.equals("edit")) {
            idNote = Integer.valueOf(getIntent().getStringExtra("id_note"));
            loadData(idNote);
            bDelete.setEnabled(true);
        } else {
            bDelete.setEnabled(false);
        }
    }

    private void loadData(Integer idNote) {
        mDBHelper = new NotePadesDBHelper(this);

        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        EditText mName = (EditText)findViewById(R.id.editName);
        EditText mText = (EditText)findViewById(R.id.editText4);

        String mQuery = "SELECT * FROM " + DataBase.Notes.TABLE_NAME + " WHERE "
                + DataBase.Notes.USER_ID + " = " + idUser + " AND "
                + DataBase.Notes._ID + " = " + idNote.toString();
        Cursor cursor = db.rawQuery(mQuery, null);

        try {
            int nameColumnIndex = cursor.getColumnIndex(DataBase.Notes.COLUMN_NAME);
            int textColumnIndex = cursor.getColumnIndex(DataBase.Notes.COLUMN_TEXT);

            while (cursor.moveToNext()) {
                String currentName = cursor.getString(nameColumnIndex);
                String currentText = cursor.getString(textColumnIndex);

                mName.setText(currentName);
                mText.setText(currentText);
            }
        } finally {
            cursor.close();
            db.close();
        }
    }

    private void newNote(String mName, String mText) {
        if ((mName.isEmpty())||(mText.isEmpty())){
            Toast.makeText(this, "Нельзя добавлять пустые записи!", Toast.LENGTH_SHORT).show();
        } else {
            long idNote = mNote.addNote(this, idUser, mName, mText);

            if (idNote == -1) {
                Toast.makeText(this, "Ошибка записи", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void saveContentNote(View view) {
        EditText mName = (EditText)findViewById(R.id.editName);
        EditText mText = (EditText)findViewById(R.id.editText4);

        mNameString = mName.getText().toString().trim();
        mTextString = mText.getText().toString().trim();

        if (!mNameString.isEmpty() & !mTextString.isEmpty()){
            if (type.equals("edit")) {
                mNote.changeNote(this, idNote, mNameString, mTextString);
            } else {
                newNote(mNameString, mTextString);
            }
            Intent intent = new Intent(Note.this, ListNotes.class);
            intent.putExtra("id_user", idUser);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Нельзя вводить пустые значения!", Toast.LENGTH_SHORT).show();
        }


    }

    public void deleteCurrentNote(View view) {
        EditText mName = (EditText)findViewById(R.id.editName);
        EditText mText = (EditText)findViewById(R.id.editText4);

        mNameString = mName.getText().toString().trim();
        mTextString = mText.getText().toString().trim();

        mNote.deleteNote(this, idNote);

        Intent intent = new Intent(Note.this, ListNotes.class);
        intent.putExtra("id_user", idUser);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Note.this, ListNotes.class);
        intent.putExtra("id_user", idUser);
        startActivity(intent);
    }
}

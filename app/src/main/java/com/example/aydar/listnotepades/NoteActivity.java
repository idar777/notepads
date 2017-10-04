package com.example.aydar.listnotepades;

import android.app.Activity;
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


public class NoteActivity extends Activity {

    private NotePadesDBHelper mDBHelper;

    private String type;
    private String idUser;
    private Integer idNote;
    private String mNameString = new String();
    private String mTextString = new String();
    private Notes mNote = new Notes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Button bDelete = (Button)findViewById(R.id.buttonDelete);

        type = getIntent().getStringExtra(StartActivity.OPEN_TYPE);
        idUser = getIntent().getStringExtra(StartActivity.USER_ID);


        if (type.equals(StartActivity.EDIT_TYPE)) {
            idNote = Integer.valueOf(getIntent().getStringExtra(StartActivity.NOTE_ID));
            loadData(idNote);
            bDelete.setEnabled(true);
        } else {
            bDelete.setEnabled(false);
        }
    }

    private void loadData(Integer idNote) {
        mDBHelper = new NotePadesDBHelper(this);

        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        EditText mName = (EditText)findViewById(R.id.editTextName);
        EditText mText = (EditText)findViewById(R.id.editTextText);

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
            Toast.makeText(this, R.string.error_empty_data, Toast.LENGTH_SHORT).show();
        } else {
            long idNote = mNote.addNote(this, idUser, mName, mText);
        }
    }


    public void saveContentNote(View view) {
        EditText mName = (EditText)findViewById(R.id.editTextName);
        EditText mText = (EditText)findViewById(R.id.editTextText);

        mNameString = mName.getText().toString().trim();
        mTextString = mText.getText().toString().trim();

        if (!mNameString.isEmpty() & !mTextString.isEmpty()){
            if (type.equals(StartActivity.EDIT_TYPE)) {
                mNote.changeNote(this, idNote, mNameString, mTextString);
            } else {
                newNote(mNameString, mTextString);
            }
            startActivity(StartActivity.newIntent(NoteActivity.this, idUser));
        } else {
            Toast.makeText(this, R.string.error_empty_data, Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteCurrentNote(View view) {
        EditText mName = (EditText)findViewById(R.id.editTextName);
        EditText mText = (EditText)findViewById(R.id.editTextText);

        mNameString = mName.getText().toString().trim();
        mTextString = mText.getText().toString().trim();

        mNote.deleteNote(this, idNote);

        startActivity(StartActivity.newIntent(NoteActivity.this, idUser));
    }

    @Override
    public void onBackPressed() {
        startActivity(StartActivity.newIntent(NoteActivity.this, idUser));
    }
}

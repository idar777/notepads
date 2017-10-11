package com.example.aydar.listnotepades;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aydar.listnotepades.data.DataBase;
import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.Notes;

import static com.example.aydar.listnotepades.StartActivity.USER_ID;


public class NoteActivity extends AppCompatActivity {

    private NotePadesDBHelper dbHelper;
    private String type;
    private String idUser;
    private Integer idNote;
    private String nameString = new String();
    private String textString = new String();
    private Notes note = new Notes();
    private EditText name;
    private EditText text;

    public static final Intent newIntent(Context context, String idUser, String editType, String noteId) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(USER_ID, idUser);
        intent.putExtra(StartActivity.OPEN_TYPE, editType);
        intent.putExtra(StartActivity.NOTE_ID, noteId);
        return intent;
    }

    public static final Intent newIntent(Context context, String idUser, String editType) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(USER_ID, idUser);
        intent.putExtra(StartActivity.OPEN_TYPE, editType);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Button bDelete = (Button)findViewById(R.id.delete_button);
        name = (EditText)findViewById(R.id.name_note_edit_text);
        text = (EditText)findViewById(R.id.content_edit_text);

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
        dbHelper = new NotePadesDBHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

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

                name.setText(currentName);
                text.setText(currentText);
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
            long idNote = note.addNote(this, idUser, mName, mText);
        }
    }


    public void saveContentNote(View view) {
        nameString = name.getText().toString().trim();
        textString = text.getText().toString().trim();

        if (!nameString.isEmpty() & !textString.isEmpty()){
            if (type.equals(StartActivity.EDIT_TYPE)) {
                note.changeNote(this, idNote, nameString, textString);
            } else {
                newNote(nameString, textString);
            }
            startActivity(ListNotesActivity.newIntent(NoteActivity.this, idUser));
        } else {
            Toast.makeText(this, R.string.error_empty_data, Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteCurrentNote(View view) {
        nameString = name.getText().toString().trim();
        textString = text.getText().toString().trim();

        note.deleteNote(this, idNote);

        startActivity(ListNotesActivity.newIntent(NoteActivity.this, idUser));
    }

    @Override
    public void onBackPressed() {
        startActivity(ListNotesActivity.newIntent(NoteActivity.this, idUser));
    }
}

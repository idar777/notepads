package com.example.aydar.listnotepades.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aydar.listnotepades.R;
import com.example.aydar.listnotepades.data.db.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.db.dao.NotesDAO;
import com.example.aydar.listnotepades.data.db.dto.Note;
import com.example.aydar.listnotepades.presentation.presenters.NotePresenter;


public class NoteActivity extends AppCompatActivity implements INoteView {

    private String type;
    private long userId;
    private Integer idNote = 0;
    private EditText name;
    private EditText text;
    NotePresenter notePresenter = new NotePresenter();
    Note note;
    NotePadesDBHelper dbHelper;
    NotesDAO notesDAO;

    public static final Intent newIntent(Context context, long idUser, String editType, String noteId) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(StartActivity.USER_ID, idUser);
        intent.putExtra(StartActivity.OPEN_TYPE, editType);
        intent.putExtra(StartActivity.NOTE_ID, noteId);
        return intent;
    }

    public static final Intent newIntent(Context context, long idUser, String editType) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(StartActivity.USER_ID, idUser);
        intent.putExtra(StartActivity.OPEN_TYPE, editType);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        dbHelper = new NotePadesDBHelper(this);
        notesDAO = new NotesDAO(dbHelper);

        Button bDelete = (Button)findViewById(R.id.delete_button);
        name = (EditText)findViewById(R.id.name_note_edit_text);
        text = (EditText)findViewById(R.id.content_edit_text);

        type = getIntent().getStringExtra(StartActivity.OPEN_TYPE);
        userId = getIntent().getLongExtra(StartActivity.USER_ID, 0);

        if (type.equals(StartActivity.EDIT_TYPE)) {
            idNote = Integer.valueOf(getIntent().getStringExtra(StartActivity.NOTE_ID));
            note = notePresenter.loadNote(this, idNote);
            name.setText(note.getName());
            text.setText(note.getText());
            bDelete.setEnabled(true);
        } else {
            bDelete.setEnabled(false);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        notePresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        notePresenter.detachView(this);
    }

    public void saveContentNoteOnClick(View view) {
        note = new Note(userId, name.getText().toString().trim(), text.getText().toString().trim());
        note.setId(idNote);
        note.setDate("Today");
        notePresenter.updateNote(this, note, type);
    }

    public void deleteCurrentNoteOnClick(View view) {
        notePresenter.deleteNote(this, idNote);

        startActivity(ListNotesActivity.newIntent(NoteActivity.this, userId));
    }

    public void showError(int resID) {
        Toast.makeText(this, getResources().getText(resID), Toast.LENGTH_SHORT).show();
    }

    public void updateListNotes(){
        startActivity(ListNotesActivity.newIntent(NoteActivity.this, userId));
    }

    @Override
    public void onBackPressed() {
        startActivity(ListNotesActivity.newIntent(NoteActivity.this, userId));
    }
}

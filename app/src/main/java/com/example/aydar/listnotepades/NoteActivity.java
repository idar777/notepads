package com.example.aydar.listnotepades;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.dao.NotesDAO;
import com.example.aydar.listnotepades.data.dto.Note;

import java.security.NoSuchAlgorithmException;

import static com.example.aydar.listnotepades.StartActivity.USER_ID;


public class NoteActivity extends AppCompatActivity {

    private String type;
    private long userId;
    private Integer idNote;
    private EditText name;
    private EditText text;
    Note note;
    NotePadesDBHelper dbHelper;
    NotesDAO notesDAO;

    public static final Intent newIntent(Context context, long idUser, String editType, String noteId) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(USER_ID, idUser);
        intent.putExtra(StartActivity.OPEN_TYPE, editType);
        intent.putExtra(StartActivity.NOTE_ID, noteId);
        return intent;
    }

    public static final Intent newIntent(Context context, long idUser, String editType) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(USER_ID, idUser);
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
            loadData(idNote);
            bDelete.setEnabled(true);
        } else {
            bDelete.setEnabled(false);
        }
    }

    private void loadData(Integer idNote) {
        note = notesDAO.getNote(idNote);
        name.setText(note.getName());
        text.setText(note.getText());
    }

    public void saveContentNote(View view) throws NoSuchAlgorithmException {
        note = new Note(userId, name.getText().toString().trim(), text.getText().toString().trim());

        if (!note.getName().isEmpty() & !note.getText().isEmpty()){
            if (type.equals(StartActivity.EDIT_TYPE)) {
                note.setId(idNote);
                notesDAO.update(note);
            } else {
                long idNote = notesDAO.insert(note);
            }
            startActivity(ListNotesActivity.newIntent(NoteActivity.this, userId));
        } else {
            Toast.makeText(this, R.string.error_empty_data, Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteCurrentNote(View view) {
        note = new Note(idNote);
        notesDAO.remove(note);

        startActivity(ListNotesActivity.newIntent(NoteActivity.this, userId));
    }

    @Override
    public void onBackPressed() {
        startActivity(ListNotesActivity.newIntent(NoteActivity.this, userId));
    }
}

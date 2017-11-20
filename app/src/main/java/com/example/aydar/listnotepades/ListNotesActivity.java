package com.example.aydar.listnotepades;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.dao.NotesDAO;
import com.example.aydar.listnotepades.data.dto.Note;

import java.util.ArrayList;

import static com.example.aydar.listnotepades.StartActivity.USER_ID;

public class ListNotesActivity extends AppCompatActivity {
    private ArrayList<Note> listNotes = new ArrayList();
    private ArrayList<String> listNames = new ArrayList();
    private RecyclerView recyclerView;

    private long idUser;
    private Integer posItem;

    NotePadesDBHelper dbHelper;
    NotesDAO notesDAO;

    public static final Intent newIntent(Context context, long idUser) {
        Intent intent = new Intent(context, ListNotesActivity.class);
        intent.putExtra(USER_ID, idUser);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        dbHelper = new NotePadesDBHelper(this);
        notesDAO = new NotesDAO(dbHelper);

        recyclerView = (RecyclerView)findViewById(R.id.notes_recycler_view);

        LinearLayoutManager llr = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llr);
        recyclerView.setHasFixedSize(true);

        idUser = getIntent().getLongExtra(USER_ID, 0);

        listNotes = notesDAO.getNotesList(idUser);
        for (int i = 0; i < listNotes.size(); i++) {
            Note note = listNotes.get(i);
            listNames.add(note.getName());
        }

        RVAdapter adapter = new RVAdapter(listNames, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                posItem = position;
                startActivity(NoteActivity.newIntent(ListNotesActivity.this, idUser, StartActivity.EDIT_TYPE, getIDNote(posItem)));
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private String getIDNote(Integer position) {
        return String.valueOf(listNotes.get(position).getId());
    }

    public void addNewNote(View view) {
        startActivity(NoteActivity.newIntent(ListNotesActivity.this, idUser, StartActivity.NEW_TYPE));
    }

    @Override
    public void onBackPressed() {
        openQuitDialog();
    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                ListNotesActivity.this);
        quitDialog.setTitle(R.string.escape_text);

        quitDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        quitDialog.show();
    }
}

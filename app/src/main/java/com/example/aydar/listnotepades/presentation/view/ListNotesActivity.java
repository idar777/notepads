package com.example.aydar.listnotepades.presentation.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.aydar.listnotepades.R;
import com.example.aydar.listnotepades.data.db.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.db.dao.NotesDAO;
import com.example.aydar.listnotepades.presentation.presenters.ListNotesPresenter;

import java.util.ArrayList;
import java.util.List;

public class ListNotesActivity extends AppCompatActivity implements IListNotesView {
    private List<String> listNames = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListNotesPresenter listNotesPresenter = new ListNotesPresenter();;
    private long idUser;
    private Integer posItem;
    private NotePadesDBHelper dbHelper;
    private NotesDAO notesDAO;

    public static final Intent newIntent(Context context, long idUser) {
        Intent intent = new Intent(context, ListNotesActivity.class);
        intent.putExtra(StartActivity.USER_ID, idUser);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        idUser = getIntent().getLongExtra(StartActivity.USER_ID, 0);

        dbHelper = new NotePadesDBHelper(this);
        notesDAO = new NotesDAO(dbHelper);

        recyclerView = (RecyclerView)findViewById(R.id.notes_recycler_view);

        LinearLayoutManager llr = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llr);
        recyclerView.setHasFixedSize(true);
        listNames = listNotesPresenter.getListNotesNames(this, idUser);
        RVAdapter adapter = new RVAdapter(listNames, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                posItem = position;
                startActivity(NoteActivity.newIntent(ListNotesActivity.this, idUser, StartActivity.EDIT_TYPE, listNotesPresenter.getIDNote(position) ));
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
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

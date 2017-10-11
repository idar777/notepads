package com.example.aydar.listnotepades;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aydar.listnotepades.data.DataBase;
import com.example.aydar.listnotepades.data.NotePadesDBHelper;

import java.util.ArrayList;

import static com.example.aydar.listnotepades.StartActivity.USER_ID;

public class ListNotesActivity extends AppCompatActivity {

    private NotePadesDBHelper dbHelper;

    private ArrayList<String> listNotes = new ArrayList();

    private ArrayAdapter adapter;

    private String idUser;

    private Integer posItem;
    private ListView listViewNotes2;


    public static final Intent newIntent(Context context, String idUser) {
        Intent intent = new Intent(context, ListNotesActivity.class);
        intent.putExtra(USER_ID, idUser);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        listViewNotes2 = (ListView) findViewById(R.id.notes_list);

        idUser = getIntent().getStringExtra(USER_ID);

        setNotesContent();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listNotes);

        listViewNotes2.setAdapter(adapter);

        listViewNotes2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                posItem = position;
                startActivity(NoteActivity.newIntent(ListNotesActivity.this, idUser, StartActivity.EDIT_TYPE, getIDNote(posItem)));
            }
        });

    }


    private void setNotesContent() {
        dbHelper = new NotePadesDBHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projectionNotes = {
                DataBase.Notes.COLUMN_NAME
        };
        Cursor cursor = null;
        try {
            cursor = db.query(DataBase.Notes.TABLE_NAME,
                    projectionNotes,
                    "user_id = ?",
                    new String[]{idUser},
                    null,
                    null,
                    null);
            if (cursor.getCount() != 0) {
                int nameIndex = cursor.getColumnIndex(DataBase.Notes.COLUMN_NAME);
                while (cursor.moveToNext()) {
                    listNotes.add(cursor.getString(nameIndex).toString().trim());
                }

            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if(db!=null){
                db.close();
            }
        }
    }

    private String getIDNote(Integer position) {
        dbHelper = new NotePadesDBHelper(this);

        String idNote = "-1";

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projectionNotes = {
                DataBase.Notes.COLUMN_NAME,
                DataBase.Notes._ID
        };

        Cursor cursor = db.query(DataBase.Notes.TABLE_NAME,
                projectionNotes,
                "user_id = ?",
                new String[]{idUser},
                null,
                null,
                null);

        if (cursor.getCount() != 0) {
            try {
                cursor.move(position + 1);
                int idIndex = cursor.getColumnIndex(DataBase.Notes._ID);
                idNote = cursor.getString(idIndex);
            } finally {
                cursor.close();
                db.close();
            }
        }
        return idNote;
    }

    public void addNewNote(View view) {
        startActivity(NoteActivity.newIntent(ListNotesActivity.this,idUser, StartActivity.NEW_TYPE));
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

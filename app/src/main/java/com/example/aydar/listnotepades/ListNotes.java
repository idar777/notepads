package com.example.aydar.listnotepades;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aydar.listnotepades.Data.DataBase;
import com.example.aydar.listnotepades.Data.NotePadesDBHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class ListNotes extends AppCompatActivity {

    private NotePadesDBHelper mDBHelper;

    ArrayList<String> listNotes = new ArrayList();

    private ArrayAdapter mAdapter;

    String idUser = new String();
    Integer posItem;
    //Boolean mRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        ListView listViewNotes2 = (ListView)findViewById(R.id.listNotes);

        idUser = getIntent().getStringExtra("id_user");

        setNotesContent();

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listNotes);

        listViewNotes2.setAdapter(mAdapter);

        listViewNotes2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                posItem = position;
                Intent intent = new Intent(ListNotes.this, Note.class);
                intent.putExtra("id_user", idUser);
                intent.putExtra("type", "edit");
                intent.putExtra("id_note", getIDNote(posItem));
                startActivity(intent);
            }
        });

    }


    private void setNotesContent() {
        mDBHelper = new NotePadesDBHelper(this);

        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        String[] projectionNotes = {
                DataBase.Notes.COLUMN_NAME
        };

        Cursor cursor = db.query(DataBase.Notes.TABLE_NAME,
                projectionNotes,
                "user_id = ?",
                new String[] {idUser},
                null,
                null,
                null);
        if (cursor.getCount() != 0) {
                try {
                    int nameIndex = cursor.getColumnIndex(DataBase.Notes.COLUMN_NAME);
                    while (cursor.moveToNext()) {
                        listNotes.add(cursor.getString(nameIndex).toString().trim());
                    }
                } finally {
                    cursor.close();
                    db.close();
                }
        }
    }

    private String getIDNote(Integer position) {
        mDBHelper = new NotePadesDBHelper(this);

        String idNote = "-1";

        SQLiteDatabase db = mDBHelper.getReadableDatabase();

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
                cursor.move(position+1);
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
        Intent intent = new Intent(ListNotes.this, Note.class);
        intent.putExtra("id_user", idUser);
        intent.putExtra("type", "new");
        startActivity(intent);
    }
}

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

public class ListNotes extends AppCompatActivity {

    private NotePadesDBHelper mDBHelper;

    ArrayList<String> listNotes = new ArrayList();

    private ArrayAdapter mAdapter;

    String idUser = new String();
    Integer tmp = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        ListView listViewNotes2 = (ListView)findViewById(R.id.listNotes);

        idUser = getIntent().getStringExtra("id_user");

        Toast.makeText(this, idUser , Toast.LENGTH_SHORT).show();
        setNotesContent();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listNotes);

        listViewNotes2.setAdapter(mAdapter);

        listViewNotes2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                tmp = position;
                Intent intent5 = new Intent(ListNotes.this, Note.class);
                intent5.putExtra("id_user", idUser);
                intent5.putExtra("type", "edit");
                intent5.putExtra("id_note", tmp.toString());
                startActivity(intent5);
            }
        });

    }


    private void setNotesContent() {
        mDBHelper = new NotePadesDBHelper(this);

        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        String[] projectionNotes = {
                DataBase.Notepads.COLUMN_NAME
        };

        Cursor cursor3 = db.query(DataBase.Notepads.TABLE_NAME,
                projectionNotes,
                "user_id = ?",
                new String[] {idUser},
                null,
                null,
                null);
        if (cursor3.getCount() != 0) {
                try {
                    int nameIndex = cursor3.getColumnIndex(DataBase.Notepads.COLUMN_NAME);
                    while (cursor3.moveToNext()) {
                        listNotes.add(cursor3.getString(nameIndex).toString().trim());
                    }
                } finally {
                    cursor3.close();
                }
        }
    }

    public void addNewNote(View view) {
        Intent intent4 = new Intent(ListNotes.this, Note.class);
        intent4.putExtra("id_user", idUser);
        intent4.putExtra("type", "new");
        startActivity(intent4);
    }
}

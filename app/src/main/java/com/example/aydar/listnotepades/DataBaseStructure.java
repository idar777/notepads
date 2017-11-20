package com.example.aydar.listnotepades;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.dao.Notes;
import com.example.aydar.listnotepades.data.dao.Users;

import java.util.ArrayList;

public class DataBaseStructure extends AppCompatActivity {

    private NotePadesDBHelper dbHelper;
    private ArrayList<String> listUsers = new ArrayList<>();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//    private GoogleApiClient client;
    public ArrayAdapter adapter;

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, DataBaseStructure.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_structure);
        ListView mListView = (ListView)findViewById(R.id.database_list);
        databaseView();


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listUsers);

        mListView.setAdapter(adapter);
    }

    private void databaseView() {


        dbHelper = new NotePadesDBHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        listUsers.add("users --------------------------");

        String[] projectionUsers = {
                Users._ID,
                Users.COLUMN_LOGIN,
                Users.COLUMN_PASSWORD
        };

        Cursor cursor = db.query(Users.TABLE_NAME,
                projectionUsers,
                null,
                null,
                null,
                null,
                null);

        try {
            int loginColumnIndex = cursor.getColumnIndex(Users.COLUMN_LOGIN);
            int passwordColumnIndex = cursor.getColumnIndex(Users.COLUMN_PASSWORD);
            int idColumnIndex = cursor.getColumnIndex(Users._ID);

            while (cursor.moveToNext()) {
                String currentLogin = cursor.getString(loginColumnIndex);
                String currentPassword = cursor.getString(passwordColumnIndex);
                Integer currentID = cursor.getInt(idColumnIndex);
                listUsers.add(currentID.toString() + " " + currentLogin + "  " + currentPassword);
            }
        } finally {
            cursor.close();
        }

        listUsers.add("notes --------------------------");

        String[] projectionUsers3 = {
                Notes._ID,
                Notes.USER_ID,
                Notes.NOTE_ID,
                Notes.COLUMN_TEXT,
                Notes.COLUMN_NAME
        };

        Cursor cursor3 = db.query(Notes.TABLE_NAME,
                projectionUsers3,
                null,
                null,
                null,
                null,
                null);

        try {
            int loginColumnIndex = cursor3.getColumnIndex(Notes.USER_ID);
            int passwordColumnIndex = cursor3.getColumnIndex(Notes.NOTE_ID);
            int nameColumnIndex = cursor3.getColumnIndex(Notes.COLUMN_NAME);
            int textColumnIndex = cursor3.getColumnIndex(Notes.COLUMN_TEXT);
            int idColumnIndex = cursor3.getColumnIndex(Notes._ID);

            while (cursor3.moveToNext()) {
                Integer currentLogin = cursor3.getInt(loginColumnIndex);
                Integer currentPassword = cursor3.getInt(passwordColumnIndex);
                Integer currentID = cursor3.getInt(idColumnIndex);
                String currentText = cursor3.getString(textColumnIndex);
                String currentName = cursor3.getString(nameColumnIndex);
                listUsers.add(currentID.toString() + " " + currentLogin.toString() + "  " + currentPassword.toString() + " " + currentName.toString() + " " + currentText.toString());
            }
        } finally {
            cursor3.close();
        }
    }
}

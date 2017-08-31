package com.example.aydar.listnotepades;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ListActivity;

import com.example.aydar.listnotepades.Data.DataBase;
import com.example.aydar.listnotepades.Data.NotePadesDBHelper;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class DataBaseStructure extends AppCompatActivity {

    private NotePadesDBHelper mDBHelper;
    ArrayList<String> listUsers = new ArrayList<>();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_structure);
        ListView mListView = (ListView)findViewById(R.id.list1);
        databaseView();


        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listUsers);

        mListView.setAdapter(mAdapter);
    }

    private void databaseView() {


        mDBHelper = new NotePadesDBHelper(this);

        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        listUsers.add("users --------------------------");

        String[] projectionUsers = {
                DataBase.Users._ID,
                DataBase.Users.COLUMN_LOGIN,
                DataBase.Users.COLUMN_PASSWORD
        };

        Cursor cursor = db.query(DataBase.Users.TABLE_NAME,
                projectionUsers,
                null,
                null,
                null,
                null,
                null);

        try {
            int loginColumnIndex = cursor.getColumnIndex(DataBase.Users.COLUMN_LOGIN);
            int passwordColumnIndex = cursor.getColumnIndex(DataBase.Users.COLUMN_PASSWORD);
            int idColumnIndex = cursor.getColumnIndex(DataBase.Users._ID);

            while (cursor.moveToNext()) {
                String currentLogin = cursor.getString(loginColumnIndex);
                String currentPassword = cursor.getString(passwordColumnIndex);
                Integer currentID = cursor.getInt(idColumnIndex);
                listUsers.add(currentID.toString() + " " + currentLogin + "  " + currentPassword);
            }
        } finally {
            cursor.close();
        }

//        listUsers.add("notepads --------------------------");
//
//        String[] projectionUsers2 = {
//                DataBase.Notepads._ID,
//                DataBase.Notepads.USER_ID,
//                DataBase.Notepads.COLUMN_NAME
//        };
//
//        Cursor cursor2 = db.query(DataBase.Notepads.TABLE_NAME,
//                projectionUsers2,
//                null,
//                null,
//                null,
//                null,
//                null);
//
//        try {
//            int loginColumnIndex = cursor2.getColumnIndex(DataBase.Notepads.USER_ID);
//            int passwordColumnIndex = cursor2.getColumnIndex(DataBase.Notepads.COLUMN_NAME);
//            int idColumnIndex = cursor2.getColumnIndex(DataBase.Notepads._ID);
//
//            while (cursor2.moveToNext()) {
//                Integer currentLogin = cursor2.getInt(loginColumnIndex);
//                String currentPassword = cursor2.getString(passwordColumnIndex);
//                Integer currentID = cursor2.getInt(idColumnIndex);
//                listUsers.add(currentID.toString() + " " + currentLogin.toString() + "  " + currentPassword);
//            }
//        } finally {
//            cursor2.close();
//        }

        listUsers.add("notes --------------------------");

        String[] projectionUsers3 = {
                DataBase.Notes._ID,
                DataBase.Notes.USER_ID,
                DataBase.Notes.NOTE_ID,
                DataBase.Notes.COLUMN_TEXT,
                DataBase.Notes.COLUMN_NAME
        };

        Cursor cursor3 = db.query(DataBase.Notes.TABLE_NAME,
                projectionUsers3,
                null,
                null,
                null,
                null,
                null);

        try {
            int loginColumnIndex = cursor3.getColumnIndex(DataBase.Notes.USER_ID);
            int passwordColumnIndex = cursor3.getColumnIndex(DataBase.Notes.NOTE_ID);
            int nameColumnIndex = cursor3.getColumnIndex(DataBase.Notes.COLUMN_NAME);
            int textColumnIndex = cursor3.getColumnIndex(DataBase.Notes.COLUMN_TEXT);
            int idColumnIndex = cursor3.getColumnIndex(DataBase.Notes._ID);

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

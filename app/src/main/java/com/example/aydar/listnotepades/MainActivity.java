package com.example.aydar.listnotepades;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aydar.listnotepades.Data.DataBase;
import com.example.aydar.listnotepades.Data.NotePadesDBHelper;

public class MainActivity extends AppCompatActivity {

    private NotePadesDBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new NotePadesDBHelper(this);
    }

    public void RegistrationClick(View view) {
        Intent intent = new Intent(MainActivity.this, Registration.class);
        startActivity(intent);
    }

    public void LookToDatabaseClick(View view) {
        Intent intent2 = new Intent(MainActivity.this, DataBaseStructure.class);
        startActivity(intent2);
    }

    public boolean checkLogin(String mLogin){
        if (mLogin != "1"){
            return "ok1111";
        } else {
            return "nonono";
        }
    }

    public void entrance(View view) {
        EditText mLogin = (EditText)findViewById(R.id.editText2);
        EditText mPassword = (EditText)findViewById(R.id.editText3);

        String idUser = new String();

        //String login2 = new String(mLogin.getText().toString())

        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        String mQuery = "SELECT * FROM " + DataBase.Users.TABLE_NAME + " WHERE "
                + DataBase.Users.COLUMN_LOGIN + " = " + mLogin.getText() + " AND "
                + DataBase.Users.COLUMN_PASSWORD + " = " + mPassword.getText();

        Cursor cursor2 = db.rawQuery(mQuery, null);

        if (!(mLogin.getText().toString().isEmpty())) {
            if (checkLogin(mLogin.getText().toString())) {
                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
            }
        }



        if (cursor2.getCount() == 1) {
            //Toast.makeText(this, "все ок!", Toast.LENGTH_SHORT).show();
            try {
                int idUserIndex = cursor2.getColumnIndex(DataBase.Users._ID);
                while (cursor2.moveToNext()) {
                    idUser = cursor2.getString(idUserIndex);
                }
            } finally {
                cursor2.close();
            }

            Intent intent3 = new Intent(MainActivity.this, ListNotes.class);
            intent3.putExtra("test_user", "testtest");
            intent3.putExtra("id_user", idUser);
            startActivity(intent3);
        } else {
            Toast.makeText(this, "Доступ запрещен", Toast.LENGTH_SHORT).show();
        }

    }
}

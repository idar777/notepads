package com.example.aydar.listnotepades;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aydar.listnotepades.Data.DataBase;
import com.example.aydar.listnotepades.Data.NotePadesDBHelper;
import com.example.aydar.listnotepades.Data.Users;
import com.example.aydar.listnotepades.R;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void AddUserClick(View view) {

        EditText mLogin = (EditText)findViewById(R.id.editText2);
        EditText mPassword = (EditText)findViewById(R.id.editText3);

        String mLoginData = mLogin.getText().toString();
        Users usersWork = new Users();

        if (usersWork.checkUserName(mLoginData)) {
            if (usersWork.checkUserExists(mLoginData)) {
                Toast.makeText(this, "Пользователь с данным e-mail уже существует", Toast.LENGTH_SHORT).show();
            } else {
                NotePadesDBHelper mDBHelper = new NotePadesDBHelper(this);

                SQLiteDatabase db = mDBHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(DataBase.Users.COLUMN_LOGIN, mLogin.getText().toString().trim());
                values.put(DataBase.Users.COLUMN_PASSWORD, mPassword.getText().toString().trim());

                long newRowId = db.insert(DataBase.Users.TABLE_NAME, null, values);

                db.close();

                Intent intent = new Intent(Registration.this, ListNotes.class);
                intent.putExtra("id_user", newRowId);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Логин должен иметь формат e-mail: *@*.*", Toast.LENGTH_SHORT).show();
        }
    }
}

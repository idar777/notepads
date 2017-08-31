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
import com.example.aydar.listnotepades.Data.Users;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void RegistrationClick(View view) {
        Intent intent = new Intent(MainActivity.this, Registration.class);
        startActivity(intent);
    }

    public void LookToDatabaseClick(View view) {
        Intent intent = new Intent(MainActivity.this, DataBaseStructure.class);
        startActivity(intent);
    }

    public void entrance(View view) {
        EditText mLogin = (EditText)findViewById(R.id.editText2);
        EditText mPassword = (EditText)findViewById(R.id.editText3);

        NotePadesDBHelper mDBHelper = new NotePadesDBHelper(this);

        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        Users usersWork = new Users();

        if (usersWork.checkUserName(mLogin.getText().toString())) {
            Integer idUser = usersWork.checkUser(this, mLogin.getText().toString(), mPassword.getText().toString());
            if (idUser.equals(0)){
                Toast.makeText(this, "Доступ запрещен", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, ListNotes.class);
                intent.putExtra("test_user", "testtest");
                intent.putExtra("id_user", idUser.toString());
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Логин должен иметь формат e-mail: *@*.*", Toast.LENGTH_SHORT).show();
        }
    }
}

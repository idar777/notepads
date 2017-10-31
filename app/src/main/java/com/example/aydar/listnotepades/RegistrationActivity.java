package com.example.aydar.listnotepades;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aydar.listnotepades.data.DataBase;
import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.Users;
import com.example.aydar.listnotepades.data.Utils;

import java.security.NoSuchAlgorithmException;

public class RegistrationActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        login = (EditText)findViewById(R.id.login_edit_text);
        password = (EditText)findViewById(R.id.password_edit_text);
    }

    public void addUserClick(View view) throws NoSuchAlgorithmException {
        String loginData = login.getText().toString();
        String passwordData = password.getText().toString();
        if (Utils.checkUserName(login.getText().toString()) & !(password.toString().isEmpty())) {
            if (Users.checkUserExists(this, loginData)) {
                Toast.makeText(this, R.string.error_login_exists, Toast.LENGTH_SHORT).show();
            } else {
                startActivity(ListNotesActivity.newIntent(RegistrationActivity.this,String.valueOf(Users.insertUser(this, loginData, passwordData))));
            }
        } else {
            Toast.makeText(this, R.string.error_incorrect_format_login, Toast.LENGTH_SHORT).show();
        }
    }
}

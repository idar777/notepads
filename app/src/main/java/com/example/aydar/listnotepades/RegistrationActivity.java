package com.example.aydar.listnotepades;

import android.app.Activity;
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

import java.security.NoSuchAlgorithmException;

public class RegistrationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void addUserClick(View view) throws NoSuchAlgorithmException {

        EditText mLogin = (EditText)findViewById(R.id.editTextLogin2);
        EditText mPassword = (EditText)findViewById(R.id.editTextPassword2);

        String mLoginData = mLogin.getText().toString();
        Users usersWork = new Users();

        if (usersWork.checkUserName(mLogin.getText().toString()) & !(mPassword.toString().isEmpty())) {
            if (usersWork.checkUserExists(this, Users.changeToMD5(mLoginData))) {
                Toast.makeText(this, R.string.error_login_exists, Toast.LENGTH_SHORT).show();
            } else {
                NotePadesDBHelper mDBHelper = new NotePadesDBHelper(this);

                SQLiteDatabase db = mDBHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(DataBase.Users.COLUMN_LOGIN, Users.changeToMD5(mLogin.getText().toString().trim()));
                values.put(DataBase.Users.COLUMN_PASSWORD, Users.changeToMD5(mPassword.getText().toString().trim()));

                long newRowId = db.insert(DataBase.Users.TABLE_NAME, null, values);

                db.close();

                startActivity(StartActivity.newIntent(RegistrationActivity.this,String.valueOf(newRowId)));
            }
        } else {
            Toast.makeText(this, R.string.error_incorrect_format_login, Toast.LENGTH_SHORT).show();
        }
    }
}

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
        Users usersWork = new Users();

        if (Utils.checkUserName(login.getText().toString()) & !(password.toString().isEmpty())) {
            if (usersWork.checkUserExists(this, Utils.changeToMD5(loginData))) {
                Toast.makeText(this, R.string.error_login_exists, Toast.LENGTH_SHORT).show();
            } else {
                NotePadesDBHelper dbHelper = new NotePadesDBHelper(this);

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(DataBase.Users.COLUMN_LOGIN, Utils.changeToMD5(login.getText().toString().trim()));
                values.put(DataBase.Users.COLUMN_PASSWORD, Utils.changeToMD5(password.getText().toString().trim()));

                long newRowId = db.insert(DataBase.Users.TABLE_NAME, null, values);

                db.close();

                startActivity(ListNotesActivity.newIntent(RegistrationActivity.this,String.valueOf(newRowId)));
            }
        } else {
            Toast.makeText(this, R.string.error_incorrect_format_login, Toast.LENGTH_SHORT).show();
        }
    }
}

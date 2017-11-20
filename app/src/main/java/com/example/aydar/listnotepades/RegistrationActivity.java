package com.example.aydar.listnotepades;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.dao.Users;
import com.example.aydar.listnotepades.data.Utils;
import com.example.aydar.listnotepades.data.dao.UsersDAO;
import com.example.aydar.listnotepades.data.dto.User;

import java.security.NoSuchAlgorithmException;

public class RegistrationActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;
    User userData;

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
        NotePadesDBHelper dbHelper = new NotePadesDBHelper(this);
        UsersDAO usersDAO = new UsersDAO(dbHelper);

        userData = new User(login.getText().toString(), password.getText().toString());

        if (Utils.checkUserName(userData.getLogin()) & !(userData.getPassword().isEmpty())) {
            if (usersDAO.checkUser(userData, false)!=0) {
                Toast.makeText(this, R.string.error_login_exists, Toast.LENGTH_SHORT).show();
            } else {
                startActivity(ListNotesActivity.newIntent(RegistrationActivity.this, usersDAO.insert(userData)));
            }
        } else {
            Toast.makeText(this, R.string.error_incorrect_format_login, Toast.LENGTH_SHORT).show();
        }
    }
}

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

public class RegistrationActivity extends AppCompatActivity implements IRegistrationView {

    private EditText loginEditText;
    private EditText passwordEditText;
    User userData;

    private RegistrationPresenter registrationPresenter = new RegistrationPresenter();

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loginEditText = (EditText)findViewById(R.id.login_edit_text);
        passwordEditText = (EditText)findViewById(R.id.password_edit_text);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registrationPresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        registrationPresenter.detachView(this);
    }

    public void addUserClick(View view) {
        userData = new User(loginEditText.getText().toString(), passwordEditText.getText().toString());
        registrationPresenter.addUserClick(this, userData);
    }

    public void enterToNoteList(long idUser){
        startActivity(ListNotesActivity.newIntent(RegistrationActivity.this, idUser));
    }

    @Override
    public void showError(int resID) {
        Toast.makeText(this, getResources().getText(resID), Toast.LENGTH_SHORT).show();
    }
}

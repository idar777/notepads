package com.example.aydar.listnotepades.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aydar.listnotepades.R;
import com.example.aydar.listnotepades.data.db.dto.User;
import com.example.aydar.listnotepades.presentation.presenters.RegistrationPresenter;

public class RegistrationActivity extends AppCompatActivity implements IRegistrationView {

    private EditText loginEditText;
    private EditText passwordEditText;
    private User userData;
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

    @Override
    public void addUserClick(View view) {
        userData = new User(loginEditText.getText().toString(), passwordEditText.getText().toString());
        registrationPresenter.addUserClick(this, userData);
    }

    @Override
    public void enterToNoteList(long idUser){
        startActivity(ListNotesActivity.newIntent(RegistrationActivity.this, idUser));
    }

    @Override
    public void showError(int resID) {
        Toast.makeText(this, getResources().getText(resID), Toast.LENGTH_SHORT).show();
    }
}

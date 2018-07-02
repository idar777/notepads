package com.example.aydar.listnotepades.presentation.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.aydar.listnotepades.R;
import com.example.aydar.listnotepades.data.db.dto.User;
import com.example.aydar.listnotepades.presentation.presenters.StartPresenter;

import io.fabric.sdk.android.Fabric;

public class StartActivity extends AppCompatActivity implements IStartView{
    public static final String USER_ID = "user_id";
    public static final String NOTE_ID = "id_note";
    public static final String OPEN_TYPE = "type";
    public static final String EDIT_TYPE = "edit";
    public static final String NEW_TYPE = "new";

    private EditText loginEditText;
    private EditText passwordEditText;
    private StartPresenter startPresenter;
    private User userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        startPresenter = new StartPresenter();
        loginEditText = (EditText)findViewById(R.id.login_edit_text);
        passwordEditText = (EditText)findViewById(R.id.password_edit_text);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startPresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        startPresenter.detachView(this);
    }

    @Override
    public void registrationClick(View view) {
        startActivity(RegistrationActivity.newIntent(StartActivity.this));
    }

    @Override
    public void enterToNoteList(long idUser){
        startActivity(ListNotesActivity.newIntent(StartActivity.this, idUser));
    }

    @Override
    public void enterOnClink(View view) {
        userData = new User(loginEditText.getText().toString(), passwordEditText.getText().toString());
        startPresenter.enterOnClick(this, userData);
    }

    @Override
    public void showError(int resID) {
        Toast.makeText(this, getResources().getText(resID), Toast.LENGTH_SHORT).show();
    }

}

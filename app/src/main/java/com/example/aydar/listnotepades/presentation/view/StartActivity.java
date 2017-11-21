package com.example.aydar.listnotepades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.aydar.listnotepades.data.dto.User;

import io.fabric.sdk.android.Fabric;

public class StartActivity extends AppCompatActivity implements IStartView{

    public static final String USER_ID = "user_id";
    public static final String NOTE_ID = "id_note";
    public static final String OPEN_TYPE = "type";
    public static final String EDIT_TYPE = "edit";
    public static final String NEW_TYPE = "new";

    private EditText loginEditText;
    private EditText passwordEditText;
    private IStartPresenter startPresenter;
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

    public void registrationClick(View view) {
        startActivity(RegistrationActivity.newIntent(StartActivity.this));
    }

    public void enterToNoteList(long idUser){
        startActivity(ListNotesActivity.newIntent(StartActivity.this, idUser));
    }

    public void enterOnClink(View view) {
        userData = new User(loginEditText.getText().toString(), passwordEditText.getText().toString());
        startPresenter.enterOnClick(this, userData);
    }

    public void showError(int resID) {
        Toast.makeText(this, getResources().getText(resID), Toast.LENGTH_SHORT).show();
    }

}

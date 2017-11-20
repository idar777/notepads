package com.example.aydar.listnotepades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.Utils;
import com.example.aydar.listnotepades.data.dao.UsersDAO;
import com.example.aydar.listnotepades.data.dto.User;

import io.fabric.sdk.android.Fabric;

import java.security.NoSuchAlgorithmException;

public class StartActivity extends AppCompatActivity {

    public static final String USER_ID = "user_id";
    public static final String NOTE_ID = "id_note";
    public static final String OPEN_TYPE = "type";
    public static final String EDIT_TYPE = "edit";
    public static final String NEW_TYPE = "new";

    private EditText loginEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        loginEditText = (EditText)findViewById(R.id.login_edit_text);
        passwordEditText = (EditText)findViewById(R.id.password_edit_text);
    }

    public void registrationClick(View view) {
        startActivity(RegistrationActivity.newIntent(StartActivity.this));
    }

    public void lookToDatabaseClick(View view) {
        startActivity(DataBaseStructure.newIntent(StartActivity.this));
    }

    public void entrance(View view) throws NoSuchAlgorithmException {
        NotePadesDBHelper dbHelper = new NotePadesDBHelper(this);
        UsersDAO usersDAO = new UsersDAO(dbHelper);
        User user = new User(loginEditText.getText().toString(),passwordEditText.getText().toString() );

        if (Utils.checkUserName(user.getLogin()) & !(user.getPassword().isEmpty())) {
            Integer idUser = usersDAO.checkUser(user, true);
            if (idUser.equals(0)){
                Toast.makeText(this, R.string.access_is_denied, Toast.LENGTH_SHORT).show();
            } else {
                startActivity(ListNotesActivity.newIntent(StartActivity.this, idUser));
            }
        } else {
            Toast.makeText(this, R.string.error_incorrect_format_login, Toast.LENGTH_SHORT).show();
        }
    }
}

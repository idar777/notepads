package com.example.aydar.listnotepades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.aydar.listnotepades.Data.DataBase;
import com.example.aydar.listnotepades.Data.NotePadesDBHelper;
import com.example.aydar.listnotepades.Data.Users;

import static android.R.attr.data;
import io.fabric.sdk.android.Fabric;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StartActivity extends Activity {

    public static final String USER_ID = "user_id";
    public static final String NOTE_ID = "id_note";
    public static final String OPEN_TYPE = "type";
    public static final String EDIT_TYPE = "edit";
    public static final String NEW_TYPE = "new";


    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        return intent;
    }

    public static final Intent newIntent(Context context, String idUser) {
        Intent intent = new Intent(context, ListNotesActivity.class);
        intent.putExtra(USER_ID, idUser);
        return intent;
    }

    public static final Intent newIntentDatabase(Context context) {
        Intent intent = new Intent(context, DataBaseStructure.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
    }

    public void registrationClick(View view) {
        startActivity(newIntent(StartActivity.this));
    }

    public void lookToDatabaseClick(View view) {
        startActivity(newIntentDatabase(StartActivity.this));
    }

    public void entrance(View view) throws NoSuchAlgorithmException {
        EditText mLogin = (EditText)findViewById(R.id.editTextLogin);
        EditText mPassword = (EditText)findViewById(R.id.editTextPassword);

        Users usersWork = new Users();

        if (usersWork.checkUserName(mLogin.getText().toString()) & !(mPassword.toString().isEmpty())) {
            Integer idUser = usersWork.checkUser(this, Users.changeToMD5(mLogin.getText().toString()), Users.changeToMD5(mPassword.getText().toString()));
            if (idUser.equals(0)){
                Toast.makeText(this, R.string.access_is_denied, Toast.LENGTH_SHORT).show();
            } else {
                startActivity(newIntent(StartActivity.this, idUser.toString()));
            }
        } else {
            Toast.makeText(this, R.string.error_incorrect_format_login, Toast.LENGTH_SHORT).show();
        }
    }
}

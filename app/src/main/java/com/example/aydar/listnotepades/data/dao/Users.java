package com.example.aydar.listnotepades.data;

import android.content.Context;
import android.provider.BaseColumns;

import java.security.NoSuchAlgorithmException;


/**
 * Created by aydar on 29.08.17.
 */

public class Users {
    public final static String TABLE_NAME = "users";
    public final static String _ID = BaseColumns._ID;
    public final static String COLUMN_LOGIN = "login";
    public final static String COLUMN_PASSWORD = "password";

    public static boolean checkUserExists(Context context, String login) throws NoSuchAlgorithmException { //Проверка на существование при регистрации
        return NotePadesDBHelper.checkUserExistsDB(context, login);
    };

    public static Integer checkUser(Context context, String login, String password) throws NoSuchAlgorithmException  { //Проверка доступа при входе
        return NotePadesDBHelper.checkUserDB(context,login,password);
    };

    public static long insertUser(Context context, String login, String password) throws NoSuchAlgorithmException{
        return NotePadesDBHelper.insertUserDB(context, login, password);
    }

}

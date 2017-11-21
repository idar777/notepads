package com.example.aydar.listnotepades;

import android.content.Context;

import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.dao.UsersDAO;
import com.example.aydar.listnotepades.data.dto.User;

import java.security.NoSuchAlgorithmException;

/**
 * Created by aydar on 17.11.17.
 */

public class AddUserInteractor {
    UsersDAO usersDAO;

    public long addUserInteractor(Context context, User userData) {
        NotePadesDBHelper dbHelper = new NotePadesDBHelper(context);
        usersDAO = new UsersDAO(dbHelper);
        return usersDAO.insert(userData);
    }
}

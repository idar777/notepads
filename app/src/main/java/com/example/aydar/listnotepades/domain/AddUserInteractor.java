package com.example.aydar.listnotepades.domain;

import android.content.Context;

import com.example.aydar.listnotepades.data.db.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.db.dao.UsersDAO;
import com.example.aydar.listnotepades.data.db.dto.User;

/**
 * Created by aydar on 17.11.17.
 */

public class AddUserInteractor {
    private UsersDAO usersDAO;
    private NotePadesDBHelper dbHelper;

    public long addUserInteractor(Context context, User userData) {
        dbHelper = new NotePadesDBHelper(context);
        usersDAO = new UsersDAO(dbHelper);
        return usersDAO.insert(userData);
    }
}

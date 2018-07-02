package com.example.aydar.listnotepades.domain;

import android.content.Context;

import com.example.aydar.listnotepades.data.db.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.db.dao.UsersDAO;
import com.example.aydar.listnotepades.data.db.dto.User;

/**
 * Created by aydar on 20.11.17.
 */

public class CheckLoginInteractor {
    public long checkLoginInteractor(Context context, User userData) {
        NotePadesDBHelper dbHelper = new NotePadesDBHelper(context);
        UsersDAO usersDAO = new UsersDAO(dbHelper);
        return usersDAO.checkUser(userData, false);
    }
}

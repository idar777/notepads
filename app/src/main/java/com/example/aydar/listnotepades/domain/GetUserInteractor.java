package com.example.aydar.listnotepades;

import android.content.Context;

import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.dao.UsersDAO;
import com.example.aydar.listnotepades.data.dto.User;
/**
 * Created by aydar on 17.11.17.
 */

public class GetUserInteractor {
    public long getUserByLogin(Context context, User userData) {
        NotePadesDBHelper dbHelper = new NotePadesDBHelper(context);
        UsersDAO usersDAO = new UsersDAO(dbHelper);

        return usersDAO.checkUser(userData, true);
    }
}

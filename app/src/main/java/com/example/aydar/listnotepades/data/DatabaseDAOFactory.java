package com.example.aydar.listnotepades.data;

import com.example.aydar.listnotepades.data.dao.Idao;
import com.example.aydar.listnotepades.data.dao.NotesDAO;
import com.example.aydar.listnotepades.data.dao.UsersDAO;

/**
 * Created by aydar on 08.11.17.
 */

public class DatabaseDAOFactory {
    NotePadesDBHelper dbHelper;

    public DatabaseDAOFactory(NotePadesDBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public Idao getDaoInstance(Class classDto) {
        if (classDto == UsersDAO.class) {
            return new UsersDAO(dbHelper);
        } else if (classDto == NotesDAO.class) {
            return new NotesDAO(dbHelper);
        } else {
            return null;
        }
    }
}

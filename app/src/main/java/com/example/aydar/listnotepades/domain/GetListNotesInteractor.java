package com.example.aydar.listnotepades.domain;

import android.content.Context;

import com.example.aydar.listnotepades.data.db.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.db.dao.NotesDAO;
import com.example.aydar.listnotepades.data.db.dto.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aydar on 16.11.17.
 */

public class GetListNotesInteractor {
    private List<Note> listNotes = new ArrayList();
    private NotesDAO notesDAO;
    private NotePadesDBHelper dbHelper;

    public List<Note> getListNotesNamesInteractor(Context context, long idUser){
        dbHelper = new NotePadesDBHelper(context);
        notesDAO = new NotesDAO(dbHelper);
        listNotes = notesDAO.getNotesList(idUser);
        return listNotes;
    }
}

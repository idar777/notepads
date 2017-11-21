package com.example.aydar.listnotepades;

import android.content.Context;

import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.dao.NotesDAO;
import com.example.aydar.listnotepades.data.dto.Note;

import java.util.ArrayList;

/**
 * Created by aydar on 16.11.17.
 */

public class GetListNotesInteractor {
    private ArrayList<Note> listNotes = new ArrayList();
    private NotesDAO notesDAO;
    private NotePadesDBHelper dbHelper;

    public ArrayList<Note> getListNotesNamesInteractor(Context context, long idUser){
        dbHelper = new NotePadesDBHelper(context);
        notesDAO = new NotesDAO(dbHelper);
        listNotes = notesDAO.getNotesList(idUser);
        return listNotes;
    }
}

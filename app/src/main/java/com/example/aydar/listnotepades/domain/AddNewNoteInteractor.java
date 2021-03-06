package com.example.aydar.listnotepades.domain;

import android.content.Context;

import com.example.aydar.listnotepades.data.db.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.db.dao.NotesDAO;
import com.example.aydar.listnotepades.data.db.dto.Note;

/**
 * Created by aydar on 20.11.17.
 */

public class AddNewNoteInteractor {
    private NotePadesDBHelper notePadesDBHelper;
    private NotesDAO notesDAO;

    public void saveNote(Context context, Note note){
        notePadesDBHelper = new NotePadesDBHelper(context);
        notesDAO = new NotesDAO(notePadesDBHelper);
        notesDAO.insert(note);
    };
}

package com.example.aydar.listnotepades;

import android.content.Context;

import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.dao.NotesDAO;
import com.example.aydar.listnotepades.data.dto.Note;

/**
 * Created by aydar on 20.11.17.
 */

public class AddNewNoteInteractor {
    NotePadesDBHelper notePadesDBHelper;
    NotesDAO notesDAO;

    public void saveNote(Context context, Note note){
        notePadesDBHelper = new NotePadesDBHelper(context);
        notesDAO = new NotesDAO(notePadesDBHelper);
        long idNote = notesDAO.insert(note);
    };
}

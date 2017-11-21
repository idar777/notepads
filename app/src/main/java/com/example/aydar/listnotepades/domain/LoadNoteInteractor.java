package com.example.aydar.listnotepades.domain;

import android.content.Context;

import com.example.aydar.listnotepades.data.db.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.db.dao.NotesDAO;
import com.example.aydar.listnotepades.data.db.dto.Note;

/**
 * Created by aydar on 20.11.17.
 */

public class LoadNoteInteractor {
    NotePadesDBHelper notePadesDBHelper;
    NotesDAO notesDAO;
    public Note loadNoteInteractor(Context context, long idNote){
        notePadesDBHelper = new NotePadesDBHelper(context);
        notesDAO = new NotesDAO(notePadesDBHelper);
        return notesDAO.getNote(idNote);
    }

}

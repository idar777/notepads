package com.example.aydar.listnotepades;

import android.content.Context;

import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.dao.NotesDAO;
import com.example.aydar.listnotepades.data.dao.UsersDAO;
import com.example.aydar.listnotepades.data.dto.Note;

/**
 * Created by aydar on 20.11.17.
 */

class LoadNoteInteractor {
    NotePadesDBHelper notePadesDBHelper;
    NotesDAO notesDAO;
    public Note loadNoteInteractor(Context context, long idNote){
        notePadesDBHelper = new NotePadesDBHelper(context);
        notesDAO = new NotesDAO(notePadesDBHelper);
        return notesDAO.getNote(idNote);
    }

}

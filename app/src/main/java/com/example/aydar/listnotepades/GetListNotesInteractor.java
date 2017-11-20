package com.example.aydar.listnotepades;

import com.example.aydar.listnotepades.data.dao.NotesDAO;
import com.example.aydar.listnotepades.data.dto.Note;

import java.util.ArrayList;

/**
 * Created by aydar on 16.11.17.
 */

public class ListNotesInteractor {
    private ArrayList<Note> listNotes = new ArrayList();
    private ArrayList<String> listNames = new ArrayList();
    private NotesDAO notesDAO;


    public ArrayList<String> getListNotesNamesInteractor(long idUser){
        listNotes = notesDAO.getNotesList(idUser);
        for (int i = 0; i < listNotes.size(); i++) {
            Note note = listNotes.get(i);
            listNames.add(note.getName());
        }
        return listNames;
    }
}

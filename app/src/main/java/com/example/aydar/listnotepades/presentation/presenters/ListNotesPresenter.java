package com.example.aydar.listnotepades;

import android.content.Context;

import com.example.aydar.listnotepades.data.dto.Note;

import java.util.ArrayList;

/**
 * Created by aydar on 16.11.17.
 */

public class ListNotesPresenter implements IListNotesPresenter {
    GetListNotesInteractor listNotesInteractor = new GetListNotesInteractor();
    private ArrayList<Note> listNotes = new ArrayList();
    private ArrayList<String> listNames = new ArrayList();

    public ArrayList<String> getListNotesNames(Context context, long idUser){
        listNotes = listNotesInteractor.getListNotesNamesInteractor(context, idUser);
        for (int i = 0; i < listNotes.size(); i++) {
            Note note = listNotes.get(i);
            listNames.add(note.getName());
        }
        return listNames;
    }

    public String getIDNote(Integer position) {
        return String.valueOf(listNotes.get(position).getId());
    }
}

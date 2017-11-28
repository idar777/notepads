package com.example.aydar.listnotepades.presentation.presenters;

import android.content.Context;

import com.example.aydar.listnotepades.data.db.dto.Note;
import com.example.aydar.listnotepades.domain.GetListNotesInteractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aydar on 16.11.17.
 */

public class ListNotesPresenter implements IListNotesPresenter {
    private GetListNotesInteractor listNotesInteractor;
    private List<Note> listNotes = new ArrayList();
    private List<String> listNames = new ArrayList();

    @Override
    public List<String> getListNotesNames(Context context, long idUser){
        listNotesInteractor = new GetListNotesInteractor();
        listNotes = listNotesInteractor.getListNotesNamesInteractor(context, idUser);
        for (int i = 0; i < listNotes.size(); i++) {
            Note note = listNotes.get(i);
            listNames.add(note.getName());
        }
        return listNames;
    }

    @Override
    public String getIDNote(Integer position) {
        return String.valueOf(listNotes.get(position).getId());
    }
}

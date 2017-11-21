package com.example.aydar.listnotepades;

import android.content.Context;

import com.example.aydar.listnotepades.data.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.dao.NotesDAO;
import com.example.aydar.listnotepades.data.dto.Note;

/**
 * Created by aydar on 20.11.17.
 */

public class NotePresenter implements INotePresenter {
    LoadNoteInteractor loadNoteInteractor;
    DeleteNoteInteractor deleteNoteInteractor;
    AddNewNoteInteractor addNewNoteInteractor;
    UpdateNoteInteractor updateNoteInteractor;
    INoteView view;
    Note note;


    @Override
    public void attachView(INoteView view) {
        this.view = view;
    }

    @Override
    public void detachView(INoteView view) {
        this.view = null;
    }

    public Note loadNote(Context context, long idNote){
        loadNoteInteractor = new LoadNoteInteractor();
        return loadNoteInteractor.loadNoteInteractor(context, idNote);
    };

    public void updateNote(Context context, Note note, String type){
        updateNoteInteractor = new UpdateNoteInteractor();
        addNewNoteInteractor = new AddNewNoteInteractor();
        if (!note.getName().isEmpty() & !note.getText().isEmpty()){
            if (type.equals(StartActivity.EDIT_TYPE)) {
                updateNoteInteractor.updateNoteInteractor(context, note);
            } else {
                addNewNoteInteractor.saveNote(context, note);
            }
            view.updateListNotes();
        } else {
            view.showError(R.string.error_empty_data);
        }
    }

    public void deleteNote(Context context, long idNote){
        deleteNoteInteractor = new DeleteNoteInteractor();
        note = new Note(idNote);
        deleteNoteInteractor.deleteNoteInteractor(context, note);
    };
}

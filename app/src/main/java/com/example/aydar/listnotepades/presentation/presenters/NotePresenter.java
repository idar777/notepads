package com.example.aydar.listnotepades.presentation.presenters;

import android.content.Context;

import com.example.aydar.listnotepades.R;
import com.example.aydar.listnotepades.data.db.dto.Note;
import com.example.aydar.listnotepades.domain.AddNewNoteInteractor;
import com.example.aydar.listnotepades.domain.DeleteNoteInteractor;
import com.example.aydar.listnotepades.domain.LoadNoteInteractor;
import com.example.aydar.listnotepades.domain.UpdateNoteInteractor;
import com.example.aydar.listnotepades.presentation.view.INoteView;
import com.example.aydar.listnotepades.presentation.view.StartActivity;

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

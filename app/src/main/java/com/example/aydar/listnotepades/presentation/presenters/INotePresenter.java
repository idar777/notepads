package com.example.aydar.listnotepades;

import android.content.Context;

import com.example.aydar.listnotepades.data.dto.Note;

/**
 * Created by aydar on 20.11.17.
 */

public interface INotePresenter {
    void attachView(INoteView view);
    void detachView(INoteView view);
    Note loadNote(Context context, long idNote);
    void updateNote(Context context, Note note, String type);
    void deleteNote(Context context, long idNote);
}

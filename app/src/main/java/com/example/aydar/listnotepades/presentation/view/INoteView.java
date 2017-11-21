package com.example.aydar.listnotepades.presentation.view;

import android.view.View;

/**
 * Created by aydar on 20.11.17.
 */

public interface INoteView {
    void saveContentNoteOnClick(View view);
    void deleteCurrentNoteOnClick(View view);

    void showError(int resID);
    void updateListNotes();
}

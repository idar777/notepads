package com.example.aydar.listnotepades.presentation.view;

import android.view.View;

/**
 * Created by aydar on 17.11.17.
 */

public interface IStartView {
    void registrationClick(View view);
    void enterToNoteList(long idUser);
    void enterOnClink(View view);
    void showError(int resID);
}

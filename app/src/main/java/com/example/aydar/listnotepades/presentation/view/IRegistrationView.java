package com.example.aydar.listnotepades.presentation.view;

import android.view.View;

/**
 * Created by aydar on 20.11.17.
 */

public interface IRegistrationView {
    void addUserClick(View view);
    void showError(int resID);
    void enterToNoteList(long idUser);
}

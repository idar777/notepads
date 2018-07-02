package com.example.aydar.listnotepades.presentation.presenters;

import android.content.Context;

import java.util.List;

/**
 * Created by aydar on 20.11.17.
 */

public interface IListNotesPresenter {
    List<String> getListNotesNames(Context context, long idUser);
    String getIDNote(Integer position);
}

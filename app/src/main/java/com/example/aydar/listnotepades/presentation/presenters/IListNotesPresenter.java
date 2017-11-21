package com.example.aydar.listnotepades;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by aydar on 20.11.17.
 */

public interface IListNotesPresenter {
    ArrayList<String> getListNotesNames(Context context, long idUser);
    String getIDNote(Integer position);
}

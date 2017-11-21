package com.example.aydar.listnotepades.presentation.presenters;

import android.content.Context;

import com.example.aydar.listnotepades.data.db.dto.User;
import com.example.aydar.listnotepades.presentation.view.IStartView;

/**
 * Created by aydar on 17.11.17.
 */

interface IStartPresenter {

    void attachView(IStartView view);
    void detachView(IStartView view);

    boolean checkUserName(User userData);
    long getUserByLogin(Context context, User userData);

    void enterOnClick(Context context, User userData);
}

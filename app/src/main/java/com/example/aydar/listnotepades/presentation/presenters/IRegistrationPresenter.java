package com.example.aydar.listnotepades.presentation.presenters;

import android.content.Context;

import com.example.aydar.listnotepades.data.db.dto.User;
import com.example.aydar.listnotepades.presentation.view.IRegistrationView;

/**
 * Created by aydar on 20.11.17.
 */

public interface IRegistrationPresenter {
    void attachView(IRegistrationView view);

    void detachView(IRegistrationView view);

    long getUserByLogin(Context context, User userData);

    boolean checkUserName(User userData);

    void addUserClick(Context context, User userData);
}

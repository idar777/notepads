package com.example.aydar.listnotepades;

import android.content.Context;

import com.example.aydar.listnotepades.data.dto.User;

/**
 * Created by aydar on 20.11.17.
 */

public interface IRegistrationPresenter {
    void attachView(IRegistrationView view);
    void detachView(IRegistrationView view);
    long getUserByLogin(Context context, User userData);
    boolean checkUserName(User userData);
}

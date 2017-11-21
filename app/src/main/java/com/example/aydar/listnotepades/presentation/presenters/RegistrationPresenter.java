package com.example.aydar.listnotepades;

import android.content.Context;
import android.widget.Toast;

import com.example.aydar.listnotepades.data.dto.User;

/**
 * Created by aydar on 17.11.17.
 */

public class RegistrationPresenter implements IRegistrationPresenter{
    private CheckUserNameInteractor checkUserNameInteractor;
    private CheckLoginInteractor checkLoginInteractor;
    private AddUserInteractor addUserInteractor;
    private IRegistrationView view;

    public long getUserByLogin(Context context, User userData) {
        checkLoginInteractor = new CheckLoginInteractor();
        return checkLoginInteractor.checkLoginInteractor(context, userData);
    }

    public boolean checkUserName(User userData){
        checkUserNameInteractor = new CheckUserNameInteractor();
        return checkUserNameInteractor.checkUserName(userData);
    }

    public void addUserClick(Context context, User userData) {
        addUserInteractor = new AddUserInteractor();
        if (checkUserName(userData) & !(userData.getPassword().isEmpty())) {
            long idUser = getUserByLogin(context, userData);
            if (idUser != 0) {
                view.showError(R.string.error_login_exists);
            } else {
                idUser =  addUserInteractor.addUserInteractor(context, userData);
                view.enterToNoteList(idUser);
            }
        } else {
            view.showError(R.string.error_incorrect_format_login);
        };
    }

    @Override
    public void attachView(IRegistrationView view) {
        this.view = view;
    }

    @Override
    public void detachView(IRegistrationView view) {
        this.view = null;
    }
}

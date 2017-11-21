package com.example.aydar.listnotepades;

import android.content.Context;

import com.example.aydar.listnotepades.data.dto.User;

import java.security.NoSuchAlgorithmException;

/**
 * Created by aydar on 17.11.17.
 */

public class StartPresenter implements IStartPresenter {
    private GetUserInteractor getUserInteractor;
    private CheckUserNameInteractor checkUserNameInteractor;
    private IStartView view;

    public long getUserByLogin(Context context, User userData) {
        getUserInteractor = new GetUserInteractor();
        return getUserInteractor.getUserByLogin(context, userData);
    }

    @Override
    public void attachView(IStartView view) {
        this.view = view;
    }

    @Override
    public void detachView(IStartView view) {
        this.view = null;
    }

    public boolean checkUserName(User userData) {
        checkUserNameInteractor = new CheckUserNameInteractor();
        boolean isValid =  checkUserNameInteractor.checkUserName(userData);
        return isValid;
    }

    public void enterOnClick(Context context, User userData){
        if (checkUserName(userData)) {
            long idUser = getUserByLogin(context, userData);
            if (idUser == 0){
                if(view!=null){
                    view.showError(R.string.access_is_denied);
                }
            } else {
                view.enterToNoteList(idUser);
            }
        } else {
            if(view!=null){
                view.showError(R.string.error_incorrect_format_login);
            }
        }
    }
}

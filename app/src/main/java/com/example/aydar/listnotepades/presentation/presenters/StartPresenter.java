package com.example.aydar.listnotepades.presentation.presenters;

import android.content.Context;

import com.example.aydar.listnotepades.R;
import com.example.aydar.listnotepades.data.db.dto.User;
import com.example.aydar.listnotepades.domain.CheckUserNameInteractor;
import com.example.aydar.listnotepades.domain.CryptInteractor;
import com.example.aydar.listnotepades.domain.GetUserInteractor;
import com.example.aydar.listnotepades.presentation.view.IStartView;

/**
 * Created by aydar on 17.11.17.
 */

public class StartPresenter implements IStartPresenter {
    private GetUserInteractor getUserInteractor;
    private CheckUserNameInteractor checkUserNameInteractor;
    private CryptInteractor cryptInteractor;
    private IStartView view;

    @Override
    public long getUserByLogin(Context context, User userData) {
        getUserInteractor = new GetUserInteractor();
        cryptInteractor = new CryptInteractor();
        userData = cryptInteractor.cryptInteractor(userData);
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

    @Override
    public boolean checkUserName(User userData) {
        checkUserNameInteractor = new CheckUserNameInteractor();
        return checkUserNameInteractor.checkUserName(userData);
    }

    @Override
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

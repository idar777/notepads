package com.example.aydar.listnotepades.domain;

import com.example.aydar.listnotepades.data.db.dto.User;

/**
 * Created by aydar on 17.11.17.
 */

public class CheckUserNameInteractor {
    public boolean checkUserName(User userData){
        return userData.getLogin().matches("\\w+\\@\\w+\\.\\w+");
    }
}

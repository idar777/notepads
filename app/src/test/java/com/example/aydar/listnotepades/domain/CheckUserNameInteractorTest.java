package com.example.aydar.listnotepades.domain;

import com.example.aydar.listnotepades.data.db.dto.User;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aydar on 27.11.17.
 */
public class CheckUserNameInteractorTest {
    @Test
    public void checkUserName() throws Exception {
        CheckUserNameInteractor interactor = new CheckUserNameInteractor();
        boolean result = interactor.checkUserName(mockNegativeUser());
        assertEquals(result, false);

        result = interactor.checkUserName(mockPositiveUser());
        assertEquals(result, true);
    }

    private User mockNegativeUser(){
        User user = new User("login", "password");
        return user;
    }

    private User mockPositiveUser(){
        User user = new User("user@user.com", "password");
        return user;
    }
}
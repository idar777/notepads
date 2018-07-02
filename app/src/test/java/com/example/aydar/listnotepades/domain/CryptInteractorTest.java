package com.example.aydar.listnotepades.domain;

import com.example.aydar.listnotepades.data.db.dto.User;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aydar on 27.11.17.
 */
public class CryptInteractorTest {
    @Test
    public void cryptInteractor() throws Exception {
        CryptInteractor cryptInteractor = new CryptInteractor();
        User user2 = cryptInteractor.cryptInteractor(mockUser());

        assertNotEquals(user2.equals(mockUser()), true);
    }

    private User mockUser(){
        User user = new User("login", "password");
        return user;
    }
}
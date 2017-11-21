package com.example.aydar.listnotepades.domain;

import android.util.Log;

import com.example.aydar.listnotepades.data.db.dto.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by aydar on 20.11.17.
 */

public class CryptInteractor {
    private static final String TAG = "ENRYPT";

    public User cryptInteractor(User user){
        try {
            MessageDigest secure = MessageDigest.getInstance("MD5");

            byte[] md5 = secure.digest(user.getLogin().getBytes());
            user.setLogin(String.format("%032X", new BigInteger(1, md5)));

            md5 = secure.digest(user.getPassword().getBytes());
            user.setPassword(String.format("%032X", new BigInteger(1, md5)));

        } catch (NoSuchAlgorithmException e) {
            Log.d(TAG, e.getMessage());
        }
        return user;
    }

}

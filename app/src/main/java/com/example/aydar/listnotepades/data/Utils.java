package com.example.aydar.listnotepades.data;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by aydar on 11.10.17.
 */

public class Utils {
    public static String changeToMD5(String initial) throws NoSuchAlgorithmException {
        MessageDigest secure = MessageDigest.getInstance("MD5");
        byte[] md5 = secure.digest(initial.getBytes());
        return String.format("%032X", new BigInteger(1, md5));
    }

    public static boolean checkUserName(String mLogin){
        return mLogin.matches("\\w+\\@\\w+\\.\\w+");
    }
}

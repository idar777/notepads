package com.example.aydar.listnotepades;

import com.example.aydar.listnotepades.data.Utils;
import com.example.aydar.listnotepades.data.dto.User;

/**
 * Created by aydar on 17.11.17.
 */

public class CheckUserNameInteractor {
    public boolean checkUserName(User userData){
        Utils utils = new Utils();
        return utils.checkUserName(userData);
    }
}

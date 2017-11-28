package com.example.aydar.listnotepades.data.db.dto;

import java.util.Objects;
//import com.google.common.base.Objects;

/**
 * Created by aydar on 08.11.17.
 */

public class User implements IDto {
    private long id;
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o){
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        User user = (User) o;
//        return login == user.login &&
//                password == user.password;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(login, password);
//    }
//
//    @Override
//    public String toString() {
//        return Objects.toStringHelper(this)
//                .add("login", login)
//                .add("password", password);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!login.equals(user.login)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "login "+login+", password"+password;
    }
}

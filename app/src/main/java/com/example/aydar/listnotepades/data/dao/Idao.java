package com.example.aydar.listnotepades.data.dao;

import com.example.aydar.listnotepades.data.dto.Idto;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by aydar on 08.11.17.
 */

public interface Idao<T extends Idto> {
    void createTable();
    void dropTable();
    List<T> select();
    long insert(T item) throws NoSuchAlgorithmException;
    void update(T item);
    void remove(T item);
    String getNameOfTable();

}

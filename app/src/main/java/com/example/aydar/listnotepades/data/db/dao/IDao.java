package com.example.aydar.listnotepades.data.dao;

import com.example.aydar.listnotepades.data.dto.IDto;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by aydar on 08.11.17.
 */

public interface IDao<T extends IDto> {
    void createTable();
    void dropTable();
    List<T> select();
    long insert(T item) throws NoSuchAlgorithmException;
    void update(T item);
    void remove(T item);
    String getNameOfTable();

}

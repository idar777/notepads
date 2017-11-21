package com.example.aydar.listnotepades.data.db.dao;

import com.example.aydar.listnotepades.data.db.dto.IDto;

import java.util.List;

/**
 * Created by aydar on 08.11.17.
 */

public interface IDao<T extends IDto> {

    void createTable();

    void dropTable();

    List<T> select();

    long insert(T item);

    void update(T item);

    void remove(T item);

    String getNameOfTable();
}

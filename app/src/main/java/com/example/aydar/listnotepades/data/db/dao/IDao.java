package com.example.aydar.listnotepades.data.db.dao;

import android.database.sqlite.SQLiteDatabase;

import com.example.aydar.listnotepades.data.db.NotePadesDBHelper;
import com.example.aydar.listnotepades.data.db.dto.IDto;

import java.util.List;

/**
 * Created by aydar on 08.11.17.
 */

public interface IDao<T extends IDto> {

    void createTable(SQLiteDatabase db);

    void dropTable(SQLiteDatabase db);

    List<T> select();

    long insert(T item);

    void update(T item);

    void remove(T item);

    String getNameOfTable();
}

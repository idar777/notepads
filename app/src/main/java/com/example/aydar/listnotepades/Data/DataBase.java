package com.example.aydar.listnotepades.Data;

import android.provider.BaseColumns;

/**
 * Created by aydar on 22.08.17.
 */

public final class DataBase {
    private DataBase() {
    }

    public static final class Users implements BaseColumns {
        public final  static String TABLE_NAME = "users";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_LOGIN = "login";
        public final static String COLUMN_PASSWORD = "password";
    }

    public static final class Notepads implements BaseColumns {
        public final static String TABLE_NAME = "notepads";

        public final static String _ID = BaseColumns._ID;
        public final static String USER_ID = "user_id";
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_DATE = "date";
    }

    public static final class Notes implements BaseColumns {
        public final static String TABLE_NAME = "notes";

        public final static String _ID = BaseColumns._ID;
        public final static String USER_ID = "user_id";
        public final static String NOTE_ID = "note_id";
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_TEXT = "text";
        public final static String COLUMN_DATE = "date";
    }
}

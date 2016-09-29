package com.bignerdranch.android.personaltrainer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

public class CustomerDbSchema {

    public static final class CustomerTable {
        public static final String NAME = "customers";

        public static final class Cols {
            public static final String NAME = "name";
            public static final String PHONE_NUMBER = "phone number";
            public static final String ADDRESS = "address";
        }
    }
}

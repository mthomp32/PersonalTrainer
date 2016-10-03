package com.bignerdranch.android.personaltrainer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bignerdranch.android.personaltrainer.database.CustomerDbSchema.CustomerTable;

public class CustomerBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "personalTrainer.db";
    public static final String CUSTOMERS_DATABASE_TABLE = "Customers";

    public CustomerBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public static final String DATABASE_CREATE = "create table " + CustomerTable.NAME + "(" +
            CustomerTable.KEY_ID + " integer primary key autoincrement, " +
            CustomerTable.Cols.NAME + ", " +
            CustomerTable.Cols.PHONE_NUMBER + ", " +
            CustomerTable.Cols.ADDRESS +
            ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w("TaskDBAdapter", "Upgrading from version" + oldVersion + " to " +
            newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF IT EXISTS" + CUSTOMERS_DATABASE_TABLE);
        onCreate(db);
    }
}

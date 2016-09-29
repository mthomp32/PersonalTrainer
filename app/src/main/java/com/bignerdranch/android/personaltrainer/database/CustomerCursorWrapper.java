package com.bignerdranch.android.personaltrainer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.personaltrainer.Customer;
import com.bignerdranch.android.personaltrainer.database.CustomerDbSchema.CustomerTable;

import java.util.UUID;

public class CustomerCursorWrapper extends CursorWrapper {

    public CustomerCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Customer getCustomer() {
        String name = getString(getColumnIndex(CustomerTable.Cols.NAME));
        int phoneNumber = getInt(getColumnIndex(CustomerTable.Cols.PHONE_NUMBER));
        String address = getString(getColumnIndex(CustomerTable.Cols.ADDRESS));

        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);

        return customer;
    }
}

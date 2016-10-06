package com.bignerdranch.android.personaltrainer;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bignerdranch.android.personaltrainer.database.CustomerBaseHelper;
import com.bignerdranch.android.personaltrainer.database.CustomerCursorWrapper;
import com.bignerdranch.android.personaltrainer.database.CustomerDbSchema;
import com.bignerdranch.android.personaltrainer.database.CustomerDbSchema.CustomerTable;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NewCustomerActivity extends AppCompatActivity {

    private Button backButton;
    private Button confirmButton;
    private Context context;
    private Customer c;
    private EditText nameEditText;
    private EditText phoneNumberEditText;
    private EditText addressEditText;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_customer);
        c = new Customer();

        context = getApplicationContext();
        database = new CustomerBaseHelper(context).getWritableDatabase();

        nameEditText = (EditText) findViewById(R.id.enter_name_edittext);
        nameEditText.setText(R.string.name);
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                c.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        phoneNumberEditText = (EditText) findViewById(R.id.phone_number_edittext);
        phoneNumberEditText.setText(R.string.sample_phone_number);
        phoneNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                c.setPhoneNumber(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        addressEditText = (EditText) findViewById(R.id.address_edittext);
        addressEditText.setText(R.string.address);
        addressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                c.setAddress(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewCustomerActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });

        confirmButton = (Button) findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomer(c);
            }
        });

    }

    private static ContentValues getContentValues(Customer customer) {
        ContentValues values = new ContentValues();
        values.put(CustomerTable.Cols.NAME, customer.getName());
        values.put(CustomerTable.Cols.PHONE_NUMBER, customer.getPhoneNumber());
        values.put(CustomerTable.Cols.ADDRESS, customer.getAddress());

        return values;
    }

    public void addCustomer(Customer c) {
        ContentValues values = getContentValues(c);
        database.insert(CustomerTable.NAME, null, values);
    }
}

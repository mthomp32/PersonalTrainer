package com.bignerdranch.android.personaltrainer;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NewCustomerActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneNumberEditText;
    private EditText addressEditText;
    private Button backButton;
    private Button confirmButton;
    private Context context;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_customer);

        context = getApplicationContext();
        database = new CustomerBaseHelper(context).getWritableDatabase();

        nameEditText = (EditText) findViewById(R.id.name_edittext);
        phoneNumberEditText = (EditText) findViewById(R.id.phone_number_edittext);
        addressEditText = (EditText) findViewById(R.id.address_edittext);

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
                String name = nameEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();
                String address = addressEditText.getText().toString();
                if ("".equals(name) && "".equals(phoneNumber) && "".equals(address)) {
                    clearEditTexts();
                } else {
                    Customer customer = new Customer();
                    customer.setName(name);
                    customer.setPhoneNumber(Integer.parseInt(phoneNumber));
                    customer.setAddress(address);
                    addCustomer(customer);
                    clearEditTexts();
                }
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

    public void clearEditTexts() {
        nameEditText.setText("");
        phoneNumberEditText.setText("");
        addressEditText.setText("");
    }

}

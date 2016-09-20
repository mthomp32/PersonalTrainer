package com.bignerdranch.android.personaltrainer;

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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NewCustomerActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneNumberEditText;
    private EditText addressEditText;
    private Button backButton;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_customer);

        nameEditText = (EditText) findViewById(R.id.name_edittext);
        phoneNumberEditText = (EditText) findViewById(R.id.phone_number_edittext);
        addressEditText = (EditText) findViewById(R.id.address_edittext);

        final ArrayList<Customer> customers = new ArrayList<Customer>();

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
                if (name != "" && phoneNumber != "" && address != "") {
                    Customer customer = new Customer();
                    customer.setName(name);
                    customer.setPhoneNumber(Integer.parseInt(phoneNumber));
                    customer.setAddress(address);
                    customers.add(customer);
                }
            }
        });
    }
}

package com.bignerdranch.android.personaltrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bignerdranch.android.personaltrainer.database.CustomerCursorWrapper;

import java.util.ArrayList;
import java.util.List;

public class CustomerListActivity extends AppCompatActivity {

    private ListView customerInfoListView;
    private ListView customerPictureListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_customer_list);

        customerInfoListView = (ListView) findViewById(R.id.customer_info_listview);
        customerPictureListView = (ListView) findViewById(R.id.customer_picture_listview);

        ArrayList<Customer> customerArray = new ArrayList<Customer>();
        int layoutID = android.R.layout.simple_list_item_1;

        ArrayAdapter<Customer> customerArrayAdapter;
        customerArrayAdapter = new ArrayAdapter<Customer>(this, layoutID, customerArray);

        customerInfoListView.setAdapter(customerArrayAdapter);

    }
}

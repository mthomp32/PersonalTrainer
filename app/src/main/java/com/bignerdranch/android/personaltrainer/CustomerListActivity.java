package com.bignerdranch.android.personaltrainer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bignerdranch.android.personaltrainer.database.CustomerBaseHelper;
import com.bignerdranch.android.personaltrainer.database.CustomerCursorWrapper;
import com.bignerdranch.android.personaltrainer.database.CustomerDbSchema;
import com.bignerdranch.android.personaltrainer.database.CustomerDbSchema.CustomerTable;

import java.util.ArrayList;
import java.util.List;

public class CustomerListActivity extends AppCompatActivity {

    private ListView customerInfoListView;
    private ListView customerPictureListView;
    private Context context;
    private SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_customer_list);

        context = getApplicationContext();
        database = new CustomerBaseHelper(context).getWritableDatabase();

        customerInfoListView = (ListView) findViewById(R.id.customer_info_listview);
        customerPictureListView = (ListView) findViewById(R.id.customer_picture_listview);

        List<Customer> customerArray = new ArrayList<Customer>();
        customerArray = getCustomers();
        int layoutID = android.R.layout.simple_list_item_1;

        ArrayAdapter<Customer> customerArrayAdapter;
        customerArrayAdapter = new ArrayAdapter<Customer>(this, layoutID, customerArray);

        customerInfoListView.setAdapter(customerArrayAdapter);

    }

    private CustomerCursorWrapper queryCustomers(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                CustomerTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new CustomerCursorWrapper(cursor);
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();

        CustomerCursorWrapper cursor = queryCustomers(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                customers.add(cursor.getCustomer());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return customers;
    }

}

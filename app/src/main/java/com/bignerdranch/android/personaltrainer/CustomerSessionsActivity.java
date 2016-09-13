package com.bignerdranch.android.personaltrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class CustomerSessionsActivity extends AppCompatActivity {

    private ListView customerSessionsListView;
    private Button addNewButton;
    private Button editButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_customer_sessions);

        customerSessionsListView = (ListView) findViewById(R.id.customer_sessions_listview);

        addNewButton = (Button) findViewById(R.id.add_new_button);
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to be added to add new customer
            }
        });

        editButton = (Button) findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to be added to edit sessions from session list. This will
                // launch the EditSessionActivity
            }
        });

        deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to be added to delete sessions from session list
            }
        });
    }
}

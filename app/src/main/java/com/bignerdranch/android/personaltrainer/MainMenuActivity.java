package com.bignerdranch.android.personaltrainer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {

    private Button newCustomerButton;
    private Button customerListButton;
    private Button customerSessionsButton;
    private Button cameraButton;
    private TextView mainMenuTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_menu);

        mainMenuTextView = (TextView) findViewById(R.id.main_menu_text_view);

        newCustomerButton = (Button)findViewById(R.id.new_customer_button);
        newCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, NewCustomerActivity.class);
                startActivity(intent);
            }
        });

        customerListButton = (Button) findViewById(R.id.customer_list_button);
        customerListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, CustomerListActivity.class);
                startActivity(intent);
            }
        });

        customerSessionsButton = (Button) findViewById(R.id.customer_sessions_button);
        customerSessionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, CustomerSessionsActivity.class);
                startActivity(intent);
            }
        });

        cameraButton = (Button) findViewById(R.id.camera_button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to be added to open and use the camera.
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logoff_menu_item) {
            super.onOptionsItemSelected(item);
            Intent intent = new Intent(MainMenuActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        Toast.makeText(this, R.string.logging_off_toast, Toast.LENGTH_SHORT).show();
        return true;
    }
}

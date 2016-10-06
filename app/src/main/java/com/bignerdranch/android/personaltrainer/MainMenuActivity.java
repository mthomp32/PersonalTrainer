package com.bignerdranch.android.personaltrainer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
                dispatchTakePictureIntent();
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

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        currentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred..
            }

            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this,
                        "com.bignerdranch.android.personaltrainer.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_PHOTO);
            }
        }
    }
}

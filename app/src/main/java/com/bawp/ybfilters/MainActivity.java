package com.bawp.ybfilters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;
import com.bawp.yb_filterlibrary.CameraPermissionHelper;
import com.bawp.yb_filterlibrary.YBFilters;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 17;
    private ImageView MAIN_Image;
    private MaterialButton MAIN_BTN_takePicture;
    private RecyclerView Main_LST_images;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Uri uri;
    ArrayList<ColorMatrixColorFilter> filters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findAllViews();
        MAIN_BTN_takePicture.setOnClickListener(view -> dispatchTakePictureIntent());

        YBFilters ybFilters = new YBFilters(this);
        filters = ybFilters.getAllFilters();
        ybFilters.askCameraPermission(this);

    }

    private void setRecyclerViewAdapter() {
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, filters, uri);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        Main_LST_images.setLayoutManager(linearLayoutManager);
        Main_LST_images.setAdapter(myRecyclerViewAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] results) {
        super.onRequestPermissionsResult(requestCode, permissions, results);
        if (!CameraPermissionHelper.hasCameraPermission(this)) {
            //Permission denied
            Toast.makeText(this, "Camera permission is needed to run this application", Toast.LENGTH_LONG)
                    .show();
            if (!CameraPermissionHelper.shouldShowRequestPermissionRationale(this)) {
                //Permission denied with checking "Do not ask again".
                CameraPermissionHelper.launchPermissionSettings(this);
            }
        }
    }

    private void dispatchTakePictureIntent() {

        if (!checkStoragePermission())
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        else{
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "NEW PICTURE");
            values.put(MediaStore.Images.Media.DESCRIPTION,"from camera");
            uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } catch (ActivityNotFoundException e) {
                // display error state to the user
            }
        }
    }

    public boolean checkStoragePermission(){
        return checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            MAIN_Image.setImageURI(uri);
            MAIN_Image.setRotation(90);
            setRecyclerViewAdapter();
        }
    }

    private void findAllViews() {
        MAIN_Image = findViewById(R.id.MAIN_Image);
        MAIN_BTN_takePicture = findViewById(R.id.MAIN_BTN_takePicture);
        Main_LST_images = findViewById(R.id.Main_LST_images);
    }
}
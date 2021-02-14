package com.bawp.ybfilters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.SeekBar;
import com.bawp.yb_filterlibrary.YBFilters;
import com.google.android.material.button.MaterialButton;

public class Matrix_Activity extends AppCompatActivity {

    private static final int REQUEST_CODE = 17;
    private ImageView image_background;
    private MaterialButton Matrix_BTN_TakePhoto;
    private SeekBar seek_saturation;
    private SeekBar seek_scale;
    private SeekBar seek_scale_red;
    private SeekBar seek_scale_green;
    private SeekBar seek_scale_blue;
    private SeekBar seek_rotate_red;
    private SeekBar seek_rotate_green;
    private SeekBar seek_rotate_blue;
    private SeekBar seek_binary;
    private SeekBar seek_black_white;
    private SeekBar seek_red_mul;
    private SeekBar seek_green_mul;
    private SeekBar seek_blue_mul;
    private SeekBar seek_red_add;
    private SeekBar seek_green_add;
    private SeekBar seek_blue_add;
    private MaterialButton originalImage;
    private MaterialButton grey;
    private MaterialButton greyOnRed;
    private MaterialButton greyOnGreen;
    private MaterialButton greyOnBlue;
    private MaterialButton red;
    private MaterialButton green;
    private MaterialButton blue;
    private MaterialButton inverted;
    private MaterialButton binary;
    private MaterialButton colorSwap;
    private MaterialButton colorSwap2;
    private MaterialButton sepia;
    private MaterialButton grayScale;
    private MaterialButton alphaBlue;
    private MaterialButton alphaPink;
    private MaterialButton blur;
    private MaterialButton sharpe;
    private MaterialButton glass;
    private Uri uri;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private int mulRed = 0;
    private int mulGreen= 0;
    private int mulBlue= 0;
    private int addRed= 0;
    private int addGreen= 0;
    private int addBlue= 0;
    private YBFilters ybFilters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        findAllViews();
        ybFilters = new YBFilters(this);
        ybFilters.askCameraPermission(this);
        ybFilters.uploadImageURL(image_background,"https://miro.medium.com/max/875/1*Bzelk40QghXg_EDhydMM3Q.png");
        Matrix_BTN_TakePhoto.setOnClickListener(view -> dispatchTakePictureIntent());

        seek_saturation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ybFilters.adjust(image_background, YBFilters.Adjust.SATURATION, i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_scale.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ybFilters.adjust(image_background, YBFilters.Adjust.SCALE , i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_scale_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ybFilters.adjust(image_background, YBFilters.Adjust.SCALE_RED , i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_scale_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ybFilters.adjust(image_background, YBFilters.Adjust.SCALE_GREEN , i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_scale_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ybFilters.adjust(image_background, YBFilters.Adjust.SCALE_BLUE , i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_rotate_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ybFilters.adjust(image_background, YBFilters.Adjust.ROTATE_RED , i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_rotate_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ybFilters.adjust(image_background, YBFilters.Adjust.ROTATE_GREEN , i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_rotate_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ybFilters.adjust(image_background, YBFilters.Adjust.ROTATE_BLUE , i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_binary.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ybFilters.adjust(image_background, YBFilters.Adjust.SEEK_BINARY , i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_black_white.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ybFilters.adjust(image_background, YBFilters.Adjust.SEEK_BLACK_WHITE , i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_red_mul.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mulRed = i;
                ybFilters.manipulateColors(image_background , mulRed,  mulGreen,  mulBlue,  addRed,  addGreen,  addBlue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_green_mul.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mulGreen = i;
                ybFilters.manipulateColors(image_background , mulRed, mulGreen,  mulBlue,  addRed,  addGreen,  addBlue);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_blue_mul.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mulBlue = i;
                ybFilters.manipulateColors(image_background , mulRed,  mulGreen, mulBlue,  addRed,  addGreen,  addBlue);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_red_add.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                addRed = i;
                ybFilters.manipulateColors(image_background , mulRed,  mulGreen,  mulBlue, addRed,  addGreen,  addBlue);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_green_add.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                addGreen = i;
                ybFilters.manipulateColors(image_background , mulRed,  mulGreen,  mulBlue,  addRed,  addGreen,  addBlue);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_blue_add.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                addBlue = i;
                ybFilters.manipulateColors(image_background , mulRed,  mulGreen,  mulBlue,  addRed,  addGreen,  addBlue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        originalImage.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.ORIGINAL));

        grey.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.GREY));

        greyOnRed.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.GREY_ON_RED));

        greyOnGreen.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.GREY_ON_GREEN));

        greyOnBlue.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.GREY_ON_BLUE));

        red.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.RED));

        green.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.GREEN));

        blue.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.BLUE));

        inverted.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.INVERT));

        binary.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.BINARY));

        colorSwap.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.COLOR_SWAP));

        colorSwap2.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.COLOR_SWAP2));

        sepia.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.SEPIA));

        grayScale.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.GREY_SCALE));

        alphaBlue.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.ALPHA_BLUE));

        alphaPink.setOnClickListener(view -> ybFilters.setFilter(image_background, YBFilters.Filters.ALPHA_PINK));

        blur.setOnClickListener(view -> ybFilters.blurImage(image_background, 9));

        sharpe.setOnClickListener(view -> ybFilters.sharpenImage(image_background));

        glass.setOnClickListener(view -> ybFilters.glassImage(image_background));
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
            ybFilters.uploadImageUri(image_background, uri);
        }
    }

    private void findAllViews() {
        image_background = findViewById(R.id.image_background);
        Matrix_BTN_TakePhoto = findViewById(R.id.Matrix_BTN_TakePhoto);
        seek_saturation = findViewById(R.id.seek_saturation);
        seek_scale = findViewById(R.id.seek_scale);
        seek_scale_red = findViewById(R.id.seek_scale_red);
        seek_scale_green = findViewById(R.id.seek_scale_green);
        seek_scale_blue = findViewById(R.id.seek_scale_blue);
        seek_rotate_red = findViewById(R.id.seek_rotate_red);
        seek_rotate_green = findViewById(R.id.seek_rotate_green);
        seek_rotate_blue = findViewById(R.id.seek_rotate_blue);
        seek_binary = findViewById(R.id.seek_binary);
        seek_black_white = findViewById(R.id.seek_black_white);
        seek_red_mul = findViewById(R.id.seek_red_mul);
        seek_green_mul = findViewById(R.id.seek_green_mul);
        seek_blue_mul = findViewById(R.id.seek_blue_mul);
        seek_red_add = findViewById(R.id.seek_red_add);
        seek_green_add = findViewById(R.id.seek_green_add);
        seek_blue_add = findViewById(R.id.seek_blue_add);
        originalImage = findViewById(R.id.originalImage);
        grey = findViewById(R.id.grey);
        greyOnRed = findViewById(R.id.greyOnRed);
        greyOnGreen = findViewById(R.id.greyOnGreen);
        greyOnBlue = findViewById(R.id.greyOnBlue);
        red = findViewById(R.id.red);
        green = findViewById(R.id.green);
        blue = findViewById(R.id.blue);
        inverted = findViewById(R.id.inverted);
        binary = findViewById(R.id.binary);
        colorSwap = findViewById(R.id.colorSwap);
        colorSwap2 = findViewById(R.id.colorSwap2);
        sepia = findViewById(R.id.sepia);
        grayScale = findViewById(R.id.grayScale);
        alphaBlue = findViewById(R.id.alphaBlue);
        alphaPink = findViewById(R.id.alphaPink);
        blur = findViewById(R.id.blur);
        sharpe = findViewById(R.id.sharpe);
        glass = findViewById(R.id.glass);
    }
}
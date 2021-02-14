package com.bawp.yb_filterlibrary;

import android.app.Activity;
import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.widget.ImageView;
import android.net.Uri;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class YBFilters {

    private final Context context;

    public YBFilters(Context context) {
        this.context = context;
    }

    public void setFilter(ImageView imageView, String color) {

        ColorMatrix matrix = new ColorMatrix();
        MyFilters myFilters = new MyFilters();
        switch (color){
            case Filters.BLUE:
                matrix.set(myFilters.blue());
                break;
            case Filters.GREEN:
                matrix.set(myFilters.green());
                break;
            case Filters.RED:
                matrix.set(myFilters.red());
                break;
            case Filters.GREY:
                matrix.set(myFilters.grey());
                break;
            case Filters.GREY_ON_BLUE:
                matrix.set(myFilters.greyOnBlue());
                break;
            case Filters.GREY_ON_RED:
                matrix.set(myFilters.greyOnRed());
                break;
            case Filters.GREY_ON_GREEN:
                matrix.set(myFilters.greyOnGreen());
                break;
            case Filters.INVERT:
                matrix.set(myFilters.inverted());
                break;
            case Filters.COLOR_SWAP:
                matrix.set(myFilters.colorSwap());
                break;
            case Filters.COLOR_SWAP2:
                matrix.set(myFilters.colorSwap2());
                break;
            case Filters.ALPHA_BLUE:
                matrix.set(myFilters.AlphaBlue());
                break;
            case Filters.ALPHA_PINK:
                matrix.set(myFilters.AlphaPink());
                break;
            case Filters.GREY_SCALE:
                matrix.set(myFilters.grayScale());
                break;
            case Filters.BINARY:
                matrix.set(myFilters.Binary());
                break;
            case Filters.SEPIA:
                matrix.set(myFilters.Sepia());
                break;
            default:
                matrix.set(myFilters.originalImage());
        }
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imageView.setColorFilter(filter);
    }

    public void adjust(ImageView imageView, String adjust, float progress) {
        YBAdjust ybAdjust = new YBAdjust();
        switch (adjust){
            case Adjust.SCALE:
                ybAdjust.scale(imageView, progress);
                break;
            case Adjust.SATURATION:
                ybAdjust.saturation(imageView, progress);
                break;
            case Adjust.ROTATE_BLUE:
                ybAdjust.rotateBlue(imageView, progress);
                break;
            case Adjust.ROTATE_GREEN:
                ybAdjust.rotateGreen(imageView, progress);
                break;
            case Adjust.ROTATE_RED:
                ybAdjust.rotateRed(imageView, progress);
                break;
            case Adjust.SCALE_BLUE:
                ybAdjust.scaleBlue(imageView, progress);
                break;
            case Adjust.SCALE_GREEN:
                ybAdjust.scaleGreen(imageView, progress);
                break;
            case Adjust.SCALE_RED:
                ybAdjust.scaleRed(imageView, progress);
                break;
            case Adjust.SEEK_BINARY:
                ybAdjust.seekBinary(imageView, progress);
                break;
            case Adjust.SEEK_BLACK_WHITE:
                ybAdjust.seekBlackWhite(imageView, progress);
                break;
        }
    }

    public void manipulateColors(ImageView imageView ,int mulRed, int mulGreen, int mulBlue, int addRed, int addGreen, int addBlue){
        YBAdjust ybAdjust = new YBAdjust();
        ybAdjust.manipulateColors(imageView, mulRed, mulGreen, mulBlue, addRed, addGreen, addBlue);
    }

    public void blurImage(ImageView imageView, float radius){
        MyFilters myFilters = new MyFilters();
        myFilters.blur(imageView, radius, context);
    }

    public void sharpenImage(ImageView imageView){
        MyFilters myFilters = new MyFilters();
        myFilters.sharpe(imageView, context);
    }

    public void glassImage(ImageView imageView){
        MyFilters myFilters = new MyFilters();
        myFilters.glass(imageView, context);
    }

    private FilterMethod[] setFiltersArray(){
        MyFilters myFilters = new MyFilters();
        FilterMethod [] filterMethods = new FilterMethod[]{
                new FilterMethod() {public float[] applyFilter() {return myFilters.originalImage();}},
                new FilterMethod() {public float[] applyFilter() {return myFilters.AlphaBlue();}},
                new FilterMethod() {public float[] applyFilter() {return myFilters.AlphaPink();}},
                new FilterMethod() {public float[] applyFilter() {return myFilters.Sepia();}},
                new FilterMethod() {public float[] applyFilter() {return myFilters.blue();}},
                new FilterMethod() {public float[] applyFilter() {return myFilters.red();}},
                new FilterMethod() {public float[] applyFilter() {return myFilters.green();}},
                new FilterMethod() {public float[] applyFilter() {return myFilters.colorSwap();}},
                new FilterMethod() {public float[] applyFilter() {return myFilters.colorSwap2();}},
                new FilterMethod() {public float[] applyFilter() {return myFilters.grayScale();}},
                new FilterMethod() {public float[] applyFilter() {return myFilters.grey();}},
                new FilterMethod() {public float[] applyFilter() {return myFilters.greyOnGreen();}},
                new FilterMethod() {public float[] applyFilter() {return myFilters.greyOnBlue();}},
                new FilterMethod() {public float[] applyFilter() {return myFilters.greyOnRed();}},
                new FilterMethod() {public float[] applyFilter() {return myFilters.Binary();}},
                new FilterMethod() {public float[] applyFilter() {return myFilters.inverted();}}

        };
        return filterMethods;
    }

    public ArrayList<ColorMatrixColorFilter> getAllFilters(){
        ArrayList<ColorMatrixColorFilter> arrayList = new ArrayList<>();
        FilterMethod [] filterMethods = setFiltersArray();
        ColorMatrix matrix = new ColorMatrix();
        for (FilterMethod filter: filterMethods) {
            matrix.set(filter.applyFilter());
            ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(matrix);
            arrayList.add(colorMatrixColorFilter);
        }
        return arrayList;
    }


    public void askCameraPermission(Activity activity) {
        if (!CameraPermissionHelper.hasCameraPermission(activity)) {
            CameraPermissionHelper.requestCameraPermission(activity);
        }
    }
    public void uploadImageDrawable(ImageView imageView, int image) {
        Glide
                .with(context)
                .load(image)
                .into(imageView);
    }

    public void uploadImageUri(ImageView imageView, Uri uri) {
        Glide
                .with(context)
                .load(uri)
                .into(imageView);
    }

    public void uploadImageURL(ImageView imageView, String url) {
        Glide
                .with(context)
                .load(url)
                .into(imageView);
    }

    public interface Filters{
        String BLUE = "BLUE";
        String RED = "RED";
        String GREEN = "GREEN";
        String GREY = "GREY";
        String GREY_ON_BLUE = "GREY_ON_BLUE";
        String GREY_ON_RED = "GREY_ON_RED";
        String GREY_ON_GREEN = "GREY_ON_GREEN";
        String INVERT = "INVERT";
        String COLOR_SWAP = "COLOR_SWAP";
        String COLOR_SWAP2 = "COLOR_SWAP2";
        String ORIGINAL = "ORIGINAL";
        String ALPHA_BLUE = "ALPHA_BLUE";
        String ALPHA_PINK = "ALPHA_PINK";
        String SEPIA = "SEPIA";
        String BINARY = "BINARY";
        String GREY_SCALE = "GREY_SCALE";
    }

    public interface Adjust{
        String SCALE = "SCALE";
        String SATURATION = "SATURATION";
        String ROTATE_BLUE = "ROTATE_BLUE";
        String ROTATE_GREEN = "ROTATE_GREEN";
        String ROTATE_RED = "ROTATE_RED";
        String SCALE_BLUE = "SCALE_BLUE";
        String SCALE_GREEN = "SCALE_GREEN";
        String SCALE_RED = "SCALE_RED";
        String SEEK_BINARY = "SEEK_BINARY";
        String SEEK_BLACK_WHITE = "SEEK_BLACK_WHITE";
    }

}

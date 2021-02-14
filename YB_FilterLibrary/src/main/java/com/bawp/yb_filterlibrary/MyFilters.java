package com.bawp.yb_filterlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.renderscript.ScriptIntrinsicConvolve3x3;
import android.widget.ImageView;

public class MyFilters {

    public float [] blue() {
        return new float [] {
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                1, 1, 1, 0, 0,
                0, 0, 0, 1, 0};
    }

    public float [] green() {
        return new float[]{
                0, 0, 0, 0, 0,
                1, 1, 1, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 1, 0};
    }

    public float [] red() {
        return new float []{
                1, 1, 1, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 1, 0};

    }

    public float [] grey() {
        return new float []{
                0.33f, 0.33f, 0.33f, 0, 0,
                0.33f, 0.33f, 0.33f, 0, 0,
                0.33f, 0.33f, 0.33f, 0, 0,
                0, 0, 0, 1, 0};
    }

    public float [] greyOnBlue() {
        return new float []{
                0, 0, 1, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0 };
    }

    public float [] greyOnRed() {
        return new float []{
                1, 0, 0, 0, 0,
                1, 0, 0, 0, 0,
                1, 0, 0, 0, 0,
                0, 0, 0, 1, 0};
    }

    public float [] greyOnGreen() {
        return new float []{
                0, 1, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 0, 1, 0};
    }

    public float [] inverted() {
        return new float []{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0};
    }

    public float [] colorSwap() {
        return new float []{
                0, 0, 1, 0, 0,
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 0, 1, 0};
    }

    public float [] colorSwap2() {
        return new float []{
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                1, 0, 0, 0, 0,
                0, 0, 0, 1, 0 };
    }

    public float [] originalImage(){
        return new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        };
    }

    public float [] Sepia(){
        return new float[]{
                1, 0,   0, 0, 0,
                0, 1,   0, 0, 0,
                0, 0, 0.8f, 0, 0,
                0, 0,   0, 1, 0
        };
    }

    public float [] grayScale(){
        return new float[]{
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0,     0,     0, 1, 0
        };
    }

    public float [] Binary(){
        float m = 255f;
        float t = -255*128f;
        return new float[]{
                m, 0, 0, 1, t,
                0, m, 0, 1, t,
                0, 0, m, 1, t,
                0, 0, 0, 1, 0
        };
    }

    public float [] AlphaBlue(){
        return new float[]{
                0, 0, 0, 0, 0,
                0.3f, 0, 0, 0, 50,
                0, 0, 0, 0, 255,
                0.2f, 0.4f, 0.4f, 0, -30
        };
    }

    public float [] AlphaPink(){
        return new float[]{
                0, 0, 0, 0, 255,
                0, 0, 0, 0, 0,
                0.2f, 0, 0, 0, 50,
                0.2f, 0.2f, 0.2f, 0, -20
        };
    }

    public void blur(ImageView imageView, float radius, Context context) {
        float BITMAP_SCALE = 0.4f;
        Bitmap image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        theIntrinsic.setRadius(radius);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);
        imageView.setImageBitmap(outputBitmap);
        rs.destroy();
    }

    public void sharpe(ImageView imageView, Context context) {

        float[] coefficients = {  0, -1,  0,
                                 -1 , 10, -1,
                                  0, -1,  0  };
        Bitmap original = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        Bitmap bitmap = Bitmap.createBitmap(original.getWidth(), original.getHeight(), Bitmap.Config.ARGB_8888);

        RenderScript rs = RenderScript.create(context);

        Allocation allocIn = Allocation.createFromBitmap(rs, original);
        Allocation allocOut = Allocation.createFromBitmap(rs, bitmap);

        ScriptIntrinsicConvolve3x3 convolution = ScriptIntrinsicConvolve3x3.create(rs, Element.U8_4(rs));
        convolution.setInput(allocIn);
        convolution.setCoefficients(coefficients);
        convolution.forEach(allocOut);

        allocOut.copyTo(bitmap);
        rs.destroy();
        imageView.setImageBitmap(bitmap);
    }

    public void glass(ImageView imageView, Context context) {

        float[] coefficients = {0,  20/7f,  0,
                20/7f, -59/7f, 20/7f,
                1/7f,  13/7f,  0  } ;
        Bitmap original = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Bitmap bitmap = Bitmap.createBitmap(original.getWidth(), original.getHeight(),
                Bitmap.Config.ARGB_8888);
        RenderScript rs = RenderScript.create(context);
        Allocation allocIn = Allocation.createFromBitmap(rs, original);
        Allocation allocOut = Allocation.createFromBitmap(rs, bitmap);
        ScriptIntrinsicConvolve3x3 convolution = ScriptIntrinsicConvolve3x3.create(rs, Element.U8_4(rs));
        convolution.setInput(allocIn);
        convolution.setCoefficients(coefficients);
        convolution.forEach(allocOut);
        allocOut.copyTo(bitmap);
        rs.destroy();
        imageView.setImageBitmap(bitmap);
    }

}

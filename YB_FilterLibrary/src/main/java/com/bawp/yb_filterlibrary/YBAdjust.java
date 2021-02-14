package com.bawp.yb_filterlibrary;


import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.widget.ImageView;

public class YBAdjust {

    public void scale(ImageView imageView, float progress){
        ColorMatrix matrix = new ColorMatrix();
        matrix.setScale((progress)/255f, (progress)/255f,(progress)/255f, 1f);
        imageView.setColorFilter(new ColorMatrixColorFilter(matrix));
        imageView.getMatrix();
    }


    public void saturation(ImageView imageView, float progress){
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation((progress)/255f);
        imageView.setColorFilter(new ColorMatrixColorFilter(matrix));
    }


    public void rotateBlue(ImageView imageView, float progress){
        ColorMatrix matrix = new ColorMatrix();
        matrix.setRotate(2, progress);
        imageView.setColorFilter(new ColorMatrixColorFilter(matrix));
    }

    public void rotateGreen(ImageView imageView, float progress){
        ColorMatrix matrix = new ColorMatrix();
        matrix.setRotate(1, progress);
        imageView.setColorFilter(new ColorMatrixColorFilter(matrix));
    }

    public void rotateRed(ImageView imageView, float progress){
        ColorMatrix matrix = new ColorMatrix();
        matrix.setRotate(0, progress);
        imageView.setColorFilter(new ColorMatrixColorFilter(matrix));
    }

    public void scaleBlue(ImageView imageView, float progress){
        ColorMatrix matrix = new ColorMatrix();
        matrix.setScale(1f, 1f,(progress)/255f, 1f);
        imageView.setColorFilter(new ColorMatrixColorFilter(matrix));
    }

    public void scaleGreen(ImageView imageView, float progress){
        ColorMatrix matrix = new ColorMatrix();
        matrix.setScale(1f, (progress)/255f,1f, 1f);
        imageView.setColorFilter(new ColorMatrixColorFilter(matrix));
    }

    public void scaleRed(ImageView imageView, float progress){
        ColorMatrix matrix = new ColorMatrix();
        matrix.setScale((progress)/255f, 1f,1f, 1f);
        imageView.setColorFilter(new ColorMatrixColorFilter(matrix));
    }

    public void seekBinary(ImageView imageView, float progress){
        ColorMatrix matrix = new ColorMatrix();
        float[] array = { 255f, 0f, 0f, 0f, (progress - 255)*255f,
                          0f, 255f, 0f, 0f, (progress - 255)*255f,
                          0f, 0f, 255f, 0f, (progress - 255)*255f,
                          0f, 0f, 0f, 1f, 0f};
        matrix.set(array);
        imageView.setColorFilter(new ColorMatrixColorFilter(matrix));
    }

    public void seekBlackWhite(ImageView imageView, float progress){
        ColorMatrix matrix = new ColorMatrix();
        float[] array = { 85f, 85f, 85f, 0f, (progress - 255)*255f,
                          85f, 85f, 85f, 0f, (progress - 255)*255f,
                          85f, 85f, 85f, 0f, (progress - 255)*255f,
                          0f, 0f, 0f, 1f, 0f};
        matrix.set(array);
        imageView.setColorFilter(new ColorMatrixColorFilter(matrix));
    }

    public void manipulateColors(ImageView imageView ,int mulRed, int mulGreen, int mulBlue, int addRed, int addGreen, int addBlue) {
        imageView.setColorFilter(new LightingColorFilter(
                mulRed * 0x10000 + mulGreen * 0x100 + mulBlue,
                addRed * 0x10000 + addGreen * 0x100 + addBlue));
    }
}
